package com.sglink.equipment.dto;

import java.util.List;

import com.sglink.entity.Equipment;
import com.sglink.entity.FileEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EquipmentRequestDto {

	private String equiId;

	private String equiName;

	private String equiUniname;

	private String equiContent;
	
	private String equiRegisterId;
	
	private String equiRegisterName;
	
	private String equiTel;
	
	private List<FileEntity> img;

	
	public Equipment toEntity() {
		return Equipment.builder()
				.equiId(equiId)
				.equiName(equiName)
				.equiUniname(equiUniname)
				.equiContent(equiContent)
				.equiRegisterId(equiRegisterId)
				.equiRegisterName(equiRegisterName)
				.equiTel(equiTel)
				.img(img) 
				.build();
	}
	
}
