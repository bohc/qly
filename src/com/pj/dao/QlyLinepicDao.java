/**
 *柏红春
 *2014-11-28 00:28:45
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyLinepic;

@SuppressWarnings("serial")
public class QlyLinepicDao extends DaoBase {
	private static QlyLinepicDao dao;
	static {
		dao = new QlyLinepicDao();
	}

	public final synchronized static QlyLinepicDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
