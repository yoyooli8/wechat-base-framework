package com.xa3ti.business.controller;

import com.xa3ti.base.util.WebUitl;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.entity.WXKeyword;
import com.xa3ti.business.service.IKeywordService;
import com.xa3ti.business.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 闯儿 on 14-4-28.
 */

@Controller
public class WeiXinKeywordController {
    @Autowired
    IKeywordService keywordService;
    @Autowired
    ResourceService resourceService;

    @RequestMapping("/3ti/keyword/index")
    public ModelAndView list(HttpServletRequest request,
                             @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,String sort,@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize)
    {
        ModelAndView modelAndView = new ModelAndView("keyword/keyword_list");
        Pageable pageable = WebUitl.buildPageRequest(pageNumber, pageSize, sort);
        Page<WXKeyword> page  = keywordService.findAll(pageable);
        modelAndView.addObject("page",page);
        modelAndView.addObject("pageNumber",pageNumber);
        return modelAndView;
    }

    @RequestMapping("/3ti/keyword/edit")
    public ModelAndView edit(@RequestParam(value = "flag", defaultValue = "1") Integer flag,@RequestParam(value="keywordId",defaultValue="-1") long keywordId)
    {
        ModelAndView modelAndView =new ModelAndView("keyword/keyword_edit");
        if (flag==2){
          WXKeyword keyword = keywordService.findById(keywordId);
          Resource resource = resourceService.queryById(keyword.getResId());
          modelAndView.addObject("keyword",keyword);
          modelAndView.addObject("res",resource);
        }
        modelAndView.addObject("flag",flag);
        return modelAndView;
    }

    @RequestMapping("/3ti/keyword/save")
    public ModelAndView add(WXKeyword keyword)
    {
        ModelAndView modelAndView =   new ModelAndView("redirect:index");
        boolean flag = false;
        WXKeyword dbKeyword  =  keywordService.insertKeyword(keyword);
        if (XaUtil.isNotEmpty(dbKeyword))flag =true;
        modelAndView.addObject("flag",flag);
        return modelAndView ;
    }


    @RequestMapping("/3ti/keyword/checkNameExist")
    @ResponseBody
    public String checkNameExist(HttpServletRequest request,String keyName)
    {
        String isExist="n";
        isExist=keywordService.checkNameExist(keyName)?"{\"error\":\"名称已存在\"}":"{\"ok\":\"可以使用\"}";
        return isExist;
    }


    @RequestMapping("/3ti/keyword/delete")
    public void delete(long id,HttpServletResponse httpServletResponse)
    {
        try {
            PrintWriter io = httpServletResponse.getWriter();
            io.print(keywordService.deleteKeyword(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/3ti/keyword/audit")
    public void audit(long id,HttpServletResponse httpServletResponse)
    {
        try {
            PrintWriter io = httpServletResponse.getWriter();
            io.print(keywordService.updateStatus(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/3ti/keyword/deleteAll")
    public ModelAndView deleteAll(HttpServletRequest request){
        String[] arrId = request.getParameterValues("deleteBox");
        for(int i=0;i<arrId.length;i++){
            keywordService.deleteKeyword(Long.valueOf(arrId[i]));
        }
        return new ModelAndView("redirect:index");
    }
}
