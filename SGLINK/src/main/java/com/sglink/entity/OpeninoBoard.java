package com.sglink.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="openino")
public class OpeninoBoard extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = true, length = 6000)
	private String content;
	private int readCnts;
	
	@ManyToOne
	@JoinColumn(name = "registerId")
	private Member member;
	
	
	
	@Builder
	public OpeninoBoard(Long id, String title, String content, int viewConunt, int readCnts, Member member) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.readCnts= readCnts;
		this.member = member;
	}
	
}
