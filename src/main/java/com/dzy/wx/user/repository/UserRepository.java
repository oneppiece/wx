package com.dzy.wx.user.repository;

import com.dzy.wx.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByOpenId(String openid);
}
