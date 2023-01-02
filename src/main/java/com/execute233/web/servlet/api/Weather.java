package com.execute233.web.servlet.api;

import com.alibaba.fastjson2.JSONObject;
import com.execute233.web.util.QuicklyHttp;
import lombok.Data;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Weather extends HttpServlet {
    /**
     * 天气API的url
     **/
    public static final String url = "https://v0.yiketianqi.com/api";
    public static final String appid = "";
    public static final String appSecret = "";
    public static final String version = "v61";

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        String json = QuicklyHttp.create().url(url).addParameter("appid",appid)
                .addParameter("appsecret", appSecret)
                .addParameter("version",version)
                .addParameter("ip", req.getRemoteAddr())
                .execute().responseBody();
        JSONObject object = JSONObject.parseObject(json);
        WeatherJSON weatherJSON = new WeatherJSON(object.getString("city"), object.getString("wea"), object.getString("tem2"), object.getString("tem1"));
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(JSONObject.toJSONString(weatherJSON));
    }
}
@Data
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