package com.huabiao.aoiin.retrofit;

import android.content.Context;
import android.text.TextUtils;

import com.huabiao.aoiin.constant.FlagBase;
import com.ywy.mylibs.listener.BaseRetrofitCallBackResponse;

/**
 * @author shaoshuai.
 * @PackageName com.huabiao.aoiin.retrofit
 * @date 2016-09-26 11:33
 * @description retrofit model
 */
public class BaseRetrofitCallBackVM<T extends BaseRetrofitCallBackResponse> {

    private Context mContext;

    public BaseRetrofitCallBackVM(Context context) {
        this.mContext = context;
    }

    public void onResponse(T response) {

        String resultCode = response.getResultCode();

        if (!TextUtils.isEmpty(resultCode)) {

            if (resultCode.equals(FlagBase.CALL_BACK_SUCCESS)) {
                //成功
                //DialogUtils.showToast(mContext, "" + response.getResultCode().toString());
            } else if (resultCode.equals(FlagBase.CALL_BACK_FAILED)) {
                //失败
            }
        }
    }
}
