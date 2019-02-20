package com.tensquare.question.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.question.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    //native = true,代表可以使用sql语句，而不只是jpql,分页
    @Query(value="select * from tb_problem,tb_pl where id=tb_pl.problemid and tb_pl.labelid=? ORDER BY tb_problem.replytime DESC",nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    @Query(value="select * from tb_problem,tb_pl where id=tb_pl.problemid and tb_pl.labelid=? ORDER BY tb_problem.reply DESC",nativeQuery = true)
	public Page<Problem> hotList(String labelid, Pageable pageable);

    @Query(value="select * from tb_problem,tb_pl where id=tb_pl.problemid and tb_pl.labelid=? AND reply=0 ORDER BY tb_problem.createtime DESC",nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);
}
