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
    public static final String appid = "46882813";
    public static final String appsecret = "nuZZS4SB";
    public static final String version = "v61";

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String ip = req.getRemoteAddr();
        if (ip.equals("223.74.58.106")||ip.startsWith("192.168.0")) {
            ip = "223.74.58.106";
        }
        HttpGet get = new HttpGet();
        try {
            URIBuilder builder = new URIBuilder("https://v0.yiketianqi.com/api");
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
        res.setContentType("text/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(JSONObject.toJSONString(weatherJSON));
    }
}
@Getter
@Setter
class WeatherJSON {
    public WeatherJSON(String city, String wea, String tem_night, String tem_day) {
        this.city = city;
        this.wea = wea;
        this.tem_night = tem_night;
        this.tem_day = tem_day;
    }

    private String city;
    private String wea;
    private String tem_night;
    private String tem_day;
}
