package com.xa3ti.business.service;

import com.xa3ti.business.entity.Menu;

import java.util.List;


public interface MenuService {
	//默认方法
	public Menu save(Menu entity);
	public void deleteById(int mId);
	public void update(Menu entity);
	public void saveOrUpdate(Menu entity);
	public Menu queryById(int mId);
	public List<Menu> queryAll(int begin, int count);
	public Long queryCount();
	
	//rely接口
	public List<Menu> queryMenuByPId(int pId);
	//query接口
	public Menu queryByKey(String key);
	public List<Menu> queryByFlag(int flag);
	public void deleteByParId(int parId);
	
	public Long getPCount();
	public Long getSCount(int parId);

    public boolean checkNameExist(String name);
}