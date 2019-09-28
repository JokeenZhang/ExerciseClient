package com.zzq.gankclient.base;

import okhttp3.Request;

/**
 * 可以基于此，做扩展
 */
public class ErrorBean {
    private Request request;


    public ErrorBean(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
