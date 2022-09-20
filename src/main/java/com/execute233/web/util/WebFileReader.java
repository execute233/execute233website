package com.execute233.web.util;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

public class WebFileReader {
    static Logger logger = Logger.getLogger("web");
    static ServletContext context = null;

    public static void preload(ServletContext context) {
        WebFileReader.context = context;
    }
    public static InputStream getFileAsStream(String filePath) {
        if (context == null) {
            logger.error("WebFileReader isn't preload!");
            return null;
        }
        return context.getResourceAsStream(filePath);
    }
    public static byte[] getFileData(String filePath) {
        if (context == null) {
            logger.error("WebFileReader isn't preload!");
            return new byte[0];
        }
        InputStream in = null;
        byte[] data;
        try {
            in = context.getResourceAsStream(filePath);
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            logger.error("fail to load file:" + filePath + "! {}",e);
            return new byte[0];
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("fail to close stream! {}",e);
                }
            }
        }
        return data;
    }

}
