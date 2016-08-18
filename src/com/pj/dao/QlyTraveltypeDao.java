/**
 *柏红春
 *2014-11-10 14:44:21
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyTraveltype;

@SuppressWarnings("serial")
public class QlyTraveltypeDao extends DaoBase {
	private static QlyTraveltypeDao dao;
	static {
		dao = new QlyTraveltypeDao();
	}

	public final synchronized static QlyTraveltypeDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
