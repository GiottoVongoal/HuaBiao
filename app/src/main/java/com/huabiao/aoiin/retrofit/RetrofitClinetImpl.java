package com.huabiao.aoiin.retrofit;

import android.content.Context;
import android.util.Log;

import com.ywy.mylibs.listener.BaseRetrofitCallBackResponse;
import com.ywy.mylibs.listener.ICallBackResultCode;
import com.ywy.mylibs.listener.ICallBackStatusCode;
import com.ywy.mylibs.retrofit.RetrofitClient;
import com.ywy.mylibs.utils.NetWorkUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaoshuai.
 * @PackageName com.huabiao.aoiin.retrofit
 * @date 2016-10-27 16:35
 * @description 网络请求实现类
 */
public class RetrofitClinetImpl {
    private static final String TAG = "RetrofitClinetImpl";
    private volatile static RetrofitClinetImpl instance;
    private static String url = "\"http://uniapi.cheyipai.com/\"";
    private static boolean isParseStateCode = false;
    private static boolean isLoading = true;
    private static Context mContext;

    private RetrofitClinetImpl() {
    }

    public static RetrofitClinetImpl getInstance(Context context) {
        initRetrofitSet(context);
        if (instance == null) {
            synchronized (RetrofitClinetImpl.class) {
                if (instance == null) {
                    instance = new RetrofitClinetImpl();
                }
                return instance;
            }
        }
        return instance;
    }

    private static void initRetrofitSet(Context context) {
        mContext = context;
        url = "http://uniapi.cheyipai.com/";
        isLoading = true;
        isParseStateCode = false;
    }

    /**
     * set header
     *
     * @return
     */
    private Map<String, String> setHeader() {
        Map<String, String> header = new HashMap<>();
//        header.putAll(HttpData.getHeader());
//        header.putAll(new HttpParams().initHttpHeader());
        return header;
    }

    /**
     * common parameter
     *
     * @return
     */
    private Map<String, String> setCommonParameter() {
        Map<String, String> map = new HashMap<String, String>();
//        map.put(CYPApplication.getAppContext().getString(R.string.urlargs_businessid), GlobalConfigHelper.getInstance().getUserInfo() != null ? GlobalConfigHelper.getInstance().getUserInfo().getBusinessId() : "");
//        map.put(CYPApplication.getAppContext().getString(R.string.urlargs_clientversion), AppManager.getInstance(CYPApplication.getAppContext()).getVersion() + "");
//        map.put(CYPApplication.getAppContext().getString(R.string.urlargs_onlineid), GlobalConfigHelper.getInstance().getUserInfo() != null ? GlobalConfigHelper.getInstance().getUserInfo().getOnlineId() : "");
//        map.put(CYPApplication.getAppContext().getString(R.string.urlargs_memberCode), GlobalConfigHelper.getInstance().getUserInfo() != null ? (GlobalConfigHelper.getInstance().getUserInfo().getUserMemberCode() != null ? GlobalConfigHelper.getInstance().getUserInfo().getUserMemberCode() : "") : "");
//        map.put(CYPApplication.getAppContext().getString(R.string.urlargs_userId), GlobalConfigHelper.getInstance().getUserInfo() != null ? (GlobalConfigHelper.getInstance().getUserInfo().getBusId() != null ? GlobalConfigHelper.getInstance().getUserInfo().getBusId() : "") : "");
//        map.put(CYPApplication.getAppContext().getString(R.string.urlargs_sessionId), GlobalConfigHelper.getInstance().getUserInfo() != null ? (GlobalConfigHelper.getInstance().getUserInfo().getOnlineId() != null ? GlobalConfigHelper.getInstance().getUserInfo().getOnlineId() : "") : "");
        map.put("fromType", "android");
        String ip = "";
        if (NetWorkUtils.isNetWorkConnected(mContext)) {
            if (NetWorkUtils.isWifi(mContext)) {
//                ip = NetWorkUtils.getWifiIpAddress(mContext);
            } else {
//                ip = NetWorkUtils.getLocalIpAddress();
            }
        }
//        map.put("ip", ip);
        map.put("clienttype", "0");
        return map;
    }

    /**
     * Whether to display the progress bar
     *
     * @param isloading
     */
    public RetrofitClinetImpl setRetrofitLoading(boolean isloading) {
        this.isLoading = isloading;
        return this;
    }

    /**
     * Whether to display the state code
     *
     * @param isParseStateCode
     */
    public RetrofitClinetImpl setRetrofitParseStateCode(boolean isParseStateCode) {
        this.isParseStateCode = isParseStateCode;
        return this;
    }

    /**
     * @param url
     */
    public RetrofitClinetImpl setRetrofitBaseURL(String url) {
        this.url = url;
        return this;
    }

    public RetrofitClient newRetrofitClient() {
        Map<String, String> headerMap = setHeader();
        Map<String, String> commonMap = setCommonParameter();

        RetrofitClient mRetrofitClient = new RetrofitClient.Builder(mContext)
                .baseUrl(url)
                .addHeader(headerMap)
                .addCommonParameters(commonMap)
                .addLoading(isLoading)
                .addParseStateCode(isParseStateCode)
                .build();
        mRetrofitClient.setRetrofitCallBackStatusCode(new ICallBackStatusCode() {
            @Override
            public void getCallBackStatusCode(BaseRetrofitCallBackResponse coreBaseRetrofitCallBackResponse) {
                new BaseRetrofitCallBackVM<BaseRetrofitCallBackResponse>(mContext).onResponse(coreBaseRetrofitCallBackResponse);
            }
        });
        mRetrofitClient.setRetrofitCallBackResultCode(new ICallBackResultCode() {
            @Override
            public void getCallBackResultCode(String s, String s1) {
                Log.i(TAG, "getCallBackResultCode: " + s + "--" + s1);
            }
        });
        return mRetrofitClient;
    }
}
