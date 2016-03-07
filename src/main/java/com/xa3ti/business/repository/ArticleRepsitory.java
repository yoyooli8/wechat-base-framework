package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepsitory extends
		PagingAndSortingRepository<Article, Integer>,
		JpaSpecificationExecutor<Article> {
    @Query("select count(*) from Article")
    public Long queryCount();
    @Query("update Article a set a.resourceId=?1 where a.artId=?2")
    public void updateRerourceId(int resourceId, int artId);
    
    @Query("select a from Article a where a.resourceId=?1")
    public List<Article> queryByResourceId(int resourceId);
}
