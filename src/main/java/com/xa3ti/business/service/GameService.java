package com.xa3ti.business.service;


import com.xa3ti.business.entity.PlateGame;
import com.xa3ti.business.entity.PrizeLog;
import com.xa3ti.business.entity.Winner;

import java.util.List;

public interface GameService {
	/**
	 * 根据活动Id获取大转盘游戏中奖信息
	 * @return
	 */
	public PlateGame getPlateGame( String openId);
	
	/**
	 * 处理中奖人信息
	 * @param win
	 */
	public void winnerProcess(PrizeLog win);
	
	/**
	 * 处理未中奖人信息
	 * @param win
	 */
	public void partProcess(String openId);
	
	/**
	 * 读取活动的最新获奖信息
	 * @return
	 */
	public List<Object[]> queryAwards(int actId);
}