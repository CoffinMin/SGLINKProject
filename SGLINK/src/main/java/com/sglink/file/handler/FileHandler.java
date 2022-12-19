package com.sglink.file.handler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sglink.entity.Business;
import com.sglink.entity.Equipment;
import com.sglink.entity.FileBoard;
import com.sglink.entity.FileEntity;

@Component
public class FileHandler {
	public List<FileEntity> parseFileInfo(List<MultipartFile> multipartFiles,Long id,FileBoard fileBoard) throws Exception {

		// 반환을 할 파일 리스트
		List<FileEntity> fileList = new ArrayList<>();

		// 파일이 빈 것이 들어오면 빈 것을 반환
		if (multipartFiles.isEmpty()) {
			return fileList;
		}

		// 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String current_date = simpleDateFormat.format(new Date());


		// 경로를 지정하고 그곳에다가 저장할 심산이다
		String path = "C:\\sglink\\fileBoard\\" + id +"\\" + current_date ;
		String staticPath = "/img/sglink/fileBoard/" + id +"/" + current_date ;
		File file = new File(path);
		// 저장할 위치의 디렉토리가 존지하지 않을 경우
		if (!file.exists()) {
			// mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
			file.mkdirs();
		}

		// 파일들을 이제 만져볼 것이다
		for (MultipartFile multipartFile : multipartFiles) {
			// 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
			if (!multipartFile.isEmpty()) {
				// jpeg, png, gif 파일들만 받아서 처리할 예정
				String contentType = multipartFile.getContentType();
				String originalFileExtension;
				String icon;
				// 확장자 명이 없으면 이 파일은 잘 못 된 것이다
				if (ObjectUtils.isEmpty(contentType)) {
					break;
				} else {
					if (contentType.contains("image/jpeg")) {
						originalFileExtension = ".jpg";
						icon ="jpg";
					} else if (contentType.contains("image/png")) {
						originalFileExtension = ".png";
						icon ="png";
					} else if (contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
						icon ="gif";
					} else if (contentType.contains("text/plain")) {
						originalFileExtension = ".txt";
						icon ="txt";
					}
					// 다른 파일 명이면 아무 일 하지 않는다
					else {
						break;
					}
				}
				// 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
				String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
				// 생성 후 리스트에 추가
				FileEntity fileEntity = FileEntity.builder().fileBoard(fileBoard)
						.original_file_name(multipartFile.getOriginalFilename())
						.stored_file_path(staticPath + "/" + new_file_name)
						.file_size(multipartFile.getSize())
						.icon(icon)
						.build();
				fileList.add(fileEntity);

				// 저장된 파일로 변경하여 이를 보여주기 위함
				file = new File(path + "\\" + new_file_name);
				multipartFile.transferTo(file);
			}
		}

		return fileList;
	}
	
	public List<FileEntity> parseFileImageInfo(List<MultipartFile> multipartFiles, String equiId, Equipment equipment) throws Exception {

		// 반환을 할 파일 리스트
		List<FileEntity> fileList = new ArrayList<>();

		// 파일이 빈 것이 들어오면 빈 것을 반환
		if (multipartFiles.isEmpty()) {
			return fileList;
		}

		// 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String current_date = simpleDateFormat.format(new Date());


		// 경로를 지정하고 그곳에다가 저장할 심산이다
		String path = "C:\\sglink\\equipment\\" + equiId +"\\" + current_date ;
		String staticPath = "/img/sglink/equipment/" + equiId +"/" + current_date ;
		File file = new File(path);
		// 저장할 위치의 디렉토리가 존지하지 않을 경우
		if (!file.exists()) {
			// mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
			file.mkdirs();
		}

		// 파일들을 이제 만져볼 것이다
		for (MultipartFile multipartFile : multipartFiles) {
			// 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
			if (!multipartFile.isEmpty()) {
				// jpeg, png, gif 파일들만 받아서 처리할 예정
				String contentType = multipartFile.getContentType();
				String originalFileExtension;
				String icon;
				// 확장자 명이 없으면 이 파일은 잘 못 된 것이다
				if (ObjectUtils.isEmpty(contentType)) {
					break;
				} else {
					if (contentType.contains("image/jpeg")) {
						originalFileExtension = ".jpg";
						icon ="jpg";
					} else if (contentType.contains("image/png")) {
						originalFileExtension = ".png";
						icon ="png";
					} else if (contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
						icon ="gif";
					}
					// 다른 파일 명이면 아무 일 하지 않는다
					else {
						break;
					}
				}
				// 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
				String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
				// 생성 후 리스트에 추가
				FileEntity fileEntity = FileEntity.builder().img(equipment)
						.original_file_name(multipartFile.getOriginalFilename())
						.stored_file_path(staticPath + "/" + new_file_name)
						.file_size(multipartFile.getSize())
						.icon(icon)
						.build();
				fileList.add(fileEntity);

				// 저장된 파일로 변경하여 이를 보여주기 위함
				file = new File(path + "\\" + new_file_name);
				multipartFile.transferTo(file);
			}
		}

		return fileList;
	}
	
	public List<FileEntity> parseFileImageInfo(List<MultipartFile> multipartFiles, String busiId, Business business) throws Exception {

		// 반환을 할 파일 리스트
		List<FileEntity> fileList = new ArrayList<>();

		// 파일이 빈 것이 들어오면 빈 것을 반환
		if (multipartFiles.isEmpty()) {
			return fileList;
		}

		// 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String current_date = simpleDateFormat.format(new Date());


		// 경로를 지정하고 그곳에다가 저장할 심산이다
		String path = "C:\\sglink\\business\\" + busiId +"/" + current_date ;
		String staticPath = "/img/sglink/business/" + busiId +"/" + current_date ;
		File file = new File(path);
		// 저장할 위치의 디렉토리가 존지하지 않을 경우
		if (!file.exists()) {
			// mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
			file.mkdirs();
		}

		// 파일들을 이제 만져볼 것이다
		for (MultipartFile multipartFile : multipartFiles) {
			// 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
			if (!multipartFile.isEmpty()) {
				// jpeg, png, gif 파일들만 받아서 처리할 예정
				String contentType = multipartFile.getContentType();
				String originalFileExtension;
				String icon;
				// 확장자 명이 없으면 이 파일은 잘 못 된 것이다
				if (ObjectUtils.isEmpty(contentType)) {
					break;
				} else {
					if (contentType.contains("image/jpeg")) {
						originalFileExtension = ".jpg";
						icon ="jpg";
					} else if (contentType.contains("image/png")) {
						originalFileExtension = ".png";
						icon ="png";
					} else if (contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
						icon ="gif";
					}
					// 다른 파일 명이면 아무 일 하지 않는다
					else {
						break;
					}
				}
				// 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
				String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
				// 생성 후 리스트에 추가
				FileEntity fileEntity = FileEntity.builder().busiImg(business)
						.original_file_name(multipartFile.getOriginalFilename())
						.stored_file_path(staticPath + "/" + new_file_name)
						.file_size(multipartFile.getSize())
						.icon(icon)
						.build();
				fileList.add(fileEntity);

				// 저장된 파일로 변경하여 이를 보여주기 위함
				file = new File(path + "\\" + new_file_name);
				multipartFile.transferTo(file);
			}
		}

		return fileList;
	}
}
