/**
 *柏红春
 *2014-11-25 23:29:33
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyCitytocityprice;

@SuppressWarnings("serial")
public class QlyCitytocitypriceDao extends DaoBase {
	private static QlyCitytocitypriceDao dao;
	static {
		dao = new QlyCitytocitypriceDao();
	}

	public final synchronized static QlyCitytocitypriceDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
