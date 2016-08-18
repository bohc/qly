/**
 *柏红春
 *2014-10-24 10:48:39
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyLinetravel;

@SuppressWarnings("serial")
public class QlyLinetravelDao extends DaoBase {
	private static QlyLinetravelDao dao;
	static {
		dao = new QlyLinetravelDao();
	}

	public final synchronized static QlyLinetravelDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
