package com.example.administrator.superandroid.util;

import android.provider.MediaStore;

import java.io.File;
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
}
