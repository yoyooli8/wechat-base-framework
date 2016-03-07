package com.xa3ti.business.controller;

import com.google.gson.Gson;
import com.xa3ti.base.util.WebUitl;
import com.xa3ti.base.util.XaDbStatus;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Award;
import com.xa3ti.business.entity.PlateGame;
import com.xa3ti.business.entity.PrizeLog;
import com.xa3ti.business.entity.Winner;
import com.xa3ti.business.entity.wxPOJO.WeiXinObject;
import com.xa3ti.business.service.GameService;
import com.xa3ti.business.service.IAwardService;
import com.xa3ti.business.service.IPrizeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class GameController {
	@Autowired
    GameService service;
    @Autowired
    IAwardService awardService;
    @Autowired
    IPrizeLogService prizeLogService;

	
	/**
	 * 大转盘角度和中奖
	 */
	@RequestMapping(value="/web/zhuanpan/process")
	public void process(HttpServletRequest request, HttpServletResponse response) {
		try {
			String openId = request.getParameter("openId");
            String name = request.getParameter("name");

			PlateGame pg = service.getPlateGame(openId);
			
			//在session中放置角度信息，返回时验证openId和角度是否吻合。中奖者才会放置验证信息。
			if(pg.isWinner()) {
				request.getSession().setAttribute(openId, String.valueOf(pg.getAngle()));
			}
			Gson g = new Gson();
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(g.toJson(pg));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			request.setAttribute("error", "活动信息不存在，请稍后再试！");
		}
	}
	
	/**
	 * 查看活动奖品信息
	 */
	@RequestMapping(value="/web/zhuanpan/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/game/zhuanpan");
		try {
			String openId = request.getParameter("openId");
			String name = request.getParameter("name");
            System.out.println("/web/zhuanpan/index   ===== openId= "+openId+"  name =="+name);
//            WeiXinObject weiXinObject = request.get("weiXinObject");
			request.setAttribute("openId", openId);
			request.setAttribute("name", name);

			//再此使用
			String reload = request.getParameter("reload");
			if(reload == null) {
				request.setAttribute("prompt", "大转盘游戏。祝您好运哦！");
			}
			
			PlateGame pg = service.getPlateGame("");
			request.setAttribute("pg", pg);
			
			//实时中奖信息
//			List<Object[]> ws = service.queryAwards(Integer.valueOf(actId));
            Pageable pageable = WebUitl.buildPageRequest(1,20,"");
			List<PrizeLog> ws = prizeLogService.getAll(pageable).getContent();
			request.setAttribute("ws", ws);
		} catch (Exception e) {
			request.setAttribute("error", "活动信息不存在，请稍后再试！");
		}
		
		return mv;
	}
	
	/**
	 * 验证并保存中奖者信息。同时减去相应积分。
	 * 中奖者会验证session中的角度信息，非中奖者不验证。
	 */
	@RequestMapping(value="/web/zhuanpan/winner")
	public void winner(HttpServletRequest request, HttpServletResponse response) {
		try {
			String openId = request.getParameter("openId");
			String name = request.getParameter("name");
			String winner = request.getParameter("winner");
            System.out.println(winner+"winnerwinnerwinner   ===");
            //中奖者验证中奖信息
			if("true".equals(winner)) {
				//转盘转动角度
				String angle = request.getParameter("angle");
				String awaId = request.getParameter("awaId");
				
				String val = (String) request.getSession().getAttribute(openId);
				request.getSession().removeAttribute(openId);
				
				//验证通过则处理
				if(val.equals(angle)) {
                    PrizeLog prizeLog= new PrizeLog();
					prizeLog.setPrizeId(Integer.valueOf(awaId));
                    Award award = awardService.findOne(Integer.valueOf(awaId));
                    prizeLog.setPrizeName(award.getAwaName());
                    prizeLog.setAwaRank(award.getAwaRank());
                    prizeLog.setStatus(XaDbStatus.DB_STATUS_NOMAL);
                    prizeLog.setTime(XaUtil.getToDayStr());
                    prizeLog.setUser_openid(openId);
                    prizeLog.setUser_nickname(name);
					service.winnerProcess(prizeLog);
				}
			}else {
				service.partProcess(openId);
                System.out.println("errorerrorerrorerrorerror  errorerror ");
            }
		} catch (Exception e) {
			request.setAttribute("error", "读取活动信息失败，请稍后再试！");
		}
	}
	

	
	/**
	 * 规则
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/web/zhuanpan/rule")
	public ModelAndView rule(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("openId", request.getParameter("openId"));
		return new ModelAndView("/web/game/guize");
	}
}
