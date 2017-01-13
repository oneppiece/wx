package com.dzy.wx.repository;

import com.dzy.wx.entity.WxServerIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:15:57
 */
@Repository
public interface WxServerIPRepository extends JpaRepository<WxServerIP, Integer> {
}
