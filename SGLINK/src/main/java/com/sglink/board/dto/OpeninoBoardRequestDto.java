package com.sglink.board.dto;

import com.sglink.entity.Board;
import com.sglink.entity.Member;
import com.sglink.entity.OpeninoBoard;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class OpeninoBoardRequestDto {
	private Long id;
	private String title;
	private String content;
	private Member member;

	
	public OpeninoBoard toEntity() {
		return OpeninoBoard.builder()
				.title(title)
				.content(content)
				.member(member)
				.build();
	}

}
