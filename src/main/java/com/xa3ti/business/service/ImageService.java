package com.xa3ti.business.service;

import com.xa3ti.business.entity.Image;

import java.util.List;


public interface ImageService {
	//默认方法
	public void save(Image entity);
	public void deleteById(int id);
	public void update(Image entity);
	public void saveOrUpdate(Image entity);
	public Image queryById(int id);
	public List<Image> queryAll(int begin, int count);
	public Long queryCount();
}