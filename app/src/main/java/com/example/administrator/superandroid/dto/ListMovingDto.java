package com.example.administrator.superandroid.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxiaosan on 2017/4/12.
 */
public class ListMovingDto {
    List<MovingDto> movingDtoList = new ArrayList<>();

    public List<MovingDto> getMovingDtoList() {
        return movingDtoList;
    }

    public void setMovingDtoList(List<MovingDto> movingDtoList) {
        this.movingDtoList = movingDtoList;
    }
}
