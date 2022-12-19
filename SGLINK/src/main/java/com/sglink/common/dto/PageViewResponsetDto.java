package com.sglink.common.dto;




import com.sglink.entity.Introduce;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PageViewResponsetDto {
	
	private Long id;
	private String content;
	
	public PageViewResponsetDto(Introduce entity) {
		this.id =entity.getId();
		this.content = entity.getContent();

	}

}
