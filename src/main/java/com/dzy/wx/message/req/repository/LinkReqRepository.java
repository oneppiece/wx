package com.dzy.wx.message.req.repository;

import com.dzy.wx.message.entity.req.msg.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkReqRepository extends JpaRepository<Link, Integer> {
}
