package com.execute233.web.util;

import lombok.Getter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * 工具类，用于更便捷地请求API
 * **/
public class QuicklyHttp {

    private static final OkHttpClient DEFAULT_HTTP_CLIENT = new OkHttpClient();

    /**
     * URL Builder.
     * **/
    private HttpUrl.Builder urlBuilder;
    /**
     * 请求
     * **/
    @Getter
    private Request request;

    /**
     * 响应
     * **/
    @Getter
    private Response response;

    public static QuicklyHttp create() {
        return new QuicklyHttp();
    }
    private QuicklyHttp() {}
    /**
     * 设置请求URL
     * **/
    public QuicklyHttp url(String url) {
        urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        return this;
    }
    /**
     * 添加请求参数
     * **/
    public QuicklyHttp addParameter(String name, String value) {
        urlBuilder.addQueryParameter(name, value);
        return this;
    }
    /**
     * 构造request
     **/
    public void buildRequest() {
        request = new Request.Builder().url(urlBuilder.build()).build();
    }
    /**
     * 请求
     * **/
    public QuicklyHttp execute() {
        buildRequest();
        try {
            response = DEFAULT_HTTP_CLIENT.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    /**
     * 返回字符串获取响应体的内容
     * **/
    public String responseBody() {
        try {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
