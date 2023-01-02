package com.execute233.web.servlet.api;

import com.execute233.web.Resource;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;


public class Background extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(Resource.getBackgroundURL());
    }
}