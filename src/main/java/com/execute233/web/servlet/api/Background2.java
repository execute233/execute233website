package com.execute233.web.servlet.api;

import com.alibaba.fastjson2.JSONObject;
import com.execute233.web.util.QuicklyHttp;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Background2 extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        String json = QuicklyHttp.create().url("https://myhkw.cn/open/img/acg")
                .addParameter("key","33f7a0e1ca6741d298fea3fb1119c9fe")
                .addParameter("type","json")
                .execute().responseBody();
        JSONObject object = JSONObject.parseObject(json);
        String url = object.getString("img");

        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(url);
    }
}