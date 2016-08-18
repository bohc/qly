package com.util;

import java.io.File;
import java.io.FilenameFilter;

public class CustomFileFilter implements FilenameFilter{
	private String[] extentions;
	public CustomFileFilter(){
		
	}
	public String getDescription() {
		String desc = "";
		for (int i = 0; i < extentions.length; ++i) {
			desc += "*" + extentions[i] + " ";
		}
		return desc;
	}

	public boolean accept(File dir, String name) {
		if(name.indexOf("s.")!=-1){
			return false;
		}
		return true;
	}

}
