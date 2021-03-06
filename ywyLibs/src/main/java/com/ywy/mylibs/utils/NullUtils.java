package com.ywy.mylibs.utils;

/**
 * @author shaoshuai.
 * @PackageName com.ywy.mylibs.utils
 * @date 2016-09-28 17:04
 * @description null check
 */
public class NullUtils {
    public static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
