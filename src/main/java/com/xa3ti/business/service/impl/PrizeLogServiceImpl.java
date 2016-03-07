package com.xa3ti.business.service.impl;

import com.xa3ti.base.util.XaDbStatus;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Prize;
import com.xa3ti.business.entity.PrizeLog;
import com.xa3ti.business.repository.PrizeLogRepository;
import com.xa3ti.business.repository.PrizeRepository;
import com.xa3ti.business.service.IPrizeLogService;
import com.xa3ti.business.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 闯儿 on 14-6-24.
 */
@Service("IPrizeLogService")
@Transactional(readOnly = true)
public class PrizeLogServiceImpl implements IPrizeLogService{

    @Autowired
    PrizeLogRepository prizeLogRepository;


    @Override
    public Page<PrizeLog> getAll(Pageable pageable) {
        return prizeLogRepository.findByStatusNot(XaDbStatus.DB_STATUS_DELETE, pageable);
    }

    @Override
    public PrizeLog findById(Integer id) {
        return prizeLogRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public PrizeLog save(PrizeLog prizeLog) {
        return prizeLogRepository.save(prizeLog);
    }

    @Transactional(readOnly = false)
    public PrizeLog delete(Integer id) {
        PrizeLog db = prizeLogRepository.findOne(id);
        if (XaUtil.isNotEmpty(db))
        {
            db.setStatus(XaDbStatus.DB_STATUS_DELETE);
        }
        return prizeLogRepository.save(db);
    }

    @Override
    @Transactional(readOnly = false)
    public PrizeLog publish(Integer id) {
        if (XaUtil.isEmpty(id))return null;
       PrizeLog db =  prizeLogRepository.findOne(id);
        if (XaUtil.isNotEmpty(db))
        {
            db.setStatus(XaDbStatus.DB_STATUS_PUBLISH);
        }
        System.out.println(db);

        return prizeLogRepository.save(db);
    }
}
