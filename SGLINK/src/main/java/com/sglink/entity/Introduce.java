package com.sglink.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name= "introduce")
public class Introduce{
	
	@Id
	private Long id;
	
	@Column(nullable = true, length = 6000)
	private String content;


	
	
	
	@Builder
	public Introduce(Long id, String content) {
		this.id = id;
		this.content = content;
	}
	

	

}
