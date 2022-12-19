package com.sglink.board.dto;

import java.util.List;

import com.sglink.entity.FileBoard;
import com.sglink.entity.FileEntity;
import com.sglink.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class FileBoardRequestDto {
	
	private Long id;
	private String title;
	private String content;
	private Member member;
	private List<FileEntity> file; 
	
	public FileBoard toEntity() {
		return FileBoard.builder()
				.title(title)
				.content(content)
				.member(member)
				.file(file) 
				.build();
	}
	
	
	

}
