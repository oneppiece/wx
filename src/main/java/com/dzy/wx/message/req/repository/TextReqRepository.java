package com.dzy.wx.message.req.repository;

import com.dzy.wx.message.entity.req.msg.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/26.
 * User:Administrator
 * Date:2016/12/26
 * Time:16:17
 */
@Repository
public interface TextReqRepository extends JpaRepository<Text, Integer> {

}