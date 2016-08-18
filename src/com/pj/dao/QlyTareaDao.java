/**
 *柏红春
 *2014-11-04 21:55:30
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyTarea;

@SuppressWarnings("serial")
public class QlyTareaDao extends DaoBase {
	private static QlyTareaDao dao;
	static {
		dao = new QlyTareaDao();
	}

	public final synchronized static QlyTareaDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
