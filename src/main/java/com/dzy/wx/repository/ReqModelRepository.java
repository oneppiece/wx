package com.dzy.wx.repository;

import com.dzy.wx.entity.message.req.ReqModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/28.
 * User:Administrator
 * Date:2016/12/28
 * Time:9:55
 */
@Repository
public interface ReqModelRepository extends JpaRepository<ReqModel, Integer> {
}
