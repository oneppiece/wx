package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleResponse extends RespMessage {
    public ArticleResponse(String toUserName, String fromUserName, Long createTime, String msgType, Articles articles, int articleCount) {
        super(toUserName, fromUserName, createTime, msgType);
        this.articles = articles;
        this.articleCount = articleCount;
    }

    @ManyToOne
    @JsonProperty("Articles")
    private Articles articles;
    @JsonProperty("ArticleCount")
    @Column(name = "article_count")
    private int articleCount;

    public ArticleResponse() {
    }


    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }


}