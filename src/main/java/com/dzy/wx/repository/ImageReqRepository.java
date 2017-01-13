package com.dzy.wx.repository;

import com.dzy.wx.entity.message.req.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageReqRepository extends JpaRepository<Image, Integer> {
}
