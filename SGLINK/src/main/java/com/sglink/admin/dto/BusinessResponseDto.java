package com.sglink.admin.dto;

import java.time.LocalDateTime;

import com.sglink.common.constant.Process;
import com.sglink.common.constant.Reservation;
import com.sglink.entity.Business;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusinessResponseDto {
	
	private String busiId;
	private String busiUniname;
	private String busiName;
	private String busiRegisterId;
	private String busiRegisterName;
	private String busiTel;
	private Process busiProcess;
	private Reservation reservation;
	private LocalDateTime registerTime;
	public BusinessResponseDto(Business business) {
		super();
		this.busiId = business.getBusiId();
		this.busiUniname = business.getBusiUniname();
		this.busiName = business.getBusiName();
		this.busiRegisterId =business.getBusiRegisterId();
		this.busiRegisterName =business.getBusiRegisterName();
		this.busiTel = business.getBusiTel();
		this.busiProcess =business.getProcess();
		this.reservation=business.getReservation();
		this.registerTime = business.getRegisterTime();
	}
	

	

}
