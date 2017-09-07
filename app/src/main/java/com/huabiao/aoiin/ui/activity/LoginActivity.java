package com.huabiao.aoiin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.third.LoginCallBack;
import com.huabiao.aoiin.third.UMLoginUtil;
import com.huabiao.aoiin.timecount.TimeCount;
import com.huabiao.aoiin.timecount.VerficationCodeView;
import com.huabiao.aoiin.tools.ViewTools;
import com.huabiao.aoiin.ui.view.RippleView;
import com.huabiao.aoiin.wedgit.CheckEdittextTextWatcher;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.ywy.mylibs.base.BaseActivity;
import com.ywy.mylibs.base.BasePresenter;
import com.ywy.mylibs.constant.RegexConstants;
import com.ywy.mylibs.utils.StringUtil;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.activity
 * @date 2017-08-18 15:03
 * @description 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, VerficationCodeView.InputCompleteListener, RippleView.OnRippleCompleteListener {
    //手机号
    @Bind(R.id.login_phone)
    TextInputLayout login_phone;
    @Bind(R.id.iv_delete_phone)
    ImageView iv_delete_phone;
    //验证码
    @Bind(R.id.login_getcode_tv)
    TextView login_getcode_tv;
    @Bind(R.id.login_vcode_view)
    VerficationCodeView login_vcode_view;
    private String loginCode = "";
    //登录按钮
    @Bind(R.id.login_do_tv)
    TextView login_do_tv;

    @Bind(R.id.login_sina)
    RippleView login_sina;
    @Bind(R.id.login_wechat)
    RippleView login_wechat;
    @Bind(R.id.login_qq)
    RippleView login_qq;

    private TimeCount time;

    //第三方
    private UMLoginUtil loginUtil;

    @Override
    public void bindView(Bundle savedInstanceState) {
        setTitle("登录");
        setBackEnable();

        //手机号
//        login_phone.getEditText().addTextChangedListener(new CheckEdittextTextWatcher(login_phone, "请输入正确的手机号!", 1));
        //开启计数
//        login_phone.setCounterEnabled(true);
//        login_phone.setCounterMaxLength(11);//最大输入限制数(输入框后边有0/6的字数统计)
        //一键删除按钮监听
        ViewTools.setEdittext(login_phone.getEditText(), iv_delete_phone
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login_phone.getEditText().setText("");
                    }
                });
        login_phone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!TextUtils.isEmpty(s)) {
                    iv_delete_phone.setVisibility(View.VISIBLE);
                } else {
                    iv_delete_phone.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((login_phone.getEditText().getText().toString().trim()).matches(RegexConstants.REGEX_MOBILE_EXACT)) {
                    login_getcode_tv.setTextColor(getResources().getColor(R.color.yellow_fdd400));
                } else {
                    login_getcode_tv.setTextColor(getResources().getColor(R.color.grey_848484));
                }
            }
        });

        //初始化短信倒计时内容
        // time = new TimeCount(60000, 100);// 构造CountDownTimer对象
        time = new TimeCount(6000, 100);// 构造CountDownTimer对象
        time.init(this, login_getcode_tv);

        //第三方
        loginUtil = new UMLoginUtil(this);

        login_sina.setOnRippleCompleteListener(this);
        login_wechat.setOnRippleCompleteListener(this);
        login_qq.setOnRippleCompleteListener(this);
        //点击监听
        login_do_tv.setOnClickListener(this);
        login_getcode_tv.setOnClickListener(this);
        login_vcode_view.setInputCompleteListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_getcode_tv:
                if (StringUtil.isEmpty(login_phone.getEditText().getText().toString().trim())) {
                    showToast("请输入手机号");
                    return;
                }
                if (!(login_phone.getEditText().getText().toString().trim()).matches(RegexConstants.REGEX_MOBILE_EXACT)) {
                    showToast("请输入正确的手机号");
                    return;
                }
                time.start();// 开始计时
                break;
            case R.id.login_do_tv:
                if (StringUtil.isEmpty(login_phone.getEditText().getText().toString().trim())) {
                    showToast("请输入手机号");
                    return;
                }
                if (!(login_phone.getEditText().getText().toString().trim()).matches(RegexConstants.REGEX_MOBILE_EXACT)) {
                    showToast("请输入正确的手机号");
                    return;
                }
                if (StringUtil.isEmpty(loginCode)) {
                    showToast("请输入验证码");
                    return;
                }
                showToast("登录");
                break;
