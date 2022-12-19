package com.sglink.common.dto;

import com.sglink.entity.Introduce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class PageViewRequestDto {
	
	private Long id;
	private String content;
	
	public Introduce toEntity() {
		return Introduce.builder()
				.id(id)
				.content(content)
				.build();
	}

}
