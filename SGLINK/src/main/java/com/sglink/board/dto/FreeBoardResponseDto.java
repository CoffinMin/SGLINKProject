package com.sglink.board.dto;

import java.time.LocalDateTime;

import com.sglink.entity.Board;
import com.sglink.entity.Member;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class FreeBoardResponseDto {
	private Long id;
	private String title;
	private String content;
	private int readCnt;
	private Member member;
	private LocalDateTime registerTime;
	private String boardName;
	
	public FreeBoardResponseDto(Board entity) {
		this.id =entity.getId();
		this.title= entity.getTitle();
		this.content = entity.getContent();
		this.readCnt= entity.getReadCnt();
		this.member= entity.getMember();
		this.registerTime = entity.getRegisterTime();
		this.boardName= entity.getBoardName();
	}
	

}
