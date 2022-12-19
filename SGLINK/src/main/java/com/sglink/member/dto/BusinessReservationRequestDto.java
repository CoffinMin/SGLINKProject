package com.sglink.member.dto;

import com.sglink.entity.BusinessReservation;
import com.sglink.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BusinessReservationRequestDto {
	private String busiId;
	private String busiName;
	private String busiRegisterId;
	private Member member;

	private String startDate;
	private String endDate;

	public BusinessReservation toEntity() {
		return BusinessReservation.builder()
				.busiId(busiId)
				.busiName(busiName)
				.busiRegisterId(busiRegisterId)
				.member(member)
				.startDate(startDate)
				.endDate(endDate)
				.build();
	}
}
