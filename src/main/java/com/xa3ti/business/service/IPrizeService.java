package com.xa3ti.business.service;

import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.entity.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IPrizeService {
	
	public Page<Prize> getAll(Pageable pageable);

    public Prize findByType(Integer type);

    public Prize findPublishByType(Integer type);

    public boolean remove(Integer prizeId);


    public Prize update(Prize prize);

}
