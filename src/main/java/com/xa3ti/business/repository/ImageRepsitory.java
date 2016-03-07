package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Image;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImageRepsitory extends
		PagingAndSortingRepository<Image, Integer>,
		JpaSpecificationExecutor<Image> {
    @Query("select count(*) from Image")
    public Long queryCount();
}
