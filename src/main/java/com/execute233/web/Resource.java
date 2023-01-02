package com.execute233.web;


import com.execute233.web.util.WebFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Resource {
    private static final List<String> backgroundURL = new ArrayList<>();

    //初始化服务
    public static void serverInit() {
        //background
        InputStream in = WebFileReader.getFileAsStream("/res/background.txt");
        BufferedReader reader = null;
        if (in != null) {
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String url = null;
                while (true) {
                    try {
                        url = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (url == null) {
                        break;
                    }
                    backgroundURL.add(url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    //获取随机图片URL
    public static String getBackgroundURL() {
        return backgroundURL.get(new Random().nextInt(backgroundURL.size()));
    }

}
