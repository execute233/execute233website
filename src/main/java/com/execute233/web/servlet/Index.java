package com.execute233.web.servlet;

import com.execute233.web.Res;
import com.execute233.web.util.WebFileReader;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
/**
    it created by execute233

  ......................我佛慈悲......................
                        _oo0oo_
                       o8888888o
                       88" . "88
                       (| -_- |)
                       0\  =  /0
                     ___/`---'\___
                   .' \\|     |// '.
                  / \\|||  :  |||// \
                 / _||||| -卍-|||||- \
                |   | \\\  -  /// |   |
                | \_|  ''\---/''  |_/ |
                \  .-\__  '-'  ___/-. /
              ___'. .'  /--.--\  `. .'___
           ."" '<  `.___\_<|>_/___.' >' "".
          | | :  `- \`.;`\ _ /`;.`/ - ` : | |
          \  \ `_.   \_ __\ /__ _/   .-` /  /
      =====`-.____`.___ \_____/___.-`___.-'=====
                        `=---='

 ..................佛祖保佑 ,永无BUG...................
 **/
public class Index extends HttpServlet {
    static Logger logger = Logger.getLogger("web");
    final static String filePath = "/index.html";
    static byte[] data;
    @Override
    public void init() {
        logger.info("loading index...");
        WebFileReader.preload(getServletContext());
        data = WebFileReader.getFileData(filePath);
        Res.serverInit();
        logger.info("success to load index!");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        StringBuilder sb = new StringBuilder("receive a request:");
        sb.append(" IP:");
        sb.append(req.getRemoteAddr());
        sb.append(" host:");
        sb.append(req.getRemoteHost());
        sb.append(" port:");
        sb.append(req.getRemotePort());
        logger.info(sb);
        res.getOutputStream().write(data);
    }
}
