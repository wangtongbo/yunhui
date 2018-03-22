package com.lakala.ls.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
	

	public static String saveFileImputStream( InputStream imageData ,String filepath) throws IOException{
	   File file= new File(filepath);
	   if(!file.exists()){
		   File parent = file.getParentFile();
		   if(!parent.exists())
			   parent.mkdirs();
		   file.createNewFile();
	   }
	   
		FileOutputStream filo=new FileOutputStream(file);
		byte[] buf = new byte[1024];
		int i;
		while(-1 != (i=imageData.read(buf))){
			filo.write(buf);
		}
		filo.flush();
		filo.close();
		imageData.close();
	return filepath;
}
}
