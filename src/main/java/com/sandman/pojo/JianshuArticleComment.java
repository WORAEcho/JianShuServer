package com.sandman.pojo;

import java.util.Date;

public class JianshuArticleComment {
    private Integer id;

    private Integer parentId;

    private Integer articleId;

    private Integer userId;

    private Integer likeCount;

    private Integer status;

    private Date createTime;

    private String content;

    private Integer mainComment;

    private Integer quotedUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getMainComment() {
        return mainComment;
    }

    public void setMainComment(Integer mainComment) {
        this.mainComment = mainComment;
    }

    public Integer getQuotedUserId() {
        return quotedUserId;
    }

    public void setQuotedUserId(Integer quotedUserId) {
        this.quotedUserId = quotedUserId;
    }
}