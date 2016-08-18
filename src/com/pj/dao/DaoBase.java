package com.pj.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.base.BaseIni;
import com.base.ModelBase;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.pj.sqlconfigclient.QlySqlconfigclient;
import com.util.FileManager;
import com.util.page.PageControl;
import com.util.page.PageControlFactory;

@SuppressWarnings("unchecked")
public class DaoBase {
	public static SqlMapClient sqlMapClient, sqlMapQly;

	public DaoBase() {
		sqlMapQly = QlySqlconfigclient.getSqlMapInstatce();
	}

	/*
	 * 多增加一个对应的sqlmapbase 对应调用不同的SqlMapClient
	 */

	public DaoBase(SqlMapClient sqlMapBase) {
		this.sqlMapClient = sqlMapBase;
	}

	/**
	 * 
	 * @param 当前页
	 *            系统自动维护
	 * @param 实体类参数
	 * @param 执行sql
	 * @param 获取数据总数的sql
	 *            分页使用
	 * @return PageControl null:出错
	 */
	public PageControl getInfoList(String currentpage, ModelBase para, String sql, String sqlcount) {
		PageControl pg = null;
		try {
			pg = PageControlFactory.getPgList(sql,// 查询列表的SQL
					sqlcount, // 查询总行数的 SQL
					sqlMapClient, // 查询
					currentpage, // 当前页
					para // 参数
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pg;
	}

	/**
	 * 列表数据获取方法
	 * 
	 * @param 执行sql
	 * @return 返回List数据列表 null:出错
	 */
	public List getList(String sql) {
		List result = null;
		try {
			result = sqlMapClient.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 列表数据获取方法
	 * 
	 * @param 执行sql
	 * @param 字符串参数
	 * @return 返回List数据列表 null:出错
	 */
	public List getList(String sql, String para) {
		List result = null;
		try {
			result = sqlMapClient.queryForList(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 列表数据获取方法
	 * 
	 * @param 执行sql
	 * @param 字符串参数
	 * @return 返回List数据列表 null:出错
	 */
	public List getList(String sql, Object para, SqlMapClient sqlMapClient) {
		List result = null;
		try {
			result = sqlMapClient.queryForList(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 列表数据获取方法
	 * 
	 * @param 执行sql
	 * @param Map类型自定义参数
	 * @return 返回Map类型数据列表 null:出错
	 */
	public List<Map<String, Object>> getList(String sql, Map<String, Object> para) {
		List<Map<String, Object>> result = null;
		try {
			result = sqlMapClient.queryForList(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据获取方法
	 * 
	 * @param 执行sql
	 * @param 实体类参数
	 * @return 返回实体类数据列表 null:出错
	 */
	public List<ModelBase> getList(String sql, Object para) {
		List<ModelBase> result = null;
		try {
			result = sqlMapClient.queryForList(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据获取方法
	 * 
	 * @param 执行sql
	 * @return 返回Object null:出错
	 */
	public Object getInfo(String sql) {
		Object result = null;
		try {
			result = sqlMapClient.queryForObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据获取方法
	 * 
	 * @param 执行sql
	 * @param 字符串参数
	 * @return 返回Object null:出错
	 */
	public Object getInfo(String sql, String para) {
		Object result = null;
		try {
			result = sqlMapClient.queryForObject(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据获取方法
	 * 
	 * @param 执行sql
	 * @param Map类型自定义参数
	 * @return 返回Object null:出错
	 */
	public Object getInfo(String sql, Map<String, Object> para) {
		Object result = null;
		try {
			result = sqlMapClient.queryForObject(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据获取方法
	 * 
	 * @param 执行sql
	 * @param 实体类参数
	 *            多个参数的情况下使用
	 * @return 返回一个实体类 null:出错
	 */
	public Object getInfo(String sql, Object para) {
		Object result = null;
		try {
			result = sqlMapClient.queryForObject(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/***
	 * 数据插入方法
	 * 
	 * @param 执行的sql
	 * @param 字符串参数
	 * @return 返回Object null:出错
	 */
	public Object insert(String sql, String para) {
		Object result = null;
		try {
			result = sqlMapClient.insert(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据插入方法
	 * 
	 * @param 执行的sql
	 * @param Map类型自定义参数
	 * @return 返回Object null:出错
	 */
	public Object insert(String sql, Map<String, Object> para) {
		Object result = null;
		try {
			result = sqlMapClient.insert(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据插入方法
	 * 
	 * @param 执行的sql
	 * @param 实体类参数
	 * @return 返回Object null:出错
	 */
	public Object insert(String sql, Object para) {
		Object result = null;
		try {
			result = sqlMapClient.insert(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据更新方法
	 * 
	 * @param 执行的sql
	 * @return 返回受影响的条数 -1:更新失败
	 */
	public int update(String sql) {
		int result = -1;
		try {
			result = sqlMapClient.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据更新方法
	 * 
	 * @param 执行的sql
	 * @param 字符型参数
	 * @return 返回受影响的条数 -1:更新失败
	 */
	public int update(String sql, String para) {
		int result = -1;
		try {
			result = sqlMapClient.update(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据更新方法
	 * 
	 * @param 执行的sql
	 * @param Map类型自定义参数
	 * @return 返回受影响的条数 -1:更新失败
	 */
	public int update(String sql, Map<String, Object> para) {
		int result = -1;
		try {
			result = sqlMapClient.update(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据更新方法
	 * 
	 * @param 执行的sql
	 * @param 实体类参数
	 * @return 返回受影响的条数 -1:更新失败
	 */
	public int update(String sql, Object para) {
		int result = -1;
		try {
			result = sqlMapClient.update(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据删除方法
	 * 
	 * @param 执行的sql
	 * @return 返回受影响的条数 -1:删除失败
	 */
	public int delete(String sql) {
		int result = -1;
		try {
			result = sqlMapClient.delete(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据删除方法
	 * 
	 * @param 执行的sql
	 * @param 字符串参数
	 * @return 返回受影响的条数 -1:删除失败
	 */
	public int delete(String sql, String para) {
		int result = -1;
		try {
			result = sqlMapClient.delete(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据删除方法
	 * 
	 * @param 执行的sql
	 * @param Map类型自定义参数
	 * @return 返回受影响的条数 -1:删除失败
	 */
	public int delete(String sql, Map<String, Object> para) {
		int result = -1;
		try {
			result = sqlMapClient.delete(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 数据删除方法
	 * 
	 * @param 执行的sql
	 * @param 实体类参数
	 * @return 返回受影响的条数 -1:删除失败
	 */
	public int delete(String sql, Object para) {
		int result = -1;
		try {
			result = sqlMapClient.delete(sql, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 写日志文件记录
	public static void writeLogFile(String msg) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String path = BaseIni.getBasepath() + "/log/" + sdf.format(dt) + ".txt";
		FileManager.reWriteFile(path, msg);
	}

	// 写日志文件记录
	public static void writeBackFile(String msg) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String path = BaseIni.getBasepath() + "/upload/backdata/" + sdf.format(dt) + ".txt";
		FileManager.reWriteFile(path, msg);
	}

	/**
	 * 输出文件
	 * 
	 * @param response
	 * @param path
	 * @throws FileNotFoundException
	 */
	public void downloadLocal(HttpServletResponse response, String path) throws FileNotFoundException {
		// 下载本地文件
		String fileName = path.substring(path.lastIndexOf(File.separator) + 1, path.length()); // 文件的默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream(path);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[1024];
		int len;
		try {
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
			// System.out.println(new String(b));
		} catch (IOException e) {
			System.out.println(e.toString());
			try {
				response.getOutputStream().write(e.getMessage().getBytes(), 0, e.getMessage().getBytes().length);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void downloadLocal2(HttpServletResponse response, String path) throws FileNotFoundException {

		// 下载本地文件
		String fileName = path.substring(path.lastIndexOf(File.separator) + 1, path.length()); // 文件的默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream(path);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/jpg");
		// response.setContentType("image/jpeg");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[1024];
		int len;
		try {
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
			// System.out.println(new String(b));
		} catch (IOException e) {
			System.out.println(e.toString());
			try {
				response.getOutputStream().write(e.getMessage().getBytes(), 0, e.getMessage().getBytes().length);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * // 下载本地文件 String fileName =
		 * path.substring(path.lastIndexOf(File.separator) + 1, path.length());
		 * // 文件的默认保存名 // 读到流中 InputStream inStream = new
		 * FileInputStream(path);// 文件的存放路径 // 设置输出的格式 response.reset();
		 * response.addHeader("Content-Length", "" );
		 * response.setHeader("Cache-Control", "no-cache");
		 * //response.setContentType("image/jpg");
		 * response.setContentType("image/jpeg");
		 * 
		 * // 循环取出流中的数据 byte[] b = new byte[1024]; int len;
		 * 
		 * try {
		 * 
		 * OutputStream os = response.getOutputStream();
		 * 
		 * while ((len = inStream.read(b)) > 0) {
		 * response.getOutputStream().write(b, 0, len); os.write(b); }
		 * 
		 * os.flush(); os.close();
		 * 
		 * } catch (IOException e) { try {
		 * response.getOutputStream().write(e.getMessage().getBytes(), 0,
		 * e.getMessage().getBytes().length); } catch (IOException e1) {
		 * e1.printStackTrace(); } } finally { try {
		 * response.getOutputStream().close(); } catch (IOException e) {
		 * e.printStackTrace(); } try { inStream.close(); } catch (IOException
		 * e) { e.printStackTrace(); } }
		 */
	}

	public String formatXml(String str) throws UnsupportedEncodingException, IOException, DocumentException {
		// System.out.println(" str :  " + str);
		SAXReader reader = new SAXReader();
		// System.out.println(reader);
		// 注释：创建一个串的字符输入流
		StringReader in = new StringReader(str);
		Document doc = reader.read(in);
		// System.out.println(doc.getRootElement());
		// 注释：创建输出格式
		OutputFormat formater = OutputFormat.createPrettyPrint();
		// 注释：设置xml的输出编码
		formater.setEncoding("utf-8");
		// 注释：创建输出(目标)
		StringWriter out = new StringWriter();
		// 注释：创建输出流
		XMLWriter writer = new XMLWriter(out, formater);
		// 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
		writer.write(doc);

		String destXML = out.toString();
		writer.close();
		out.close();
		in.close();
		// 注释：返回我们格式化后的结果
		return destXML;
	}

}