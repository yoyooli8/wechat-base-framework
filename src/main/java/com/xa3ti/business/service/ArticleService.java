package com.xa3ti.business.service;

import com.xa3ti.business.entity.Article;

import java.util.List;

public interface ArticleService {
	//默认方法
	public void save(Article entity);
	public void deleteById(int id);
	public void update(Article entity);
	public void saveOrUpdate(Article entity);
	public Article queryById(int id);
	public List<Article> queryAll(int begin, int count);
	public Long queryCount();
	
	public void updateSerourceId(int resourceId, int artId);
	
	//根据resourceId查询article
	public List<Article> queryByResourceId(int id);
}