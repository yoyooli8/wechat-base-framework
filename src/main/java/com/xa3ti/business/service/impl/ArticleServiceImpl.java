package com.xa3ti.business.service.impl;

import java.util.List;

import com.xa3ti.business.repository.ArticleRepsitory;
import com.xa3ti.business.entity.Article;
import com.xa3ti.business.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
    ArticleRepsitory dao;

	public void save(Article entity) {
		dao.save(entity);
	}

	public void deleteById(int id) {
		 dao.delete(id);
	}

	public void update(Article entity) {
		dao.save(entity);
	}
	
	public void saveOrUpdate(Article entity) {
		dao.save(entity);
	}

	public Article queryById(int id) {
		return dao.findOne(id);
	}

	public List<Article> queryAll(int begin, int count) {
        Pageable pageable = new PageRequest(begin,count);
        Page page = dao.findAll(pageable);
		return page.getContent();
	}

	public Long queryCount() {
		return dao.queryCount();
	}

	public void updateSerourceId(int resourceId, int artId) {
		dao.updateRerourceId(resourceId, artId);
	}

	public List<Article> queryByResourceId(int id) {
		return dao.queryByResourceId(id);
	}
}