package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

/**
 * 图文消息
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse extends RespMessage {
    public NewsResponse(String toUserName, String fromUserName, Long createTime, String msgType, Integer articleCount, Set<ArticleResponse> articleResponses) {
        super(toUserName, fromUserName, createTime, msgType);
        this.articleCount = articleCount;
        this.articleResponses = articleResponses;
    }

    public NewsResponse(String toUserName, String fromUserName, Long createTime, String msgType) {
        super(toUserName, fromUserName, createTime, msgType);
    }

    // 图文消息个数，限制为10条以内
    @JsonProperty("ArticleCount")
    @Column(name = "article_count")
    private Integer articleCount;
    @JsonProperty("Articles")
    // 多条图文消息信息，默认第一个item为大图
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<ArticleResponse> articleResponses;

    public NewsResponse() {
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        articleCount = articleCount;
    }

    public Set<ArticleResponse> getArticleResponses() {
        return articleResponses;
    }

    public void setArticleResponses(Set<ArticleResponse> articleResponses) {
        articleResponses = articleResponses;
    }
}