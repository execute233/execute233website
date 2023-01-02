package com.execute233.web.servlet.api;

import com.execute233.web.Res;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class Background extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(Res.getBackgroundURL());
    }
}