/**
 *柏红春
 *2014-11-11 13:41:50
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyTravel;

@SuppressWarnings("serial")
public class QlyTravelDao extends DaoBase {
	private static QlyTravelDao dao;
	static {
		dao = new QlyTravelDao();
	}

	public final synchronized static QlyTravelDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
