package com.execute233.web.servlet.api;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
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

public class Weather extends HttpServlet {
    public static final String appid = "";
    public static final String appsecret = "";
    public static final String version = "";

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String ip = req.getRemoteAddr();
        if (ip.equals("")||ip.startsWith("")) {
            ip = "";
        }
        HttpGet get = new HttpGet();
        try {
            URIBuilder builder = new URIBuilder("");
            builder.setParameter("appid",appid);
            builder.setParameter("appsecret",appsecret);
            builder.setParameter("version",version);
            builder.setParameter("ip",ip);
            get.setURI(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClients.createDefault();
        HttpResponse weatherRes = client.execute(get);
        InputStream inputStream = weatherRes.getEntity().getContent();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        JSONObject object = JSONObject.parseObject(json);
        WeatherJSON weatherJSON = new WeatherJSON(object.getString("city"), object.getString("wea"), object.getString("tem2"), object.getString("tem1"));
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(JSONObject.toJSONString(weatherJSON));
    }
}
@Getter
@Setter
class WeatherJSON {
    public WeatherJSON(String city, String weather, String temperature_night, String temperature_day) {
        this.city = city;
        this.weather = weather;
        this.temperature_night = temperature_night;
        this.temperature_day = temperature_day;
    }

    private String city;
    private String weather;
    private String temperature_night;
    private String temperature_day;
}