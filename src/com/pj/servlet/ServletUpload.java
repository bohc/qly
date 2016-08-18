package com.pj.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class ServletUpload extends HttpServlet {
	public ServletUpload() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收参数
		String path2 = request.getSession().getServletContext().getRealPath("/");

		List<FileEntity> fileList;
		fileList = (List) request.getAttribute("fileList");
		if (fileList == null)
			fileList = new ArrayList<FileEntity>();

		// 接收上传文件
		String uploadSign = request.getParameter("upload");
		String rootPath = request.getParameter("rootPath");
		String path = request.getParameter("path");
		String module = request.getParameter("module");
		
		if (rootPath == null)
			rootPath = "";
		rootPath = rootPath.trim();
		if (rootPath.equals("")) {
			rootPath = "files";
		}

		if (path == null) {
			path = rootPath;
		} else {
			//path = new String(Base64.decodeBase64(path.getBytes()));
		}
		if(module!=null && !module.equals("")){
			path=path+"/"+module;
		}
		System.out.println(path + "...path.getBytes():" + path.getBytes());
		uploadSign = "1";
		// 上传操作
		if (null != uploadSign && !"".equals(uploadSign)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setHeaderEncoding("UTF-8");
			try {
				List items = upload.parseRequest(request);
				if (null != items) {
					Iterator itr = items.iterator();
					int i = 0;
					while (itr.hasNext()) {
						FileItem item = (FileItem) itr.next();
						FileEntity file = new FileEntity();
						if (item.isFormField()) {
							continue;
						} else {
							// 自由修改处三：可修改上传后的文件命名方式
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");// 以当前精确到秒的日期为上传的文件的文件名
							SimpleDateFormat sdd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							SimpleDateFormat fpath = new SimpleDateFormat("/yyyy/MM/dd/");
							Date dt=new Date();
							String type = item.getName().substring(item.getName().lastIndexOf(".")+1);// 获取文件类型

							path+=fpath.format(dt);
							File savedFile = new File(path2+path, sdf.format(dt)+ "." + type);

							if(!savedFile.exists()){
								savedFile.mkdirs();
								savedFile.delete();
								savedFile.createNewFile();
							}
							
							// 把文件放到列表中，在前台显示
							file.setId(sdf.format(new Date()));
							file.setDate(sdd.format(new Date()));
							file.setFilename(item.getName());
							file.setFilepath(path + sdf.format(dt) + "." + type);
							file.setFilesize(item.getSize() + "");
							file.setFiletype(type);
							file.setMark("0");
							fileList.add(file);
							item.write(savedFile);
							
							//生成缩略图
							
//							String imgsrc=path2+path+sdf.format(dt)+ "." + type,
//								imgdist=path2+path+sdf.format(dt)+ "c." + type;
//							int widthdist=480,heightdist=320;
//							ImageZoom iz=new ImageZoom(imgsrc, imgdist, widthdist, heightdist);
//							new Thread(iz).start();
//							System.out.println(imgsrc);
//							System.out.println(imgdist);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		if (fileList != null) {
			for (int i = 0; i < fileList.size(); i++) {
				FileEntity file = (FileEntity) fileList.get(i);
				// out.println("文件："+ new
				// String(file.getFilename().getBytes("GBK"),"UTF-8")+"，文件路径："+file.getFilepath());
				String fpath=file.getFilepath();
				out.print(fpath);
				System.out.println(fpath);
			}
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
