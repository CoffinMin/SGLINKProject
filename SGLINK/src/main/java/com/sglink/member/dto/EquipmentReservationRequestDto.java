package com.sglink.member.dto;


import java.sql.Date;

import com.sglink.common.constant.Reservation;
import com.sglink.entity.EquipmentReservation;
import com.sglink.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
public class EquipmentReservationRequestDto {
	private String equiId;
	private String equiName;
	private String equiRegisterId;
	private Member member;
	private String startDate;
	private String endDate;
	
	public EquipmentReservation toEntity() {
		return EquipmentReservation.builder()
				.equiId(equiId)
				.equiName(equiName)
				.equiRegisterId(equiRegisterId)
				.member(member)
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}
	

}
