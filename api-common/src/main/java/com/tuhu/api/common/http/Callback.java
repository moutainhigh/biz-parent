package com.tuhu.api.common.http;

public interface Callback {
    /**
     * Call back method will be execute when the http request is returned.
     *
     * @param response the response of http request.
     */
    void execute(String response);

    /**
     * Callback the method when there is an exception when http connecting.
     *
     * @param exception
     */
    void exception(Exception exception);
}
