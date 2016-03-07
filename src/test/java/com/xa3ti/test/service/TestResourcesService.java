package com.xa3ti.test.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSON;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.service.ResourceService;
import com.xa3ti.test.base.XaTestBase;

public class TestResourcesService extends XaTestBase{
	@Autowired
	ResourceService resourceService;
	
	@Test
	public void testSearch(){
//		Map<String, Object> filterParams = new HashMap<String, Object>();
//		filterParams.put("LIKE_resourceName", "awd");
//		Pageable pageable = new PageRequest(0, 10);
//		Page<Resource> page = resourceService.queryResource(filterParams, pageable);
//		System.out.println(JSON.toJSON(page));
	}
}
