package com.example.administrator.superandroid.dto;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class ListMainFmDto {
    private List<String> listName;
    private List<Drawable> listIcon;


    public List<String> getListName() {
        return listName;
    }

    public void setListName(List<String> listName) {
        this.listName = listName;
    }

    public List<Drawable> getListIcon() {
        return listIcon;
    }

    public void setListIcon(List<Drawable> listIcon) {
        this.listIcon = listIcon;
    }
}
