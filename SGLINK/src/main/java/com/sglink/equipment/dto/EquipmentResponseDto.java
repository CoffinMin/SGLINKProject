package com.sglink.equipment.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.sglink.entity.Equipment;
import com.sglink.entity.FileEntity;
import com.sglink.common.constant.Process;
import com.sglink.common.constant.Reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EquipmentResponseDto {
	
	private String equiId;

	private String equiName;

	private String equiUniname;
	
	private String equiContent;
	
	private String equiRegister;
	
	private String equiTel;
	
	private Reservation reservation;
	
	private List<FileEntity> img;
	
	private Process process;
	
	private LocalDateTime registerTime;
	
	public EquipmentResponseDto(Equipment entity) {
		this.equiId=entity.getEquiId();
		this.equiName=entity.getEquiName();
		this.equiRegister=entity.getEquiRegisterName();
		this.equiTel=entity.getEquiTel();
		this.equiUniname=entity.getEquiUniname();
		this.equiContent=entity.getEquiContent();
		this.img=entity.getImg();
		this.process =entity.getProcess();
		this.reservation = entity.getReservation();
		this.registerTime = entity.getRegisterTime();
	}
}
