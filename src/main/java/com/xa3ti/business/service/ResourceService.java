package com.xa3ti.business.service;

import com.xa3ti.business.entity.Article;
import com.xa3ti.business.entity.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ResourceService {
	//默认方法
	public void save(Resource entity);
	public void deleteById(int resourceId);
	public void update(Resource entity);
	public void saveOrUpdate(Resource entity);
	public Resource queryById(int resourceId);
	public List<Resource> queryAll(int begin, int count);
	public Long queryCount();
	public Page<Resource> queryAll(Pageable pageable);
	public List<Article> queryArticleByResourceId(int resourceId);
	public boolean checkNameExist(String name);
}