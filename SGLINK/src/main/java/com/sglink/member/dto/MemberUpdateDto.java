package com.sglink.member.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sglink.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
	
	@NotBlank(message = "이름을 바꿔주세요.")
	private String userName;

	@NotEmpty(message = "비밀번호를 바꿔주세요.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 변경가능합니다")
	private String userPw;

	
//	public MemberUpdateDto(Member member) {
//		this.userId = member.getUserId();
//		this.userName = member.getUserName();
//		this.userPw = member.getUserPw();
//		this.userEmail =member.getUserEmail();
//		this.userUniname = member.getUserUniname();
//		this.userTel = member.getUserTel();
//	}
//	
}
