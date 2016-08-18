/**
 *柏红春
 *2014-12-07 00:19:41
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyCitytocitypricecustom;

@SuppressWarnings("serial")
public class QlyCitytocitypricecustomDao extends DaoBase {
	private static QlyCitytocitypricecustomDao dao;
	static {
		dao = new QlyCitytocitypricecustomDao();
	}

	public final synchronized static QlyCitytocitypricecustomDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
