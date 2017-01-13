package com.dzy.wx.repository;

import com.dzy.wx.entity.media.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
