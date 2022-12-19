package com.sglink.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
//공통적으로 날짜 필드를 관리할 클래스
	
	
//	@Column(updatable = false)
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime registerTime;
	
	@LastModifiedDate
	private LocalDateTime updateTime;
}
