package com.pj.dao;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.util.ZipFile;

public class DaoMethod {
	public String dopost(Map<String, String> val, String url) {
		String returnConnection = null;
		// 封装数据
		Map<String, String> parmas = new HashMap<String, String>();
		parmas = val;
		HttpClient client = new DefaultHttpClient();// http客户端
		HttpPost httpPost = new HttpPost(url);

		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		if (parmas != null) {
			Set<String> keys = parmas.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				pairs.add(new BasicNameValuePair(key, parmas.get(key)));
			}
		}
		try {
			UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs, "UTF-8");
			httpPost.setEntity(p_entity);
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipFile.decompress(content, baos);
			// returnConnection = convertStreamToString(content);
			returnConnection = baos.toString("UTF-8");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnConnection;
	}

	// 上传图片
	public String dopostFile(Map<String, String> val, String url) {
		String returnConnection = null;
		// 封装数据
		Map<String, String> parmas = new HashMap<String, String>();
		parmas = val;
		DefaultHttpClient client = new DefaultHttpClient();// http客户端
		HttpPost httpPost = new HttpPost(url);
		MultipartEntity mulentity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		if (parmas != null) {
			Set<String> keys = parmas.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				try {
					mulentity.addPart(key, new StringBody(parmas.get(key)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			if (parmas != null) {
				String imguri = parmas.get("imguri");
				File aFile = new File(imguri);
				if (aFile.exists()) {
					FileBody filebody = new FileBody(aFile);
					mulentity.addPart(parmas.get("filename"), filebody);
				}
			}
			httpPost.setEntity(mulentity);

			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			returnConnection = convertStreamToString(content);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnConnection; 
	}

	protected String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
