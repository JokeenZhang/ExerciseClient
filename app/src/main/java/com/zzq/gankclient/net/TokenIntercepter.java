package com.zzq.gankclient.net;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.zzq.gankclient.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class TokenIntercepter implements Interceptor {
    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.forName("UTF-8");

        String bodyString = buffer.clone().readString(charset);
        LogUtils.e("gank_net", request.url().url().toString());
        LogUtils.e("gank_net", bodyString);

        return response;
    }
}
