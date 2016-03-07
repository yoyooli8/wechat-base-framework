
package com.xa3ti.business.controller;

import com.xa3ti.base.util.WebUitl;
import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.entity.Award;
import com.xa3ti.business.service.AdminService;
import com.xa3ti.business.service.IAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/award")
public class AwardController {

    /**
     * localhost:8080/xa3ti-web/sigmaUser/regist?openId=...
     *
     * @author yzuo
     */
    @Autowired
    private IAwardService awardService;
    
    /**
     *
     *@author yzuo
     */
    @RequestMapping("/list")
   public ModelAndView list(HttpServletRequest request,
                            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,String sort,@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize)
    {
        ModelAndView modelAndView = new ModelAndView("prize/award_list");
        Pageable pageable = WebUitl.buildPageRequest(pageNumber, pageSize, sort);
        Page<Award> page= awardService.findAll(pageable);
        modelAndView.addObject("page",page);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add()
    {
        ModelAndView modelAndView = new ModelAndView("prize/award_edit");
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value="id",defaultValue="") Integer id)
    {
        ModelAndView modelAndView = new ModelAndView("prize/award_edit");
        modelAndView.addObject("award",awardService.findOne(id));
        return modelAndView;
    }

    @RequestMapping("/save")
    public ModelAndView save(Award award)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:list");
        awardService.save(award);
        return modelAndView;
    }

    
    

}