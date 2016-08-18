package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.util.Config;

public class DB {

	String strDBDriver = "";
	String strConnStr = "";
	String strUser = "";
	String strPsaaword = "";

	public DB(String strDBName) {
		try {
			strDBDriver = Config.getValueByKey("jdbc."+strDBName+".driver","com.mysql.jdbc.Driver");
			strConnStr = Config.getValueByKey("jdbc."+strDBName+".url","jdbc:mysql://localhost:3306/"+strDBName);
			strUser = Config.getValueByKey("jdbc."+strDBName+".user", "root");
			strPsaaword = Config.getValueByKey("jdbc."+strDBName+".password", "");;

			Class.forName(strDBDriver);
		} catch (java.lang.ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 得到连接信息
	 * @return
	 */
	public Connection getConn() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(strConnStr, strUser,strPsaaword);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return connection;
	}

	/**
	 * 数据更新
	 */
	public void executeUpdate(Connection conn, String strSql) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(strSql);

			if (stmt != null) {
				stmt.close();// 关闭对象
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(Connection conn, String strSql) {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	public String executeGetFileStr(Connection conn, String strSql) {

		String reStr = "";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			if (rs.next()) {
				reStr = rs.getString(1);
			}

			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return reStr;
	}

	
	public int executeGetFileInt(Connection conn, String strSql) {

		int reInt = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			if (rs.next()) {
				reInt = rs.getInt(1);
			}
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return reInt;
	}
}
