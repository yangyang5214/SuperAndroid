package com.example.administrator.superandroid.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class MovingDto {

    private String content;
    private String position;
    private String imageUrl;
    private String publishTime;

    private String userId;
    private String userName;
    private String avatarUrl;//头像

    private int movingType;

    private List<CommentDto> listComment = new ArrayList<>();

    public MovingDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getMovingType() {
        return movingType;
    }

    public void setMovingType(int movingType) {
        this.movingType = movingType;
    }

    public List<CommentDto> getListComment() {
        return listComment;
    }

    public void setListComment(List<CommentDto> listComment) {
        this.listComment = listComment;
    }
}
