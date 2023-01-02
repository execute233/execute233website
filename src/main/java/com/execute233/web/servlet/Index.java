package com.execute233.web.servlet;

import com.execute233.web.Resource;
import com.execute233.web.util.WebFileReader;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;


public class Index extends HttpServlet {
    final static String filePath = "/index.html";
    static byte[] data;
    @Override
    public void init() {
        log("loading index...");
        WebFileReader.preload(getServletContext());
        Resource.serverInit();
        data = WebFileReader.getFileData(filePath);
        log("success to load index!");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        res.getOutputStream().write(data);
    }
}
