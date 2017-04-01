package com.example.administrator.superandroid.util;

import android.content.Context;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by wangxiaosan on 2017/3/29.
 */
public class ConfigUtil {

    public static String getProperties(Context c, String s) {
        Properties properties = new Properties();
        try {
            properties.load(c.getAssets().open("application.properties"));
            return properties.getProperty(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getValueByKey(Context c, String s){
       return getProperties(c,s);
    }
}
