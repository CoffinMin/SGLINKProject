package com.sglink.board.dto;

import java.time.LocalDateTime;

import com.sglink.entity.Member;
import com.sglink.entity.OpeninoBoard;

import groovy.transform.ToString;
import lombok.Getter;

@ToString
@Getter
public class OpeninoBoardResponseDto {
	private Long id;
	private String title;
	private String content;
	private int readCnts;
	private Member member;
	private LocalDateTime registerTime;

	
	public OpeninoBoardResponseDto(OpeninoBoard entity) {
		this.id =entity.getId();
		this.title= entity.getTitle();
		this.content = entity.getContent();
		this.readCnts = entity.getReadCnts();
		this.member= entity.getMember();
		this.registerTime = entity.getRegisterTime();

	}
	

}