//            case R.id.login_sina:
//                platform = SHARE_MEDIA.SINA;
//                break;
        }
    }

    /**
     * 验证码监听
     */
    @Override
    public void inputComplete() {
        loginCode = login_vcode_view.getEditContent();
        ALog.i("loginCode--->" + login_vcode_view.getEditContent());
    }

    @Override
    public void onComplete(RippleView rippleView) {
        if (rippleView.getId() == R.id.login_sina) {
            loginUtil.login(SHARE_MEDIA.SINA, new LoginCallBack() {
                @Override
                public void onFailed(String msg) {
                    showToast(getString(R.string.error_login_sina));
                }

                @Override
                public void onSuccess(String unionId, String name, String avatar, String token) {
                    //urank = 9  screen_name = GiottoVongoal
                    // story_read_state = -1   mbrank = 0
                    // weihao =  province = 11 statuses_count = 6
                    // following = false class = 1 follow_me = false v
                    // erified_type = -1 id = 2349530141
                    // iconurl = http://tva4.sinaimg.cn/crop.1.0.712.712.50/8c0afc1djw1eha3zb9t20j20jw0jxdhy.jpg
                    // verified_reason_url =
                    // cover_image_phone = http://ww2.sinaimg.cn/crop.0.0.640.640.640/a1d3feabjw1ecatccqmkbj20hs0hsmzb.jpg
                    // accessToken = 2.008H5AZC2HuSFE0a3e4151baY1YRFB
                    // domain =  avatar_hd = http://tva4.sinaimg.cn/crop.1.0.712.712.1024/8c0afc1djw1eha3zb9t20j20jw0jxdhy.jpg
                    // friends_count = 73 bi_followers_count = 4 location = 北京 昌平区
                    // verified_source =  name = GiottoVongoal pagefriends_count = 2
                    // verified_reason =  mbtype = 0 verified_source_url =
                    // insecurity = {"sexual_content":false} remark =  url =  city = 14
                    // refreshToken = 2.008H5AZC2HuSFEef8704a1daM52GAC
                    // gender = 女 block_app = 0 ptype = 0 block_word = 0 star = 0
                    // status = {"created_at":"Thu Nov 17 15:09:50 +0800 2016","id":4042818861503631,"mid":"4042818861503631","idstr":"4042818861503631","text":"《福尔摩斯探案集001》很喜欢！都来听听吧http:\/\/t.cn\/RfVEwad（分享自@喜马拉雅FM）","textLength":79,"source_allowclick":0,"source_type":1,"source":"<a href=\"http:\/\/app.weibo.com\/t\/feed\/4vfDl3\" rel=\"nofollow\">喜马拉雅网<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"is_paid":false,"mblog_vip_type":0,"reposts_count":0,"comments_count":0,"attitudes_count":0,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_ids":[0],"biz_feature":0,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"more_info_type":0,"positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2,"comment_manage_info":{"comment_permission_type":-1}} verified = false allow_all_act_msg = false lang = zh-cn expiration = 1504810798824 idstr = 2349530141 vclub_member = 0 profile_url = u/2349530141 credit_score = 80 avatar_large = http://tva4.sinaimg.cn/crop.1.0.712.712.180/8c0afc1djw1eha3zb9t20j20jw0jxdhy.jpg user_ability = 0 online_status = 0 geo_enabled = true followers_count = 110 profile_image_url = http://tva4.sinaimg.cn/crop.1.0.712.712.50/8c0afc1djw1eha3zb9t20j20jw0jxdhy.jpg description =  access_token = 2.008H5AZC2HuSFE0a3e4151baY1YRFB verified_trade =  uid = 2349530141 created_at = Fri Sep 09 13:57:10 +0800 2011 favourites_count = 3 allow_all_comment = true expires_in = 1504810798824
                    login_phone.getEditText().setText(name);
                }

            });
        } else if (rippleView.getId() == R.id.login_wechat) {
            loginUtil.login(SHARE_MEDIA.WEIXIN, new LoginCallBack() {
                @Override
                public void onFailed(String msg) {
                    showToast(getString(R.string.error_login_wx));
                }

                @Override
                public void onSuccess(String unionid, String name, String avatar, String token) {
                    //  unionid = o2f5k0dUmX4KnYSiqnw3Ze7X9-dU
                    // screen_name = 羊小羊 city =
                    // accessToken = 0swNaNkjxObo0zajFIqmFjnZpDv_ivYYc5Aw-7JzxiMvgyw4ChEZj2QqSs5LLm2kIF4-W1JEtQviwqbrBc972w
                    // refreshToken = yxNAwOzvwPudWoAHHxIc_vP5S5AAj_nmLQR-lhJy8f7Spn5cc0Saro2iOUd640-PdH9DCy8WXO5ULQ5TWFRI7g
                    // gender = 女 province =  openid = oIyM70nxzS7ThTgfdz2vPg_quBkw
                    // profile_image_url = http://wx.qlogo.cn/mmopen/vi_32/DU6kIsJJMbnbqkiajtWfPRXBzJlvLd1ODbCSkZqlMBsqibp3U5YgYic5xDYO1OUicn1icwqDZfFa1CUWQfGWibTEZQlw/0
                    // country = 中国 access_token = 0swNaNkjxObo0zajFIqmFjnZpDv_ivYYc5Aw-7JzxiMvgyw4ChEZj2QqSs5LLm2kIF4-W1JEtQviwqbrBc972w
                    // iconurl = http://wx.qlogo.cn/mmopen/vi_32/DU6kIsJJMbnbqkiajtWfPRXBzJlvLd1ODbCSkZqlMBsqibp3U5YgYic5xDYO1OUicn1icwqDZfFa1CUWQfGWibTEZQlw/0
                    // name = 羊小羊 uid = o2f5k0dUmX4KnYSiqnw3Ze7X9-dU expiration = 1504677591174 language = zh_CN expires_in = 1504677591174
                    login_phone.getEditText().setText(name);
                }
            });
        } else if (rippleView.getId() == R.id.login_qq) {
            loginUtil.login(SHARE_MEDIA.QQ, new LoginCallBack() {
                @Override
                public void onFailed(String msg) {
                    showToast(getString(R.string.error_login_qq));
                }

                @Override
                public void onSuccess(String unionId, String name, String avatar, String token) {
                    // unionid =  is_yellow_vip = 0
                    // screen_name = 像女神一样的女子
                    // msg =  vip = 0 city = 闵行
                    // accessToken = 83218BA42A0B0C66CBE44A51665CDB0F
                    // gender = 男 province = 上海 is_yellow_year_vip = 0
                    // openid = B10FDCD7E2DA59651D518D06EC3B2A7D
                    // yellow_vip_level = 0
                    // profile_image_url = http://q.qlogo.cn/qqapp/1106093579/B10FDCD7E2DA59651D518D06EC3B2A7D/100
                    // access_token = 83218BA42A0B0C66CBE44A51665CDB0F
                    // iconurl = http://q.qlogo.cn/qqapp/1106093579/B10FDCD7E2DA59651D518D06EC3B2A7D/100
                    // name = 像女神一样的女子 uid = B10FDCD7E2DA59651D518D06EC3B2A7D
                    // expiration = 1512443062698 expires_in = 1512443062698 ret = 0 level = 0
                    login_phone.getEditText().setText(name);
                }
            });
        }
    }

    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public int getContentLayout() {
        return R.layout.login_layout;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void deleteContent(boolean isDelete) {

    }
}
