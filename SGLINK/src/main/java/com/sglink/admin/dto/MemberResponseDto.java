package com.sglink.admin.dto;

import java.time.LocalDateTime;

import com.sglink.common.constant.Role;
import com.sglink.entity.Member;

import lombok.Getter;

@Getter
public class MemberResponseDto {
	
	private String userId;
	private String userName;
	private String userEmail;
	private String userTel;
	private String userUniname;
	private Role role;
	private LocalDateTime registerTime;
	

	@Override
	public String toString() {
		return "MemberResponseDto [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userTel=" + userTel
						+ ", userUniname=" + userUniname + ", Role=" + role + ", registerTime=" + registerTime + "]";
	}
	
	public MemberResponseDto(Member member) {
		this.userId = member.getUserId();
		this.userName = member.getUserName();
		this.userEmail = member.getUserEmail();
		this.userTel = member.getUserTel();
		this.userUniname = member.getUserUniname();
		this.registerTime = member.getRegisterTime();
		this.role = member.getRole();
	}

}
