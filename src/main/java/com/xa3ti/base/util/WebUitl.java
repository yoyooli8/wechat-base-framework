package com.xa3ti.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @ClassName: WebUitl
 * @Description: Web处理常用方法
 * @author 曹文波
 * @date 2014年2月24日 上午17:00:06
 * 
 */

public class WebUitl {
	/**
	 * 构造分页参数（排序）.
	 * 
	 * @param pageNumber
	 *            the page number
	 * @param sort
	 *            the sort
	 * @return the page request
	 */
	public static PageRequest buildPageRequest(int pageNumber, String sort) {
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		if (XaUtil.isNotEmpty(sort)) {
			PageSort[] pageSorts = JSON.parseObject(sort, PageSort[].class);
			for (PageSort pageSort : pageSorts) {
				orders.add(new Order(Sort.Direction.valueOf(pageSort
						.getDirection()), pageSort.getProperty()));
			}
			Sort sorts = new Sort(orders);
			return new PageRequest(pageNumber - 1, 1, sorts);
		} else {
			return new PageRequest(pageNumber - 1, 1);
		}
	}

	/**
	 * 构造分页参数
	 * 
	 * @param pago页
	 * @param pageSize
	 *            页面大小(默认为10条)
	 * @param sort
	 *            排序字段
	 * @return
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pageSize,
			String sort) {
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		if (XaUtil.isNotEmpty(sort)) {
			PageSort[] pageSorts = JSON.parseObject(sort, PageSort[].class);
			for (PageSort pageSort : pageSorts) {
				orders.add(new Order(Sort.Direction.valueOf(pageSort
						.getDirection()), pageSort.getProperty()));
			}
			Sort sorts = new Sort(orders);
			return new PageRequest(pageNumber - 1, pageSize, sorts);
		} else {
			return new PageRequest(pageNumber - 1, pageSize);
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getParametersStartingWith(
			String jsonFilter, String prefix) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		if (XaUtil.isNotEmpty(jsonFilter) && XaUtil.isNotEmpty(prefix)) {
			Map<String, String> map = JSON.parseObject(jsonFilter,
					HashMap.class);
			for (String key : map.keySet()) {
				if (key.startsWith(prefix)) {
					String unprefixed = key.substring(prefix.length());
					String value = map.get(key);
					searchParams.put(unprefixed, value);
				}
			}
		}
		return searchParams;
	}
}
