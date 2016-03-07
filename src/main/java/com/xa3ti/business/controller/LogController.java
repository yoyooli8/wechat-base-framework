
package com.xa3ti.business.controller;

import com.xa3ti.base.util.WebUitl;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Award;
import com.xa3ti.business.entity.PrizeLog;
import com.xa3ti.business.service.IAwardService;
import com.xa3ti.business.service.IPrizeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/log")
public class LogController {


    @Autowired
    private IPrizeLogService prizeLogService;
    
    /**
     *

     */
    @RequestMapping("/list")
   public ModelAndView list(HttpServletRequest request,
                            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,String sort,@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize)
    {
        ModelAndView modelAndView = new ModelAndView("prize/log");
        Pageable pageable = WebUitl.buildPageRequest(pageNumber, pageSize, sort);
        Page<PrizeLog> page= prizeLogService.getAll(pageable);
        modelAndView.addObject("page",page);
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView list(Integer id)
    {
        ModelAndView modelAndView = new ModelAndView("prize/log_edit");
        PrizeLog page = prizeLogService.findById(id);
        modelAndView.addObject("prizeLog",page);
        return modelAndView;
    }


    @RequestMapping(value="/publish")
    @ResponseBody
    public boolean updateIndex(Integer index)
    {
        try{
            return XaUtil.isNotEmpty(prizeLogService.publish(index));
        }catch(Exception e){
            return false;
        }
    }



    
    

}