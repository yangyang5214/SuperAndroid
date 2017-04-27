package com.example.administrator.superandroid.util;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2016/10/13.
 * 隐藏软键盘
 */
public class HideSoftKeyboard {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
