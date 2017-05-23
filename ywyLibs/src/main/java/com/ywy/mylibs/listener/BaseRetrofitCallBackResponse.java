package com.ywy.mylibs.listener;

/**
 * @author shaoshuai.
 * @PackageName com.cyp.p.retrofit.call
 * @date 2016-09-28 17:04
 * @description return common
 */
public class BaseRetrofitCallBackResponse<T> {

    public String StatusCode;
    public String StateDescription;
    public String resultCode;
    public T Data;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getStateDescription() {
        return StateDescription;
    }

    public void setStateDescription(String stateDescription) {
        StateDescription = stateDescription;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

}
