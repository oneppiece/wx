package com.dzy.wx.media.service;

import com.dzy.wx.media.entity.Media;
import com.dzy.wx.media.entity.MediaPage;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public interface MediaService {
    List<Media> findPage();

    List<Media> findAll();

    Media getFromWxServer(MediaPage mediaPage);
}
