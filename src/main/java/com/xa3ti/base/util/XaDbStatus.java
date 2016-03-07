package com.xa3ti.base.util;

public interface XaDbStatus {

	/**正常*/
	int DB_STATUS_NOMAL=0;
	
	/**发布*/
	int DB_STATUS_PUBLISH=1;
	
	/**删除*/
	int DB_STATUS_DELETE=9;	
	
	/**注銷*/
	int DB_STATUS_INVALID=2;
	
	/**待审核*/
	int DB_STATUS_AUDIT=3;

	/**通过审核*/
	int DB_STATUS_PASS=4;
	
	/**未通过*/
	int DB_STATUS_NOT_PASS=5;
}
