package com.xa3ti.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xa3ti.business.commons.ParamHandler;
import com.xa3ti.business.entity.Article;
import com.xa3ti.business.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticlesController {
	@Autowired
    ArticleService service;

	/**
	 * 添加单图文页面
	 */
	@RequestMapping(value="/3ti/article/process")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/resource/article");
		
		try {
			String artId = request.getParameter("artId");
			String resourceId = request.getParameter("resourceId");
			if(artId != null && !"".equals(artId)) {
				Article art = service.queryById(Integer.valueOf(artId));
				
				resourceId = String.valueOf(art.getResourceId());
				request.setAttribute("art", art);
			}
			
			request.setAttribute("resourceId", resourceId);
			request.setAttribute("pro", request.getParameter("pro"));
		} catch (Exception e) {
		}
		
		return mv;
	}
	
	/**
	 * 添加图文资源
	 */
	@RequestMapping(value="/3ti/article/save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = false;
			Article a = ParamHandler.process(request, Article.class);
			if(a.getArtId() == 0) {
				flag = true;
			}
			
			service.saveOrUpdate(a);
			
			request.setAttribute("art", a);
			
			StringBuffer sb = new StringBuffer();
			
			//添加新数据
			if(flag) {
				sb.append("<script type=\"text/javascript\">parent.document.addITN(");
			}else {//修改数据
				sb.append("<script type=\"text/javascript\">parent.document.updateITN(");
			}
			sb.append(a.getArtId() + ",");
			sb.append("\"" + a.getTitle() + "\",");
			if(a.getDescription() != null) {
				sb.append("\"" + a.getDescription() + "\",");
			}else {
				sb.append("\"\",");
			}
			sb.append("\"" + a.getPicUrl() + "\"");
			sb.append(");</script>");
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(sb.toString());
			response.getWriter().flush();
		} catch (Exception e) {
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(value="/3ti/article/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/3ti/resource/process?resourceId=" + request.getParameter("resId"));
		
		try {
			String artId = request.getParameter("artId");
			if(artId != null && !"".equals(artId)) {
				service.deleteById(Integer.valueOf(artId));
			}
		} catch (Exception e) {
		}
		
		return mv;
	}
}
