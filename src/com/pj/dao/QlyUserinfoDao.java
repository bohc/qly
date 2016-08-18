/**
 *柏红春
 *2014-11-03 09:46:38
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyUserinfo;

@SuppressWarnings("serial")
public class QlyUserinfoDao extends DaoBase {
	private static QlyUserinfoDao dao;
	static {
		dao = new QlyUserinfoDao();
	}

	public final synchronized static QlyUserinfoDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
