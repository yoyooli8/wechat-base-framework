package com.xa3ti.business.service.impl;

import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Menu;
import com.xa3ti.business.repository.MenuRepository;
import com.xa3ti.business.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
	@Autowired
    MenuRepository dao;

    @Transactional(readOnly = false)
	public Menu save(Menu entity) {
		return dao.save(entity);
	}
    @Transactional(readOnly = false)
	public void deleteById(int mId) {
		dao.delete(mId);
	}
    @Transactional(readOnly = false)
	public void update(Menu entity) {
		dao.save(entity);
	}
    @Transactional(readOnly = false)
	public void saveOrUpdate(Menu entity) {
		dao.save(entity);
	}

	public Menu queryById(int mId) {
		return dao.findOne(mId);
	}

	public List<Menu> queryAll(int begin, int count) {
        Pageable pageable = new PageRequest(begin,count);
        Page page = dao.findAll(pageable);
        return page.getContent();
	}

	public Long queryCount() {
		return dao.queryCount();
	}

	public List<Menu> queryMenuByPId(int pId) {
		return dao.queryMenuByParId(pId);
	}

	public Menu queryByKey(String key) {
		return dao.queryByKey(key);
	}

	public List<Menu> queryByFlag(int flag) {
		return dao.queryByFlag(flag);
	}
    @Transactional(readOnly = false)
	public void deleteByParId(int parId) {
		 dao.deleteByParId(parId);
	}

	public Long getPCount() {
		return dao.getPCount();
	}

	public Long getSCount(int parId) {
		return dao.getSCount(parId);
	}

    public boolean checkNameExist(String name) {
        Menu menu = dao.findByMenuName(name+"");
        if (XaUtil.isNotEmpty(menu))
        {
           return true;
        }
           return false;
    }
}