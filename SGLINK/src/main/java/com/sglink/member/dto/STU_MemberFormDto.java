package com.sglink.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class STU_MemberFormDto {
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String stuuserId;

	@NotBlank(message = "담당자이름은 필수 입력 값입니다.")
	private String stuuserName;

	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String stuuserEmail;

	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
	private String stuuserPw;
	
//	@NotEmpty(message = "비밀번호확인은 필수 입력 값입니다.")
//	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
//	private String stuuserPw2;
	
	@NotBlank(message = "기관명은 필수 입력 값입니다.")
	private String stuuserUniname;

	@Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식에 맞춰주세요 XXX-XXXX-XXX: ")
	private String stuuserTel;

}