package com.dzy.wx.repository;

import com.dzy.wx.entity.OauthAccessToken;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:15:15
 */
@Repository
public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, Integer> {
    List<OauthAccessToken> findAllByOpenId(Sort id);

    OauthAccessToken findOneByOpenId(String openId);
}
