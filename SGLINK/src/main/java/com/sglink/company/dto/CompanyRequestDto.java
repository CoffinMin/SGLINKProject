package com.sglink.company.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sglink.common.constant.Process;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyRequestDto {
	@NotBlank(message = "기업코드는 필수 입력 값입니다.")
	private String comId;
	@NotBlank(message = "기업명은 필수 입력 값입니다.")
	private String comUniname;
	@NotBlank(message = "대표자명은 필수 입력 값입니다.")
	private String comRepname;
	@NotBlank(message = "기업 전화번호는 필수 입력 값입니다.")
	@Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식에 맞춰주세요 XXX-XXXX-XXX: ")
	private String comTel;
	@NotBlank(message = "업종은 선택은 필수입니다.")
	private String comSectors;
	@NotBlank(message = "주요상품은 필수 입력 값입니다.")
	private String comProduct;

}