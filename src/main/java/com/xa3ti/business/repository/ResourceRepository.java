package com.xa3ti.business.repository;


import com.xa3ti.business.entity.Article;
import com.xa3ti.business.entity.Resource;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {

    @Query("select count(*) from Resource")
    public Long queryCount();

    @Query("select a from Article a where a.resourceId=?1")
    public List<Article> queryArticleByResourceId(int resourceId);
}
