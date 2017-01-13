package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * 音乐消息
 *
 * @author liufeng
 * @date 2013-05-19
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"MsgId", "ThumbMediaId"})
public class MusicResponse extends RespMessage {
    public MusicResponse(String toUserName, String fromUserName, Long createTime, String msgType, Set<Music> music) {
        super(toUserName, fromUserName, createTime, msgType);
        this.music = music;
    }
    @JacksonXmlElementWrapper(localName = "Music", useWrapping = false)//是否显示List的根标签 false:不显示
    @JacksonXmlProperty(localName = "Music")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Music> music;

    public Set<Music> getMusic() {
        return music;
    }

    public void setMusic(Set<Music> music) {
        this.music = music;
    }


}