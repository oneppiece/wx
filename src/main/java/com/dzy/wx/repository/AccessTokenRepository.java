package com.dzy.wx.repository;

import com.dzy.wx.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:15:15
 */
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
}
