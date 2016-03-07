package com.xa3ti.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
public class UploadImg {
	private String newFileName ;//大图
	private String newTempName ;//缩略图
	static String forderName="";//上传文件夹名称
	private Map<String,String> map = new HashMap<String,String>();
	private String separaorChar = "/";
	public void setForderName(String forderName){
		this.forderName = forderName;
	}
	public Map<String,String> fileUpload(HttpServletRequest request,int width,int height) {
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request; 
			@SuppressWarnings("unchecked")
			Map<String,String[]>  params = mRequest.getParameterMap();//普通表单
			System.out.println("普通表单:");
			for(Entry<String,String[]> entry : params.entrySet()){
				String fieldName = entry.getKey();//表单名
				map.put(fieldName , entry.getValue()[0].trim());//所有表单参数
			}
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			System.out.println("文件表单:");
			for(Entry<String , MultipartFile> entry : fileMap.entrySet()){//文件表单
				String fieldName = entry.getKey();//表单名
				//System.out.println(fieldName);
				MultipartFile mFile = entry.getValue();
					long fileSize = mFile.getSize();//文件大小
					if(fileSize!=0){
						String fileType = mFile.getContentType();//文件类型
						String fileName = mFile.getOriginalFilename();//文件名
						String basePath = "/";
						String subpath =rootPath+"upload"+separaorChar+forderName+separaorChar;//大图服务器上传路径
						String temPath =rootPath+"upload"+separaorChar+forderName+separaorChar+"_temp"+separaorChar;//缩略图服务器上传路径
						File direction1 = new File(subpath);
						File direction2 = new File(temPath);
			            if(!direction1.exists()){
			            	direction1.mkdir();
			            	if(!direction2.exists()){
			            		direction2.mkdirs();
			            	}
			            }
						if(fileType.indexOf("image")!=-1){//文件为图片
							try{
						        ImageCut imageCut = new ImageCut(0,0,width, height);//初始图片剪切类，设置x,y,width,height
						        ImageCut imageCut2 = new ImageCut(0,0,128,128);//初始图片剪切类，设置x,y,width,height
						        //调用图片剪切，传入item、存入路径,返回新文件名字
						        newFileName =  imageCut.cut(mFile.getOriginalFilename(),mFile.getInputStream(),subpath);
						        newTempName =  imageCut2.cutTemp(newFileName, temPath);
						        map.put(fieldName+"_temp", basePath + newTempName.substring(newTempName.indexOf("upload")));
							}catch (Exception e) {
								continue;
							}
						}else{//其他文件
							FileOutputStream out = new FileOutputStream(request.getRealPath(File.separatorChar+"")+"upload"+File.separatorChar+forderName+File.separatorChar+UUID.randomUUID()+"."+fileName.substring(fileName.lastIndexOf(".")+1));
							out.write(mFile.getBytes());
							out.close();
							newFileName =rootPath+"upload"+File.separatorChar+forderName+File.separatorChar+UUID.randomUUID()+"."+fileName.substring(fileName.lastIndexOf(".")+1);
						}
						map.put(fieldName, basePath + newFileName.substring(newFileName.indexOf("upload")));
					}
				}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,String> fileUpload2(HttpServletRequest request,int width,int height) {
		List<FileItem> list;
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024 * 10);
			factory.setRepository(new File(rootPath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			list = upload.parseRequest(request);
			for (FileItem item : list) {
				String fieldName = item.getFieldName();//表单名
				if (!item.isFormField()) {// 文件表单
					long fileSize = item.getSize();//文件大小
					if(fileSize!=0){
						String fileType = item.getContentType();//文件类型
						String fileName = item.getName();//文件名
						String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
						String subpath =rootPath+"upload/"+forderName+separaorChar;//大图服务器上传路径
						String temPath =rootPath+"upload/"+forderName+separaorChar+"_temp/";//大图服务器上传路径
						File direction1 = new File(subpath);
						File direction2 = new File(temPath);
			            if(!direction1.exists()){
			            	direction1.mkdir();
			            	if(!direction2.exists()){
			            		direction2.mkdir();
			            	}
			            }
						if(fileType.indexOf("image")!=-1){//文件为图片
							ImageCut imageCut = new ImageCut(0,0,width, height);//初始图片剪切类，设置x,y,width,height
					        ImageCut imageCut2 = new ImageCut(0,0,100,100);//初始图片剪切类，设置x,y,width,height
					        //调用图片剪切，传入item、存入路径,返回新文件名字
					        newFileName =  imageCut.cut(fileName,item.getInputStream(),subpath);
					        newTempName =  imageCut2.cutTemp(newFileName, temPath);
					        map.put(fieldName+"_temp", basePath + newTempName.substring(newTempName.indexOf("upload")));
						}else{//其他文件
							FileOutputStream out = new FileOutputStream(request.getRealPath(File.separatorChar+"")+"upload"+File.separatorChar+forderName+File.separatorChar+UUID.randomUUID()+"."+fileName.substring(fileName.lastIndexOf(".")+1));
							out.write(item.get());
							out.close();
							newFileName =rootPath+"upload"+File.separatorChar+forderName+File.separatorChar+UUID.randomUUID()+"."+fileName.substring(fileName.lastIndexOf(".")+1);
						}
						map.put(fieldName, basePath + newFileName.substring(newFileName.indexOf("upload")));
						//System.out.println(newFileName);
					}
				}else {//普通表单
					String value = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
					map.put(fieldName, value);
				}
			}
		}catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
