package com.dzy.wx.repository;

import com.dzy.wx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByOpenId(String openid);
}
