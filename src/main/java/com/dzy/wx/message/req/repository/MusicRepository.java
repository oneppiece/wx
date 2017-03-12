package com.dzy.wx.message.req.repository;

import com.dzy.wx.message.entity.resp.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by 16440 on 2017/3/11.
 */
@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
    Music getOneByTitle(String title);

    @Query(value = "select * from music order by rand() limit 1", nativeQuery = true)
    Music findOneRandom();
}
