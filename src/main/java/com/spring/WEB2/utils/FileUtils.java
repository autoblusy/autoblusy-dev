package com.spring.WEB2.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	private static String UPLOADED_FOLDER = "/";
	private static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/photos/";
	
	public String singleFileUpload( MultipartFile file, String name) {;
		if (file.isEmpty()) {
			return "user";
		}
		
		try {
		
		// Get the file and save it somewhere
		byte[] bytes = file.getBytes();
		System.out.println(file.getContentType());
		Path path = Paths.get(uploadDirectory + name + ".jpg" );
		Files.write(path, bytes);
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		return file.getOriginalFilename();
	
	}
}
