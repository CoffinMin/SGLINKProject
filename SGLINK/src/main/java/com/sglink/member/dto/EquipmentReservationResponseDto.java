package com.sglink.member.dto;


import java.sql.Date;

import com.sglink.common.constant.Reservation;
import com.sglink.common.constant.Process;
import com.sglink.entity.Equipment;
import com.sglink.entity.EquipmentReservation;
import com.sglink.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
public class EquipmentReservationResponseDto {
	private Long id;
	private String equiId;
	private String equiName;
	private String equiRegisterId;
	private Member member;
	private String startDate;
	private String endDate;
	private Process equiProcess;
	
	public EquipmentReservationResponseDto(EquipmentReservation entity) {
		this.id = entity.getId();
		this.equiId=entity.getEquiId();
		this.equiName=entity.getEquiName();
		this.equiRegisterId=entity.getEquiRegisterId();
		this.member=entity.getMember();
		this.startDate=entity.getStartDate();
		this.endDate=entity.getEndDate();
		this.equiProcess =entity.getProcess();
	}
	

}
