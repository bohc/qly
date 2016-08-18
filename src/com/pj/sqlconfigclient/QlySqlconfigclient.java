package com.pj.sqlconfigclient;

import java.io.Reader;
import org.apache.log4j.Logger;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class QlySqlconfigclient {
	private static Logger log = Logger.getLogger(QlySqlconfigclient.class);
	private static SqlMapClient sqlMap;
	static {
		try {
			String resource = "QlySqlmapconfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static SqlMapClient getSqlMapInstatce() {
		return sqlMap;
	}
}
