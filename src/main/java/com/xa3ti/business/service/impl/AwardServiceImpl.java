package com.xa3ti.business.service.impl;

import com.xa3ti.base.util.BeanMapper;
import com.xa3ti.base.util.WebUitl;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.entity.Award;
import com.xa3ti.business.repository.AdminRepository;
import com.xa3ti.business.repository.AwardRepository;
import com.xa3ti.business.service.AdminService;
import com.xa3ti.business.service.IAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("IAwardService")
@Transactional(readOnly = true)
public class AwardServiceImpl implements IAwardService {
    @Autowired
    AwardRepository awardRepository;


    @Override
    public Page<Award> findAll() {
        Pageable pageable = WebUitl.buildPageRequest(1,10, "");
        return awardRepository.findAll(pageable);
    }

    @Override
    public Page<Award> findAll(Pageable pageable) {
        return awardRepository.findAll(pageable);
    }

    @Override
    public Award save(Award award) {
        if (XaUtil.isEmpty(award))return null;
        if (XaUtil.isNotEmpty(award.getAwaId()))
        {
            return update(award);
        }
        Award db = awardRepository.findByAwaRank(award.getAwaRank());
        if (XaUtil.isNotEmpty(db))
        {
            if (db.getAwaRank()==award.getAwaRank())return null;
        }
        return awardRepository.save(award);
    }

    @Override
    public Award update(Award award) {
        return awardRepository.save(award);
    }

    @Override
    public Award findOne(Integer id) {
        if (XaUtil.isEmpty(id))return null;
        return awardRepository.findOne(id);
    }
}
