package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-02-28 09:31
 **/
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleSearchDao;
    /**
     * 增加文章
     * @param article
     */
    public void add(Article article){
        articleSearchDao.save(article);
    }

    public Page<Article> findByTitleLike(String keywords, int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return articleSearchDao.findByTitleOrContentLike(keywords,keywords,pageRequest);
        //return articleSearchRepository.findByTitleOrContentLike(keywords,keywords, pageRequest);
    }
}

