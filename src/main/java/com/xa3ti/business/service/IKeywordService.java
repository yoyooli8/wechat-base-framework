package com.xa3ti.business.service;

import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.entity.WXKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IKeywordService {
	
    public WXKeyword findByKey(String key);

    public WXKeyword findById(Long id);

    public WXKeyword findPublishByKey(String key);

    public Resource findResourceByKey(String key);

    public WXKeyword insertKeyword(WXKeyword keyword);

    public WXKeyword updateKeyword(WXKeyword keyword);

	public boolean updateStatus(Long id);

    public Page<WXKeyword> findAll(Pageable pageable);

    public boolean checkNameExist(String keyName);

    public boolean deleteKeyword(Long id);
}
