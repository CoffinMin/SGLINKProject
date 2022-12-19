package com.sglink.entity;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name= "board")
public class Board extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@Column(nullable = true, length = 6000)
	private String content;
	private int readCnt;
	private String boardName;
	
	@ManyToOne
	@JoinColumn(name = "registerId")
	private Member member;
	

	
	
	
	@Builder
	public Board(Long id, String title, String content, int readCnt, Member member, String boardName) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.member = member;
		this.boardName= boardName;
	}
	

	

}
