/**
 *柏红春
 *2014-11-10 12:09:09
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyView;

@SuppressWarnings("serial")
public class QlyViewDao extends DaoBase {
	private static QlyViewDao dao;
	static {
		dao = new QlyViewDao();
	}

	public final synchronized static QlyViewDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
