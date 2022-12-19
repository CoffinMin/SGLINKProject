package com.sglink.board.dto;

import com.sglink.entity.Member;
import com.sglink.entity.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class FreeBoardRequestDto {
	private Long id;
	private String title;
	private String content;
	private Member member;
	private String boardName;
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.member(member)
				.boardName(boardName)
				.build();
	}



}
