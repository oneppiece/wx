package com.dzy.wx.service;

import com.dzy.wx.entity.media.Media;
import com.dzy.wx.entity.media.MediaPage;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public interface MediaService {
    List<Media> findPage();

    List<Media> findAll();

    Media getFromWxServer(MediaPage mediaPage);
}
