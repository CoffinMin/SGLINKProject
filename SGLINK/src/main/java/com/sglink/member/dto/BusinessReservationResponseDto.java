package com.sglink.member.dto;

import com.sglink.common.constant.Process;
import com.sglink.entity.BusinessReservation;
import com.sglink.entity.Member;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BusinessReservationResponseDto {
	private Long id;
	private String busiId;
	private String busiName;
	private String busiRegisterId;
	private Member member;
	private String startDate;
	private String endDate;

	private Process busiProcess;

	public BusinessReservationResponseDto(BusinessReservation entity) {
		this.id = entity.getId();
		this.busiId = entity.getBusiId();
		this.busiName = entity.getBusiName();
		this.busiRegisterId = entity.getBusiRegisterId();
		this.member = entity.getMember();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();

		this.busiProcess = entity.getProcess();
	}

}
