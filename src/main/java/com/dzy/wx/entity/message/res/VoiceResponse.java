package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"voice", "MsgId"})//忽略的标签
@Entity
public class VoiceResponse extends RespMessage {
    public VoiceResponse(String toUserName, String fromUserName, Long createTime, String msgType, Set<Voice> voiceList) {
        super(toUserName, fromUserName, createTime, msgType);
        this.voiceList = voiceList;
    }

    //@JsonProperty(value = "Voice")
    //@JsonInclude()
    @JacksonXmlElementWrapper(localName = "Voices", useWrapping = false)//是否显示List的根标签 false:不显示
    @JacksonXmlProperty(localName = "Voice")
    //  @JacksonXmlText(value = false)
    // @JsonIgnore
    //@JacksonXmlProperty(localName = "Voices", isAttribute = false)
    //@JacksonXmlElementWrapper(useWrapping = false,localName = "Voice")
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Voice> voiceList;

    public Set<Voice> getVoice() {
        return voiceList;
    }

    public void setVoice(Set<Voice> voices) {
        this.voiceList = voices;
    }
}
