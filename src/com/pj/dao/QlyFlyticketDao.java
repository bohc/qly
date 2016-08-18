/**
 *柏红春
 *2015-08-09 17:08:17
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyFlyticket;

@SuppressWarnings("serial")
public class QlyFlyticketDao extends DaoBase {
	private static QlyFlyticketDao dao;
	static {
		dao = new QlyFlyticketDao();
	}

	public final synchronized static QlyFlyticketDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
