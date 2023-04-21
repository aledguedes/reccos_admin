package com.reccos.admin.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reccos.admin.dto.PathDTO;
import com.reccos.admin.utils.UploadService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UploadController {

	@Autowired
	private UploadService upload;

	@PostMapping("/upload/{slug}")
	public ResponseEntity<PathDTO> uploadFoto(@RequestParam(name = "file") MultipartFile file,
			@PathVariable String slug) {
		System.out.println("debug string slug: " + slug);
		String path = upload.uploadImage(file, slug);
		if (path != null) {
			PathDTO pathDto = new PathDTO();
			pathDto.setPathToFile(path);
			return ResponseEntity.ok().body(pathDto);
		}
		return ResponseEntity.badRequest().build();
	}
}
