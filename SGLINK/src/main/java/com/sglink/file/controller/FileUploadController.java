package com.sglink.file.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sglink.file.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/upload")
@RequiredArgsConstructor
@Controller
public class FileUploadController {
	@Autowired
    private FileUploadService fileUploadService;

	/*
	 * @PostMapping("/single") public String uploadSingle(@RequestParam("files")
	 * MultipartFile file) throws Exception { String rootPath =
	 * FileSystemView.getFileSystemView().getHomeDirectory().toString(); String
	 * basePath = rootPath + "/" + "single"; String filePath = basePath + "/" +
	 * file.getOriginalFilename(); File dest = new File(filePath);
	 * file.transferTo(dest); // 파일 업로드 작업 수행 return "uploaded"; }
	 */
	@GetMapping("/view")
	public String uploadView() {
		return "/file/upload";
	}


}
