package com.xa3ti.business.service;

import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.entity.Award;
import com.xa3ti.business.entity.WXKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IAwardService {
	
    public Page<Award> findAll();


    public Page<Award> findAll(Pageable pageable);

    public Award save(Award award);

    public Award update(Award award);

    public Award findOne(Integer id);

}
