package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.entity.WXKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WXKeywordRepository extends
    PagingAndSortingRepository<WXKeyword,Long>,JpaSpecificationExecutor<WXKeyword>{

    @Query("select k from  WXKeyword k where k.wxKey = ?1 and k.status <>9")
    public WXKeyword finOneByWxKey(String wxKey);

//    public WXKeyword finByWxKeyAndStatus(String wxKey,int status);

    @Query("select r from Resource r where r.resourceId = (select k.resId from WXKeyword k where  k.wxKey = ?1 and k.status = 1)")
    public Resource finResourceByWxKey(String wxKey);

    public Page<WXKeyword> findByStatusNot(Integer status, Pageable pageable);

}
