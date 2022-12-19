package com.sglink.admin.dto;

import java.time.LocalDateTime;

import com.sglink.common.constant.Process;
import com.sglink.common.constant.Reservation;
import com.sglink.entity.Company;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompanyResponseDto {
	
	private String comId;
	private String comUniname;
	private String comRepname;
	private String comTel;
	private String comSectors;
	private String comProduct;
	private Process comProcess;
	private LocalDateTime registerTime;
	public CompanyResponseDto(Company company) {
		super();
		this.comId = company.getComId();
		this.comUniname = company.getComUniname();
		this.comRepname = company.getComRepname();
		this.comTel = company.getComTel();
		this.comSectors = company.getComSectors();
		this.comProduct = company.getComProduct();
		this.comProcess = company.getProcess();
		this.registerTime = company.getRegisterTime();
	}
	

	

}
