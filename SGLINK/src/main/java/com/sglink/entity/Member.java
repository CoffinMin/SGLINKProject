package com.sglink.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.sglink.common.constant.Role;
import com.sglink.member.dto.COM_MemberFormDto;
import com.sglink.member.dto.MemberUpdateDto;
import com.sglink.member.dto.STU_MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class Member  extends BaseTimeEntity{
	@Id
	@Column(name = "userId")
	private String userId;
	private String userName;
	@Column(unique = true)
	private String userEmail;
	private String userPw;
	private String userTel;
	private String userUniname;
	
	@OneToOne
	@JoinColumn(name = "comId")
	private Company company;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<EquipmentReservation> equipmentReservation;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<BusinessReservation> businessReservation;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="registerId")
	private List<Board> board;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="registerId")
	private List<FileBoard> fileBoard;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="registerId")
	private List<OpeninoBoard> openIno;
	
	
	public static Member createComMember(Company company,COM_MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setCompany(company);
		member.setUserId(memberFormDto.getComuserId());
		member.setUserName(memberFormDto.getComuserName());
		member.setUserEmail(memberFormDto.getComuserEmail());
		String password = passwordEncoder.encode(memberFormDto.getComuserPw());
		member.setUserPw(password);
		member.setUserUniname(memberFormDto.getComuserUniname());
		member.setUserTel(memberFormDto.getComuserTel());
		if(memberFormDto.getComuserId().equals("admin")) {
			member.setRole(Role.ADMIN);
		}else {
			member.setRole(Role.COM);
		}
		return member;
	}
	
	public static Member createStuMember(STU_MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setUserId(memberFormDto.getStuuserId());
		member.setUserName(memberFormDto.getStuuserName());
		member.setUserEmail(memberFormDto.getStuuserEmail());
		String password = passwordEncoder.encode(memberFormDto.getStuuserPw());
		member.setUserPw(password);
		member.setUserTel(memberFormDto.getStuuserTel());
		member.setUserUniname(memberFormDto.getStuuserUniname());
		if(memberFormDto.getStuuserId().equals("admin")) {
			member.setRole(Role.ADMIN);
		}else {
			member.setRole(Role.STU);
		}
		return member;
	}
	
	public static Member modifyMember(MemberUpdateDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setUserName(memberFormDto.getUserName());
		String password = passwordEncoder.encode(memberFormDto.getUserPw());
		member.setUserPw(password);
		member.getUpdateTime();
		return member;
	}
}
