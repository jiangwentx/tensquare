package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-02-16 14:40
 **/

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }
    /**
     * 根据ID查询标签
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        label.setId( idWorker.nextId()+"" );//设置ID
        labelDao.save(label);
    }
    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }
    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    //模糊查询
                    Predicate predicate=cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    //类似于 where labelname like "%小明%"
                    list.add(predicate);
                }

                if(label.getState()!=null && !"".equals(label.getState())){
                    //模糊查询
                    Predicate predicate=cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }

                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return cb.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    //模糊查询
                    Predicate predicate=cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    //类似于 where labelname like "%小明%"
                    list.add(predicate);
                }

                if(label.getState()!=null && !"".equals(label.getState())){
                    //模糊查询
                    Predicate predicate=cb.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }

                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return cb.and(parr);
            }
        }, pageable);
    }
}

