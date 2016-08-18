package com.util;

public class QueryLikeUtil {
  public static String getLikeStr(String s){
	  if(s!=null&&!s.equals("")){
		s="%"+s+"%";  
	  }
	  return s;
  }
  public static String getLeftLikeStr(String s){
	  if(s!=null&&!s.equals("")){
		s=s+"%";  
	  }
	  return s;
  }
  
  public static String getStrToShort(String str,int length){
	  String temstr=str;
	  if(temstr==null){
		  return null;
	  }
	  if(temstr.length()>length){
		  temstr=temstr.substring(0,length);
	  }
	  return temstr;
  }

}
