package com.emrekose.bakingapp.utils;

import retrofit2.HttpException;


public class HttpNotFound {

    public final static String SERVER_ERROR = "Unable to resolve host : No address associated with hostname";

    public static boolean isHttp404(Throwable t) {
        return t instanceof HttpException && ((HttpException) t).code() == 404;
    }
}
