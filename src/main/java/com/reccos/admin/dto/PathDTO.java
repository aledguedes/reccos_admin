package com.reccos.admin.dto;

import org.springframework.stereotype.Service;

@Service
public class PathDTO {

	private String pathToFile;

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}
}
