package com.xa3ti.business.commons;

import com.xa3ti.business.commons.bean.Page;

import javax.servlet.http.HttpServletRequest;



public class Paging {
	//默认页面数据数=10条
	private static final int pageCount = 10;

	public static Page process(HttpServletRequest request, long count) {
		Page p = new Page();
		
		p.setCount(count);

		//page
		try {
			String page = request.getParameter("page");
			p.setPage(Integer.valueOf(page));
		} catch (Exception e) {
			p.setPage(1);
		}
		
		//pageCount
		p.setPageCount(pageCount);
		
		//begin
		p.setBegin((p.getPage() - 1) * p.getPageCount());
		
		//pageSum
		int pageSum = (int) (count / p.getPageCount());
		if(count % p.getPageCount() > 0) {
			pageSum++;
		}
		p.setPageSum(pageSum);
		
		//start&end
		if(pageSum > 5) {
			if(p.getPage() > 3) {
				if(p.getPage() + 2 > pageSum) {
					p.setEnd(pageSum);
				}else {
					p.setEnd(p.getPage() + 2);
				}
				p.setStart(p.getEnd() - 5);
			}else {
				p.setStart(1);
				p.setEnd(5);
			}
		}else {
			p.setStart(1);
			p.setEnd(pageSum);
		}
		
		//next
		int next = p.getPage() + 1;
		if(next > pageSum) {
			next--;
		}
		p.setNext(next);
		
		//last
		int last = p.getPage() - 1;
		if(last < 1) {
			last++;
		}
		p.setLast(last);
		
		//将分页信息放入request中，可以在JSP中调用
		request.setAttribute("paging", p);
		
		return p;
	}
}
