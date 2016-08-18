package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CommonUtil {
	public CommonUtil(){
		
	}
	
	/**
	 * 编码 UTF-8编码格式
	 * @param strUrl
	 * @return 返回""：出现异常
	 */
	public static String urlEnCode(String strUrl)
	{
		try {
			return URLEncoder.encode(strUrl,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	/**
	 * 解码 UTF-8编码格式
	 * @param strUrl
	 * @return 返回""：出现异常
	 */
	public static String urlDeCode(String strUrl)
	{
		try {
			return URLDecoder.decode(strUrl,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	/**
	 * 防止sql注入 过滤参数使用
	 * @param strPara 需过滤的参数
	 * @return 过滤后的参数
	 */
	public static String filterPara(String strPara){
		String filter ="and,exec,insert,select,delete,update,count,%,chr,mid,master,truncate,declare,;,*,or,--,"; 
		String[] StrFilter = filter.split(",");
		for(int i=0; i< StrFilter.length; i++){
			strPara = strPara.replace(StrFilter[i], ""); 
		}
		return strPara; 
	}
	
	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * 
	 * @param sourceFilePath 待压缩的文件路径
	 * @param zipFilePath 压缩后存放路径
	 * @param fileName 压缩后文件的名称
	 * @return flag true：成功 false：失败
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		if (sourceFile.exists() == false) {
			System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 不存在. <<<<<<");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				if (zipFile.exists()) {
					System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件. <<<<<<");
					zipFile.delete();
				}

				File[] sourceFiles = sourceFile.listFiles();
				if (null == sourceFiles || sourceFiles.length < 1) {
					System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 里面不存在文件,无需压缩. <<<<<<");
				} else {
					fos = new FileOutputStream(zipFile);
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					byte[] bufs = new byte[1024 * 10];
					for (int i = 0; i < sourceFiles.length; i++) {
						// 创建ZIP实体,并添加进压缩包
						ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(sourceFiles[i]);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
					}
					flag = true;
				}
				System.out.println(">>>>>> 待压缩的文件目录： 已经压缩成功. <<<<<<");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return flag;
	}
	
	/**
	 * 格式化sql语句中的like 将%加入字段值中
	 * @param type 格式化类型 1：两边都加% 2：左边加% 3：右边加%
	 * @param str 需格式化的字段
	 * @return null：格式化失败
	 */
	public static String formatSqlLike(int type ,String str) {
		if (str != null && !str.equals("")) {
			switch(type){
			case 1:
				str = "%" + str + "%";
				break;
			case 2:
				str = "%" + str;
				break;
			case 3:
				str = str + "%";
				break;
			}
			
		} else {
			str = null;
		}
		return str;
	}
	
	/**
	 * 待添加
	 * @param type
	 * @return
	 */
	public static Object getRandom(int type){
		Object obj = null;		
		switch(type){
		case 1:
			break;
		case 2:
			break;
			default:
				break;
		}
		return obj;
	}
	
    /**
     * 获取全球唯一码
     * @return
     */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID(); 
		return uuid.toString();
	}
	
	/**
	 * 
	 * @param type 
	 * 		1:输出格式: 2006年4月16日 星期六  
	 * 		2:输出格式: 2006-01-25
	 * 		3:输出格式: 2006-01-01 00:00:00
	 * 		4:输出格式: 20060101000000
	 * 		默认的输出格式为：2006-01-25
	 * @param date 需要格式化的日期
	 * @return "-1":出错    未出错返回格式化的日期
	 */
	public static String dateFormat(int type ,Date date) {
		String retDate = "-1";
		if (null != date) {
			switch(type){
			case 1:
				 // 输出格式: 2006年4月16日 星期六 
				retDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
				break;
			case 2:
				 // 输出格式: 2006-01-25
				retDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
				break;
			case 3:
				//输出格式: 2006-01-01 00:00:00 
				retDate = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
				break;
			case 4:
				//输出格式: 20060101000000
				retDate = new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(date);
				break;
			default:
				// 输出格式: 2006-01-25
				retDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
				break;
			}			
		}
		return retDate;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(dateFormat(3,date));
	}
	/**
	 * 获取当前日期延后或提前天数的日期
	 * @param days 延后的天数 负数表示提前的天数
	 * @return "-1":出错    未出错返回格式化的日期
	 */
	public static String getDateAfterPara(int days){
		String retDate = "-1";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		retDate = format.format(calendar.getTime());
		return retDate;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String strNull(Object str) {
		if (null == str || "null".equals(str.toString()))
		{
			return "";
		}
		else
		{
			return str.toString();
		}
		
	}
	
	/**
	 * 
	 * @param acode 传入区域编码
	 * @return 返回格式化好的区域编码
	 */
	public static String analyseArea(String acode) {
		if (acode == null || acode.length() < 8)
			return "-11111111";
		String rcode = "";
		try {
			for (int i = 0; i < 3; i++) {
				String tcode = acode.substring(i * 2, i * 2 + 2);
				if (!tcode.equals("00")) {
					rcode += tcode;
				}
			}
		} catch (Exception e) {
			rcode = "-11111111";
		}
		return rcode;
	}
	
	/**
	 * 
	 * @param acode 传入区域编码
	 * @return 返回格式化好的区域编码
	 */
	public static int analyseArea(int acode) {
		String tempCode = String.valueOf(acode).trim();
		if (tempCode.length() < 8)
			return -11111111;
		String rcode = "";
		try {
			for (int i = 0; i < 4; i++) {
				String tcode = tempCode.substring(i * 2, i * 2 + 2);
				if (!tcode.equals("00")) {
					rcode += tcode;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1111111;
		}
		return Integer.valueOf(rcode);
	}
	
	/**
	 * 
	 * @param 需格式化的食品区域码
	 * @return 格式化完成的食品区域码
	 */
	public static long formatFoodZone(long obj){
		String acode = String.valueOf(obj);
		if (acode == null || acode.length() < 13)
			return -1111111111111L;
		char[] list = acode.toCharArray();
		for (int i = list.length - 1; i > 0; i--) {
			if ('0' == list[i])
				list[i] = '9';
			else
				break;
		}
		return Long.valueOf(String.valueOf(list));
	}
	
	/**
	 * 
	 * @param 需格式化的食品区域码
	 * @return 格式化完成的食品区域码
	 */
	public static long formatFoodZoneToSuperior(long obj){
		String acode = String.valueOf(obj).trim();
		if (acode.length() != 13)
			return -1111111111111L;
		char[] list = acode.toCharArray();
		for (int i = list.length - 1; i > 0; i--) {
			if ('0' != list[i])
				list[i] = '0';
			else
				break;
		}
		return Long.valueOf(String.valueOf(list));
	}
	
	/**
	 * 获取当前区域码的上级编码
	 * @param 需格式化的标准区域码
	 * @return 格式化完成的标准区域码
	 */
	public static int formatAreaToSuperior(int acode){
		String tempCode = String.valueOf(acode).trim();
		if (tempCode.length() != 8)
			return -11111111;
		char[] list = tempCode.toCharArray();
		String match = "";
		int counter = 0;
		for (int i = list.length-1; i >= 0; i--) {
			++counter;
			match += list[i];
			if(0 == counter%2){
				if (!match.equals("00")){
					if(i>1){
						list[i] = '0';
						list[i+1] = '0';
						break;
					}
				}else{
					match = "";
				}
			}
		}
		return Integer.valueOf(String.valueOf(list));
	}
	
	/**
	 * 获取当前区域码去除0之后的码值
	 * 
	 * @param 需格式化的标准区域码
	 * @return 格式化完成的标准区域码
	 */
	public static String formatAreaCode(String areacode) {
		if (areacode != null) {
			String tempCode = String.valueOf(areacode).trim();
			char[] list = tempCode.toCharArray();
			int endIndex = 0;
			for (int i = list.length - 1; i >= 0; i--) {
				if (list[i] != '0') {
					endIndex = i;
					break;
				}
			}
			return areacode.substring(0, endIndex + 1);
		} else
			return "";
	}
	
	/**
	 * 数据流压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void compress(InputStream is, OutputStream os) throws Exception {
		int BUFFER = 1024;
		GZIPOutputStream gos = new GZIPOutputStream(os);
		
		int count;
		byte data[] = new byte[BUFFER];
		while ((count = is.read(data, 0, BUFFER)) != -1) {
			gos.write(data, 0, count);
		}

		gos.finish();
		gos.flush();
		gos.close();
	}

	/**
	 * 数据流解压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void decompress(InputStream is, OutputStream os) throws Exception {
		int BUFFER = 1024;
		GZIPInputStream gis = new GZIPInputStream(is);

		int count;
		byte data[] = new byte[BUFFER];
		while ((count = gis.read(data, 0, BUFFER)) != -1) {
			os.write(data, 0, count);
		}

		gis.close();
	}
}