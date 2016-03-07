package com.xa3ti.base.util;

import java.io.File;

public class FileUtil {
	//删除目录及目录下文件
	public static boolean deleteFiles(String filePath){
		boolean flag = false;
		File file = new File(filePath);
		File[] files = file.listFiles();
		for(int i = 0 ; i < files.length;i++){
			if(files[i].isFile()){
				flag = deleteFile(files[i].getAbsolutePath());
				if(!flag)break;
			}else{
				flag = deleteFiles(files[i].getAbsolutePath());
				if(!flag)break;
			}
		}
		return file.delete() ;
	}
	//删除文件
	public static boolean deleteFile(String filePath){
		File file = new File(filePath);
		if(file.exists()&&file.isFile())return file.delete();
		return false;
	}
	/**
	 * 文件,文件夹重命名,renameTo具有剪贴功能
	 * @param resFilePath
	 * @param newFilePath
	 * @return
	 */
	public static boolean changeFileName(String resFilePath , String newFilePath){
		File resFile = new File(resFilePath);
		File newFile = new File(newFilePath);
		return resFile.renameTo(newFile);
	}
}
