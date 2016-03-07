package com.xa3ti.business.service.impl;

import com.xa3ti.business.entity.Prize;
import com.xa3ti.business.repository.PrizeRepository;
import com.xa3ti.business.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 闯儿 on 14-6-24.
 */
@Service("IPrizeService")
public class PrizeServiceImpl  implements IPrizeService{

    @Autowired
    PrizeRepository prizeRepository;

    @Override
    public Page<Prize> getAll(Pageable pageable) {
        return prizeRepository.findAll(pageable);
    }

    @Override
    public Prize findByType(Integer type) {
        return null;
    }

    @Override
    public Prize findPublishByType(Integer type) {
        return prizeRepository.findPublishByType(type);
    }

    @Override
    public boolean remove(Integer prizeId) {
        return false;
    }

    @Override
    public Prize update(Prize prize) {
        return null;
    }
}
