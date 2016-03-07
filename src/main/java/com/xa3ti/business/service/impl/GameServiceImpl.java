package com.xa3ti.business.service.impl;

import com.google.gson.Gson;
import com.sun.javafx.fxml.expression.Expression;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.commons.HttpsUtils;
import com.xa3ti.business.commons.TokenUtil;
import com.xa3ti.business.entity.Award;
import com.xa3ti.business.entity.PlateGame;
import com.xa3ti.business.entity.PrizeLog;
import com.xa3ti.business.entity.Winner;
import com.xa3ti.business.entity.wxPOJO.InitiativeTextMessage;
import com.xa3ti.business.service.GameService;
import com.xa3ti.business.service.IAwardService;
import com.xa3ti.business.service.IPrizeLogService;
import com.xa3ti.business.util.HttpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    IAwardService awardService;
    @Autowired
    IPrizeLogService prizeLogService;

	@Override
	public PlateGame getPlateGame( String openId) {
		PlateGame pg = new PlateGame();
        int g1 = 0;
        int g2 = 0;
        int g3 = 0;

        Award aw1 = null;
        Award aw2 = null;
        Award aw3 = null;
        pg.setAwardInfo1("暂无奖品");
        pg.setAwardInfo2("暂无奖品");
        pg.setAwardInfo3("暂无奖品");
        List<Award> as =  awardService.findAll().getContent();
        if(as != null && as.size() > 0) {
            for (int i = 0; i < as.size(); i++) {
                if(as.get(i).getAwaRank() == 1) {
                    aw1 = as.get(i);
                    g1 = (int) (aw1.getProbability() * 100);
                }else if(as.get(i).getAwaRank() == 2) {
                    aw2 = as.get(i);
                    g2 = (int) (aw2.getProbability() * 100);
                }else if(as.get(i).getAwaRank() == 3) {
                    aw3 = as.get(i);
                    g3 = (int) (aw3.getProbability() * 100);
                }
            }

            g2 += g1;
            g3 += g2;
            //发布奖品信息
            if (XaUtil.isNotEmpty(aw1))
            pg.setAwardInfo1(aw1.getInfo());
            if (XaUtil.isNotEmpty(aw2))
            pg.setAwardInfo2(aw2.getInfo());
            if (XaUtil.isNotEmpty(aw3))
            pg.setAwardInfo3(aw3.getInfo());

            //设置中奖
            int myNum = new Random().nextInt(10000);
            if(myNum < g1) {//一等奖中奖
                if (aw1.getAwaNum()>1)
                {
                    pg.setWinner(true);
                    pg.setAwaId(aw1.getAwaId());
                    pg.setInfo(aw1.getInfo());
                    pg.setAngle(firstPrize());
                }else
                {
                    pg.setInfo("很遗憾，您没有中奖！");
                    pg.setWinner(false);
                    pg.setAngle(noPrize());
                }

            }else if(myNum < g2) {//二等奖中奖
                if (aw2.getAwaNum()>1)
                {
                    pg.setWinner(true);
                    pg.setAwaId(aw2.getAwaId());
                    pg.setInfo(aw2.getInfo());
                    pg.setAngle(twicePrize());

                }else
                {
                    pg.setInfo("很遗憾，您没有中奖！");
                    pg.setWinner(false);
                    pg.setAngle(noPrize());
                }
            }else if(myNum < g3) {//三等奖中奖
                if (aw3.getAwaNum()>1)
                {
                    pg.setWinner(true);
                    pg.setAwaId(aw3.getAwaId());
                    pg.setInfo(aw3.getInfo());
                    pg.setAngle(thirdPrize());

                }else
                {
                    pg.setInfo("很遗憾，您没有中奖！");
                    pg.setWinner(false);
                    pg.setAngle(noPrize());
                }
            }else {//未中奖
                pg.setInfo("很遗憾，您没有中奖！");
                pg.setWinner(false);
                pg.setAngle(noPrize());
            }
        }else
        {
            pg.setInfo("很遗憾，您没有中奖！");
            pg.setWinner(false);
            pg.setAngle(noPrize());
        }
        return pg;
	}

	/**
	 * 保存中奖者中奖信息
	 */
	@Override
	public void winnerProcess(PrizeLog win) {

        prizeLogService.save(win);
        Award award = awardService.findOne(win.getPrizeId());
        if (award.getAwaNum()>0)
        {
            int num = award.getAwaNum()-1;
            award.setAwaNum(num);
            awardService.save(award);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+TokenUtil.getToken();
        String str = "恭喜您获得紫峰广场";
        StringBuffer context = new StringBuffer();
        context.append(str);
        switch (win.getAwaRank())
        {
            case 1:
                context.append("【一等奖】");
                break;
            case 2:
                context.append("【二等奖】");
                break;
            case 3:
                context.append("【三等奖】");
                break;
        }
        context.append(",您获得的奖品是");
        String  prize = "【"+win.getPrizeName()+"】";
        context.append(prize);
        context.append("。请您在七天内回复确认！");
        InitiativeTextMessage initiativeTextMessage = new InitiativeTextMessage();
        initiativeTextMessage.setTouser(win.getUser_openid());
        initiativeTextMessage.addContent(context.toString());
        Gson gson = new Gson();
        try {
            Map<String,String> map = new HashMap<String, String>();
            System.out.println("TokenUtil.getToken()" + TokenUtil.getToken());
            System.out.println("getUser_openid " + win.getUser_openid());
            map.put("touser", win.getUser_openid());
            map.put("msgtype","text");
            map.put("content",context.toString());
            System.out.println(map);


            String info = HttpsUtils.https(url,gson.toJson(initiativeTextMessage), HttpsUtils.POST);
//            String info = HttpsUtil.sendPostRequest(url,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	@Override
	public void partProcess(String openId) {
//		cDao.addIntergral(openId, "大转盘游戏活动", -50);
	}
	
	/**
	 * 一等奖角度计算
	 * @return
	 */
	private int firstPrize() {
		return new Random().nextInt(10) + 169;
	}




	
	/**
	 * 二等奖角度计算
	 * @return
	 */
	private int twicePrize() {
		Random r = new Random();
		int i = r.nextInt(2);
		if(i == 0) {
			i = r.nextInt(16) + 115;
		}else {
			i = r.nextInt(15) + 341;
		}
		
		return i;
	}
	
	/**
	 * 三等奖角度计算
	 * @return
	 */
	private int thirdPrize() {
		Random r = new Random();
		int i = r.nextInt(3);
		if(i == 0) {
			i = r.nextInt(26) + 43;
		}else if(i == 1){
			i = r.nextInt(26) + 214;
		}else {
			i = r.nextInt(26) + 272;
		}
		
		return i;
	}
	
	/**
	 * 未中奖角度计算
	 * @return
	 */
	private int noPrize() {
		Random r = new Random();
		int i = r.nextInt(6);
		if(i == 0) {
			i = r.nextInt(41);
		}else if(i == 1){
			i = r.nextInt(36) + 75;
		}else if(i == 2){
			i = r.nextInt(26) + 140;
		}else if(i == 3){
			i = r.nextInt(31) + 180;
		}else if(i == 4){
			i = r.nextInt(26) + 245;
		}else {
			i = r.nextInt(32) + 305;
		}
		
		return i;
	}

	@Override
	public List<Object[]> queryAwards(int actId) {
//		return wDao.queryAwards(actId);
        return  null;
	}
}