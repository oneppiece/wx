package com.dzy.wx.repository;

import com.dzy.wx.entity.message.req.Text;
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