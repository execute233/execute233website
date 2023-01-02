package com.execute233.web.servlet.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Background2 extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpGet get = new HttpGet();
        try {
            URIBuilder builder = new URIBuilder("https://myhkw.cn/open/img/acg");
            builder.setParameter("key","33f7a0e1ca6741d298fea3fb1119c9fe");
            builder.setParameter("type","json");
            get.setURI(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClients.createDefault();
        HttpResponse weatherRes = client.execute(get);
        InputStream inputStream = weatherRes.getEntity().getContent();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        JSONObject object = JSONObject.parseObject(json);
        String url = object.getString("img");

        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(url);
    }
}