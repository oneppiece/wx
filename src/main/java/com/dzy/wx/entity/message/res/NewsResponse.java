package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 文本消息
 *
 * @author liufeng
 * @date 2013-05-19
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsResponse extends RespMessage {
    public NewsResponse(String toUserName, String fromUserName, Long createTime, String msgType, Integer articleCount, Set<ArticleResponse> articleResponses) {
        super(toUserName, fromUserName, createTime, msgType);
        this.articleCount = articleCount;
        this.articleResponses = articleResponses;
    }

    // 图文消息个数，限制为10条以内
    @JsonProperty("ArticleCount")
    @Column(name = "article_count")
    private Integer articleCount;
    @JsonProperty("Articles")
    // 多条图文消息信息，默认第一个item为大图
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<ArticleResponse> articleResponses;

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