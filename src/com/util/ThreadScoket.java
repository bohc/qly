package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.base.BaseIni;
import com.pj.dao.DaoBase;

public class ThreadScoket extends Thread{
	private String wlog="";
	private String addr = "";
	private String nodeid="";
	private String msg = "";
	private Socket socket;
	private PrintWriter out;
	private int flag=0;
	protected static Logger log = Logger.getLogger(ThreadScoket.class.getName());
	private int sport;
	public ThreadScoket(){
	}
	public ThreadScoket(String addr,String msg,String nodeid,int sport){
		this.addr 	= 	addr;
		this.msg  	= 	msg;
		this.nodeid =	nodeid;
		this.sport  =   sport;
	}
	
	public ThreadScoket(int flag){
		this.flag 	= 	flag;
	}
	
	public void run(){
		try {
			switch(flag){
				case 0://外理发送消息
					sendSocket();
					break;
				case 1://外理初始信息
					initUserToNodeid();
					break;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private int sendSocket(){
		int flag=0;
		if(addr==null || addr.equals("")){
			return -1;
		}
		log.debug("send "+addr+":"+this.sport+" '"+msg+"' to terminal");

		try{
			socket = new Socket();
			socket.connect(new InetSocketAddress(InetAddress.getByName(addr),this.sport), 5000);
			socket.setSoTimeout(5000);
			out = new PrintWriter(socket.getOutputStream());
			msg=msg+"\r\n";
			out.println(msg); 
			out.flush();
			
			flag = 1;
			wlog=new SimpleDateFormat("HH:mm:ss").format(new Date())+" "+nodeid+" 发送成功===="+addr+":"+this.sport+" "+msg;
			
			Queue sendlog=null;
			try{
				sendlog=(Queue) BaseIni.getSessmap().get("sendlog");
			}catch(Exception el){}
			sendlog=sendlog==null?new LinkedList():sendlog; 
			sendlog.offer(wlog);
			BaseIni.getSessmap().put("sendlog", sendlog);
			
			DaoBase.writeLogFile(wlog);
		}catch(Exception e){System.out.println("发送失败");
			flag = -1;
			wlog=new SimpleDateFormat("HH:mm:ss").format(new Date())+" "+nodeid+" 发送失败===="+addr+":"+this.sport+" "+msg;
			Queue sendlog=null;
			try{
				sendlog=(Queue) BaseIni.getSessmap().get("sendlog");
			}catch(Exception el){}
			sendlog=sendlog==null?new LinkedList():sendlog; 
			sendlog.offer(wlog);
			BaseIni.getSessmap().put("sendlog", sendlog);
			
			DaoBase.writeLogFile(wlog);
		}finally{
			out.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public void initUserToNodeid(){
		//添加初始信息
		//TblsubeventDao.getInstence().InsertNodeidByCcid();
	}
	
	//发送信息加密
	public String strEncode(String str) {
		String sret="";
		String ss="";
		int p,k;
		try {
			byte[] buff=str.getBytes("GBK");
			byte n;
			for(p=0; p<buff.length; p++) {
				n=buff[p];
				ss="00"+Integer.toHexString(n);
				k=ss.length()-2;
				sret=sret+ss.substring(k);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sret.toUpperCase();
	}
	
	
	public static void main(String args[]){
		String msg="SHOWMAIN";
		//msg="SHOWUSERFACE";
		//msg="REGSET SYS NODENAME ";
		//msg="REGSET SYS NODEADDR ";
		//try {
			//msg+=new String("测试地址1测试地址%$#%$#$%hjhfhdgfh".getBytes("utf-8"),"unicode");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		//msg+="abcdefg中";
		ThreadScoket ts=new ThreadScoket("192.168.201.223",msg,"",30001);
		ts.run();
	}
}
