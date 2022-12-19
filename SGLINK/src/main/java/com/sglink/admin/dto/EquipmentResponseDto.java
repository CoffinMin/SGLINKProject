package com.sglink.admin.dto;

import java.time.LocalDateTime;

import com.sglink.common.constant.Process;
import com.sglink.common.constant.Reservation;
import com.sglink.entity.Equipment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EquipmentResponseDto {
	
	private String equiId;
	private String equiUniname;
	private String equiName;
	private String equiRegisterId;
	private String equiRegisterName;
	private String equiTel;
	private Process equiProcess;
	private Reservation reservation;
	private LocalDateTime registerTime;
	public EquipmentResponseDto(Equipment equipment) {
		super();
		this.equiId = equipment.getEquiId();
		this.equiUniname = equipment.getEquiUniname();
		this.equiName = equipment.getEquiName();
		this.equiRegisterId =equipment.getEquiRegisterId();
		this.equiRegisterName =equipment.getEquiRegisterName();
		this.equiTel = equipment.getEquiTel();
		this.equiProcess =equipment.getProcess();
		this.reservation=equipment.getReservation();
		this.registerTime = equipment.getRegisterTime();
	}
	

	

}
