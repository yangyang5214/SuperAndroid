package com.example.administrator.superandroid.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by wangxiaosan on 2017/3/28.
 */
public class ImageUtil {

    //File转化成MultipartBody.Part
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> filePaths) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0;i < filePaths.size();i++){
            File file = new File(filePaths.get(i));
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    //url转bitmap
    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
}
