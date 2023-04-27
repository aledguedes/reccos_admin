package com.reccos.admin.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	public String uploadImage(MultipartFile file, String slug) {
		
		System.out.println("UPLOAD IMAGEM: "+file);
		
		try {
			String caminho = "C:\\imagens\\"+slug;
			File dir = new File(caminho);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			Path path = Paths.get(dir + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("DEBUG - Arquivo copiado...");
			return file.getOriginalFilename();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
