package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

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