package com.xa3ti.business.service.impl;

import com.xa3ti.base.util.DynamicSpecifications;
import com.xa3ti.base.util.SearchFilter;
import com.xa3ti.business.entity.Article;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.repository.ResourceRepository;
import com.xa3ti.business.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
	@Autowired
    ResourceRepository dao;

	public void save(Resource entity) {
		dao.save(entity);
	}

	public void deleteById(int resourceId) {
		 dao.delete(resourceId);
	}

	public void update(Resource entity) {
		dao.save(entity);
	}
	
	public void saveOrUpdate(Resource entity) {
		dao.save(entity);
	}

	public Resource queryById(int resourceId) {
		return dao.findOne(resourceId);
	}

	public List<Resource> queryAll(int begin, int count) {
        Pageable pageable = new PageRequest(begin,count);
        Page page = dao.findAll(pageable);
        return page.getContent();
	}

	public Long queryCount() {
		return dao.queryCount();
	}

    public Page<Resource> queryAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public List<Article> queryArticleByResourceId(int resourceId) {
		return dao.queryArticleByResourceId(resourceId);
	}
    /**
     * 检查名称是否存在
     */
	public boolean checkNameExist(String name) {
		Map<String,Object> filterParams = new HashMap<String,Object>();
		filterParams.put("EQ_name", name);
		Map<String,SearchFilter> filters = SearchFilter.parse(filterParams);
		Pageable pageable = new PageRequest(0,10);
		Page<Resource> data = dao.findAll(DynamicSpecifications.bySearchFilter(filters.values(), Resource.class),pageable);
		return data.getContent().size()>0;
	}
}