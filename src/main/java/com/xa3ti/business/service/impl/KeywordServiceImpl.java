package com.xa3ti.business.service.impl;

import com.xa3ti.base.util.BeanMapper;
import com.xa3ti.base.util.XaDbStatus;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.entity.WXKeyword;
import com.xa3ti.business.repository.WXKeywordRepository;
import com.xa3ti.business.service.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by 闯儿 on 14-4-25.
 */
@Service("IKeywordService")
public class KeywordServiceImpl implements IKeywordService {

    @Autowired
    WXKeywordRepository WXKeywordRepository;

    public WXKeyword findByKey(String key) {
        return    WXKeywordRepository.finOneByWxKey(key);
    }

    @Override
    public WXKeyword findById(Long id) {
        return WXKeywordRepository.findOne(id);
    }

    public WXKeyword findPublishByKey(String key) {
//        return WXKeywordRepository.finByWxKeyAndStatus(key, XaDbStatus.DB_STATUS_PUBLISH);
        return null;
    }

    @Override
    public Resource findResourceByKey(String key) {
        return WXKeywordRepository.finResourceByWxKey(key);
    }

    public WXKeyword insertKeyword(WXKeyword keyword) {
        keyword.setStatus(XaDbStatus.DB_STATUS_NOMAL);
        return WXKeywordRepository.save(keyword);
    }

    public WXKeyword updateKeyword(WXKeyword keyword) {
        if (XaUtil.isEmpty(keyword))return null;
        if (XaUtil.isEmpty(keyword.getId()))return null;
        WXKeyword dbKeyword = WXKeywordRepository.findOne(keyword.getId());
        if (XaUtil.isEmpty(dbKeyword))return null;
        BeanMapper.copy(keyword,dbKeyword);
        return WXKeywordRepository.save(dbKeyword);
    }

    public boolean updateStatus(Long id) {
        if (XaUtil.isEmpty(id))return false;
        WXKeyword keyword = WXKeywordRepository.findOne(id);
        if (XaUtil.isEmpty(keyword)) return false;
        if (keyword.getStatus()== XaDbStatus.DB_STATUS_PUBLISH)
        {
           keyword.setStatus(XaDbStatus.DB_STATUS_NOMAL);
            WXKeywordRepository.save(keyword);
            return true;
        }else if (keyword.getStatus()==XaDbStatus.DB_STATUS_NOMAL)
        {
            keyword.setStatus(XaDbStatus.DB_STATUS_PUBLISH);
            WXKeywordRepository.save(keyword);
            return true;
        }
        return false;
    }

    public Page<WXKeyword> findAll(Pageable pageable) {

        return WXKeywordRepository.findByStatusNot(XaDbStatus.DB_STATUS_DELETE,pageable);
//        return null;
    }

    @Override
    public boolean checkNameExist(String keyName) {
        return XaUtil.isNotEmpty(WXKeywordRepository.finOneByWxKey(keyName));
    }

    @Override
    public boolean deleteKeyword(Long id) {
      WXKeyword wxKeyword =   WXKeywordRepository.findOne(id);
      wxKeyword.setStatus(XaDbStatus.DB_STATUS_DELETE);
      return XaUtil.isNotEmpty(WXKeywordRepository.save(wxKeyword));
    }
}
