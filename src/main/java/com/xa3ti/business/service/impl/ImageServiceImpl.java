package com.xa3ti.business.service.impl;

import java.util.List;

import com.xa3ti.business.repository.ImageRepsitory;
import com.xa3ti.business.entity.Image;
import com.xa3ti.business.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ImageServiceImpl implements ImageService {
	@Autowired
    ImageRepsitory dao;

	public void save(Image entity) {
		dao.save(entity);
	}

	public void deleteById(int id) {
		 dao.delete(id);
	}

	public void update(Image entity) {
		dao.save(entity);
	}
	
	public void saveOrUpdate(Image entity) {
		dao.save(entity);
	}

	public Image queryById(int id) {
		return dao.findOne(id);
	}

	public List<Image> queryAll(int begin, int count) {
        Pageable pageable = new PageRequest(begin,count);
        Page page = dao.findAll(pageable);
        return page.getContent();
	}

	public Long queryCount() {
		return dao.queryCount();
	}
}