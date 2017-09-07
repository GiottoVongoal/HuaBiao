package com.huabiao.aoiin.third;

import android.app.Activity;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;
import com.ywy.mylibs.utils.StringUtil;
import com.ywy.mylibs.utils.ToastUtils;
import com.ywy.mylibs.wedgit.dialog.CommonLoadingDialog;

import java.util.Map;
import java.util.Set;

/**
 * Created by guodong on 2016/4/21 16:43.
 */
public class UMLoginUtil {

    private Activity mContext;
    private UMShareAPI mShareAPI = null;
    private LoginCallBack loginCallBack;
    private String access_token = "";
    private String openId = "";
    private CommonLoadingDialog dialog;

    public UMLoginUtil(Activity context) {
        this.mContext = context;
        mShareAPI = UMShareAPI.get(context);
        dialog = new CommonLoadingDialog(context, "");
    }

    public void login(SHARE_MEDIA platform, LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
        if (platform == SHARE_MEDIA.WEIXIN && !mShareAPI.isInstall(mContext, platform)) {
            ToastUtils.getInstance().showToast(mContext.getResources().getString(R.string.uninstallWx));
        }
        mShareAPI.doOauthVerify(mContext, platform, authListener);
    }

    public void deleteAuth() {
//        String platform = PreferenceHelper.ins().getStringShareData(AppKey.THIRD_PLATFORM, false);
//        if (StringUtil.isEmpty(platform)) {
//            return;
//        }
//        mShareAPI.deleteOauth(mContext, SHARE_MEDIA.convertToEmun(platform), null);
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            StringBuilder sb = new StringBuilder();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                sb.append(key + " = ").append(map.get(key)).append(" ");
                if (share_media == SHARE_MEDIA.WEIXIN && key.equals("accessToken ")) {
                    access_token = map.get(key).toString();
                    break;
                } else if (share_media == SHARE_MEDIA.SINA) {
                    if (key.equals("accessToken ")) {
                        access_token = map.get(key).toString();
                    }
                    if (key.equals("uid")) {
                        openId = map.get(key).toString();
                    }
                } else if (share_media == SHARE_MEDIA.QQ) {
                    if (key.equals("accessToken ")) {
                        access_token = map.get(key).toString();
                    }
                }
            }
            ALog.d(sb.toString());
            mShareAPI.getPlatformInfo(mContext, share_media, platformInfoListener);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            ToastUtils.getInstance().showToast("授权错误");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
            ToastUtils.getInstance().showToast("授权取消");
        }
    };

    UMAuthListener platformInfoListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            SocializeUtils.safeCloseDialog(dialog);
            StringBuilder sb = new StringBuilder();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                sb.append(key + " = ").append(map.get(key)).append(" ");
            }
            ALog.d(sb.toString());
//            PreferenceHelper.ins().storeShareStringData(AppKey.THIRD_PLATFORM, share_media.toString());
            switch (share_media) {
                case QQ:
                    handlerQQInfo(map);
                    break;
                case SINA:
                    handlerSinaInfo(map);
                    break;
                case WEIXIN:
                    handlerWeixinInfo(map);
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            SocializeUtils.safeCloseDialog(dialog);
            if (throwable != null) {
                ALog.e(throwable.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            SocializeUtils.safeCloseDialog(dialog);
            ALog.e("onCancel");
        }
    };

    private void handlerQQInfo(Map<String, String> data) {
        if (data != null) {
            String name = "";
            String avatar = "";
            String openId = "";

            Set<String> keys = data.keySet();
            for (String key : keys) {
                if (key.equals("name")) {
                    name = data.get(key)
                            .toString();
                }
                if (key.equals("iconurl")) {
                    avatar = data.get(key).toString();
                }
                if (key.equals("uid")) {
                    openId = data.get(key).toString();
                }
            }

            if (null != loginCallBack) {
                loginCallBack.onSuccess(openId, name, avatar, access_token);
            }
        }
    }

    private void handlerSinaInfo(Map<String, String> data) {
        if (data != null) {
            String name = "";
            String avatar = "";
            String result = "";
//            Set<String> keys = data.keySet();
//            for (String key : keys) {
//                if (key.equals("result")) {
//                    result = data.get(key).toString();
//                    break;
//                }
//            }
//            if (StringUtil.isEmpty(result)) {
//                DLOG.e("未取到用户信息");
//                return;
//            }
//            try {
//                JSONObject infojson = new JSONObject(result);
//                name = infojson.optString("screen_name");
//                avatar = infojson.optString("avatar_hd");
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            Set<String> keys = data.keySet();
            for (String key : keys) {
                if (key.equals("name")) {
                    name = data.get(key).toString();
                }
                if (key.equals("iconurl")) {
                    avatar = data.get(key).toString();
                }
                if (key.equals("uid")) {
                    openId = data.get(key).toString();
                }
            }

            if (null != loginCallBack) {
                loginCallBack.onSuccess(openId, name, avatar, access_token);
            }
        }
    }

    private void handlerWeixinInfo(Map<String, String> data) {
        if (data != null) {
            String name = "";
            String avatar = "";
            Set<String> keys = data.keySet();
            for (String key : keys) {
                if (key.equals("name")) {
                    name = data.get(key).toString();
                }
                if (key.equals("iconurl")) {
                    avatar = data.get(key).toString();
                }
                if (key.equals("uid")) {
                    openId = data.get(key).toString();
                }
            }

            if (null != loginCallBack) {
                loginCallBack.onSuccess(openId, name, avatar, access_token);
            }
        }
    }


}
