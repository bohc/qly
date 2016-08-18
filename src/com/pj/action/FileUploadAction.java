package com.pj.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.base.BaseIni;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUploadAction extends ActionBase {
	private File file;
	private String fileFileName;
	private String fileContentType;

	public String uploadFile() {
		String rpath=BaseIni.getBasepath();
		Date dpath=new Date();
		String randompath=new SimpleDateFormat("yyyy/MM/dd").format(dpath);
		rpath+="upload/"+randompath;
		
		String subfix=fileFileName.substring(fileFileName.lastIndexOf("."));
		
		File f=new File(rpath+"/"+dpath.getTime()+subfix);
		if(!f.exists()){
			f.mkdirs();
			f.delete();
		}
		try {
			FileUtils.copyFile(file, f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		printData(f.getAbsolutePath().substring(BaseIni.getBasepath().length()));
		return ActionSupport.NONE;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
