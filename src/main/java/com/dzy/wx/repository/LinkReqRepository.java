package com.dzy.wx.repository;

import com.dzy.wx.entity.message.req.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkReqRepository extends JpaRepository<Link, Integer> {
}
