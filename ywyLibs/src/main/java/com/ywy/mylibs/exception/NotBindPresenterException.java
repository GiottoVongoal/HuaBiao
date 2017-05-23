package com.ywy.mylibs.exception;

/**
 * ==============================================
 * <p>
 * 类名：
 * <p>
 * 作者：M-Liu
 * <p>
 * 时间：2017/3/30
 * <p>
 * ==============================================
 */

public class NotBindPresenterException extends RuntimeException {
    public NotBindPresenterException(){
        super("% 未绑定Presenter %");
    }
}
