package com.dzy.wx.media.repository;

import com.dzy.wx.media.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
