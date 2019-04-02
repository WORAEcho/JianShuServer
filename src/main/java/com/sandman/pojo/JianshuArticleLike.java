package com.sandman.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class JianshuArticleLike {
    private Integer id;

    private Integer articleId;

    private String likeState;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Date likeTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Date updateTime;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getLikeState() {
        return likeState;
    }

    public void setLikeState(String likeState) {
        this.likeState = likeState == null ? null : likeState.trim();
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}