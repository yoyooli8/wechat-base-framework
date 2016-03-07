package com.xa3ti.business.service;

import com.xa3ti.business.entity.Prize;
import com.xa3ti.business.entity.PrizeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPrizeLogService {
	
	public Page<PrizeLog> getAll(Pageable pageable);

    public PrizeLog findById(Integer id);

    public PrizeLog save(PrizeLog prizeLog);
//
//    public Prize findPublishByType(Integer type);
//
    public  PrizeLog delete(Integer id);
//
//
    public PrizeLog publish(Integer id);

}
