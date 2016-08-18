/**
 *柏红春
 *2014-12-09 00:42:38
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyRegulatepricecondition;

@SuppressWarnings("serial")
public class QlyRegulatepriceconditionDao extends DaoBase {
	private static QlyRegulatepriceconditionDao dao;
	static {
		dao = new QlyRegulatepriceconditionDao();
	}

	public final synchronized static QlyRegulatepriceconditionDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
