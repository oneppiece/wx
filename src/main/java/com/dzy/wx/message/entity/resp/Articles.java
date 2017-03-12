package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "response_message_voices")
@Entity
public class Articles {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @JsonProperty("item")
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Item> item;


    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
