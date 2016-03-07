package com.xa3ti.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.alibaba.fastjson.JSON;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.test.base.XaTestWebBase;

public class TestResouceController extends XaTestWebBase {
	@Test
	public void testResouceSearch() {
//		Map<String, String> filterParams = new HashMap<String, String>();
//		filterParams.put("LIKE_resourceName", "a");
//		System.out.println(JSON.toJSONString(filterParams));
//		try {
//			ResultActions re = mockMvc.perform(post("/resource/search").param(
//					"filterParams", JSON.toJSONString(filterParams)).accept(
//					MediaType.ALL));
//			MvcResult mr = re.andReturn();
//			if (mr.getModelAndView().getModel().get("page") instanceof Page) {
//				Page page = (Page) mr.getModelAndView().getModel().get("page");
//				List<Resource> list = page.getContent();
//				for (Resource resource : list) {
//					System.out.println(resource.getContent() + "\r\n"
//							+ resource.getDescreption() + "\r\n"
//							+ resource.getResourceName() + "\r\n"
//							+ resource.getResourceId() + "\r\n"
//							+ resource.getType());
//				}
//			}
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
