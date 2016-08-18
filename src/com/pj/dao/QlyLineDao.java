/**
 *柏红春
 *2014-11-11 10:24:04
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyLine;

@SuppressWarnings("serial")
public class QlyLineDao extends DaoBase {
	private static QlyLineDao dao;
	static {
		dao = new QlyLineDao();
	}

	public final synchronized static QlyLineDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
