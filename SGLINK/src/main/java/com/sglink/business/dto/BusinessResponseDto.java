package com.sglink.business.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.sglink.common.constant.Process;
import com.sglink.common.constant.Reservation;
import com.sglink.entity.Business;
import com.sglink.entity.FileEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BusinessResponseDto {
	
	private String busiId;

	private String busiName;

	private String busiUniname;
	
	private String busiContent;
	
	private String busiRegister;
	
	private String busiTel;
	
	private Reservation reservation;
	
	private List<FileEntity> img;
	
	private Process process;
	
	private String startDate;
	private String endDate;
	
	private LocalDateTime registerTime;
	
	public BusinessResponseDto(Business entity) {
		this.busiId=entity.getBusiId();
		this.busiName=entity.getBusiName();
		this.busiRegister=entity.getBusiRegisterName();
		this.busiTel=entity.getBusiTel();
		this.busiUniname=entity.getBusiUniname();
		this.busiContent=entity.getBusiContent();
		this.img=entity.getBusiImg();
		this.process =entity.getProcess();
		this.reservation = entity.getReservation();
		this.registerTime = entity.getRegisterTime();
		this.startDate=entity.getStartDate();
		this.endDate=entity.getEndDate();
	}
}
