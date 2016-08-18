package com.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * INI文件读取
 * 
 * @author Jacky
 * @version V1.0
 * @since 2008-04-23
 */
public class JInitFile {

	private FileReader iniFile;

	private String sFileName;

	public JInitFile(String FileName) {
		sFileName = FileName;
	}

	private String getKeyValue(String skey, String context) {
		String result = "";
		int ipos = context.indexOf("=");
		if (ipos > 1) {
			String keyCont = context.substring(0, ipos);
			if (keyCont.equalsIgnoreCase(skey)) {
				result = context.substring(ipos + 1);
			}
		}
		return result;
	}

	/**
	 * 取得INI文件中某一项的值
	 * 
	 * @param sKey
	 * @param DefaultValue
	 * @return
	 * @throws IOException
	 * @throws ItemNotFoundException
	 */
	public String ReadInitContext(String sKey, String DefaultValue)
			throws IOException, ItemNotFoundException, FileNotFoundException {
		String result = DefaultValue;
		iniFile = new FileReader(sFileName);
		BufferedReader bfrd = new BufferedReader(iniFile);
		String s;
		do {
			s = bfrd.readLine();
			if (s != null) {
				result = getKeyValue(sKey, s);
			}
		} while (result.equals("") && (s != null));
		bfrd.close();
		iniFile.close();
		iniFile = null;
		return result;
	}

	/**
	 * 从ini配置文件中读取变量的值
	 * 
	 * @param section
	 *            要获取的变量所在段名称
	 * @param variable
	 *            要获取的变量名称
	 * @param defaultValue
	 *            变量名称不存在时的默认值
	 * @return 变量的值
	 * @throws IOException
	 *             抛出文件操作可能出现的io异常
	 */
	public String getProfileString(String section,
			String variable, String defaultValue) throws IOException {
		String strLine, value = "";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(sFileName));
		boolean isInSection = false;
		try {
			while ((strLine = bufferedReader.readLine()) != null) {
				strLine = strLine.trim();
				strLine = strLine.split("[;]")[0];
				Pattern p;
				Matcher m;
				p = Pattern.compile("file://[//s*.*//s*//]");
				m = p.matcher((strLine));
				if (m.matches()) {
					p = Pattern.compile("file://[//s*" + section
							+ "file://s*//]");
					m = p.matcher(strLine);
					if (m.matches()) {
						isInSection = true;
					} else {
						isInSection = false;
					}
				}
				if (isInSection == true) {
					strLine = strLine.trim();
					String[] strArray = strLine.split("=");
					if (strArray.length == 1) {
						value = strArray[0].trim();
						if (value.equalsIgnoreCase(variable)) {
							value = "";
							return value;
						}
					} else if (strArray.length == 2) {
						value = strArray[0].trim();
						if (value.equalsIgnoreCase(variable)) {
							value = strArray[1].trim();
							return value;
						}
					} else if (strArray.length > 2) {
						value = strArray[0].trim();
						if (value.equalsIgnoreCase(variable)) {
							value = strLine.substring(strLine.indexOf("=") + 1)
									.trim();
							return value;
						}
					}
				}
			}
		} finally {
			bufferedReader.close();
		}
		return defaultValue;
	}

	/**
	 * 修改ini配置文件中变量的值
	 * 
	 * @param file
	 *            配置文件的路径
	 * @param section
	 *            要修改的变量所在段名称
	 * @param variable
	 *            要修改的变量名称
	 * @param value
	 *            变量的新值
	 * @throws IOException
	 *             抛出文件操作可能出现的io异常
	 */
	public boolean setProfileString(String section,
			String variable, String value) throws IOException {
		String fileContent, allLine, strLine="", newLine, remarkStr;
		String getValue;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(sFileName));
		boolean isInSection = false;
		boolean isInValue = false;
		fileContent = "";
		try {

			while ((allLine = bufferedReader.readLine()) != null) {
				allLine = allLine.trim();
				if (allLine.split("[;]").length > 1)
					remarkStr = ";" + allLine.split(";")[1];
				else
					remarkStr = "";
				strLine = allLine.split(";")[0].trim();
				if (!isInSection) {
					isInSection = strLine.equals("["+section+"]");
				}
//				Pattern p;
//				Matcher m;
//				p = Pattern.compile("file://[//s*.*//s*//]");
//				m = p.matcher((strLine));
//				if (m.matches()) {
//					p = Pattern.compile("file://[//s*" + section
//							+ "file://s*//]");
//					m = p.matcher(strLine);
//					if (m.matches()) {
//						isInSection = true;
//					} else {
//						isInSection = false;
//					}
//				}
				if (isInSection) {
					String str1 = strLine.split("=")[0].trim();
					isInValue = str1.equals(variable);
				}
				if (isInValue == true) {
//					strLine = strLine.trim();
					String[] strArray = strLine.split("=");
					getValue = strArray[0].trim();
					if (getValue.equalsIgnoreCase(variable)) {
						newLine = getValue + " = " + value + " " + remarkStr;
						fileContent += newLine + "\r\n";
						while ((allLine = bufferedReader.readLine()) != null) {
							fileContent += allLine + "\r\n";
						}
						bufferedReader.close();
						BufferedWriter bufferedWriter = new BufferedWriter(
								new FileWriter(sFileName, false));
						bufferedWriter.write(fileContent);
						bufferedWriter.flush();
						bufferedWriter.close();

						return true;
					}
				}
				fileContent += allLine + "\r\n";
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			bufferedReader.close();
		}
		return false;
	}

}
