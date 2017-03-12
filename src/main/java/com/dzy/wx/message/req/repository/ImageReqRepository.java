package com.dzy.wx.message.req.repository;

import com.dzy.wx.message.entity.req.msg.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageReqRepository extends JpaRepository<Image, Integer> {
}
