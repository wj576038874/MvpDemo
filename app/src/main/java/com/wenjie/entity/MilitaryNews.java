package com.wenjie.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.entity
 * Author: wenjie
 * Date: 2019-05-05 11:13
 * Description:
 */
public class MilitaryNews implements Serializable {

    private String url;
    private List<String> imageUrls;
    private String posterId;
    private String commentCount;
    private String content;
    private String title;
    private String shareCount;
    private String id;
    private String publishDateStr;
    private String posterScreenName;
    private long publishDate;
    private String tags;
    private String likeCount;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getPosterId() {
        return posterId;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPublishDateStr() {
        return publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public void setPosterScreenName(String posterScreenName) {
        this.posterScreenName = posterScreenName;
    }

    public String getPosterScreenName() {
        return posterScreenName;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

}
