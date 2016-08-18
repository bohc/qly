/**
*柏红春
*2016-03-17 14:42:24
*这个类数据的操作类
*/
package com.pj.dao;

import com.pj.bean.QlyCustomviehcleprice;

@SuppressWarnings("serial")
public class QlyCustomviehclepriceDao extends DaoBase {
	private static QlyCustomviehclepriceDao dao;

	static {
		dao = new QlyCustomviehclepriceDao();
	}

	public final synchronized static QlyCustomviehclepriceDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
