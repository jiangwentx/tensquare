package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String>{
   //实体类中的和这里的findBy后的拼写一致
    public Page<Spit> findByParentid(String parentid,Pageable pageable);
}
