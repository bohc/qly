/**
 *柏红春
 *2014-11-19 21:40:32
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyViewpic;

@SuppressWarnings("serial")
public class QlyViewpicDao extends DaoBase {
	private static QlyViewpicDao dao;
	static {
		dao = new QlyViewpicDao();
	}

	public final synchronized static QlyViewpicDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
