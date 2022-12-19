package com.sglink.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.sglink.common.constant.Process;
import com.sglink.common.constant.Reservation;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
public class Equipment extends BaseTimeEntity {

	@Id
	private String equiId;

	@Column(nullable = false)
	private String equiName;

	private String equiUniname;
	@Column(nullable = true, length = 6000)
	private String equiContent;

	private String equiRegisterId;
	
	private String equiRegisterName;

	private String equiTel;

	@Enumerated(EnumType.STRING)
	private Process process;

	@Enumerated(EnumType.STRING)
	private Reservation reservation;

	@OneToMany(mappedBy = "img",cascade = CascadeType.ALL)
	private List<FileEntity> img;

	@Builder
	public Equipment(String equiId, String equiName, String equiUniname, String equiContent, String equiRegisterId,String equiRegisterName,
			String equiTel, List<FileEntity> img) {

		this.equiId = equiId;
		this.equiName = equiName;
		this.equiRegisterId = equiRegisterId;
		this.equiRegisterName = equiRegisterName;
		this.equiTel = equiTel;
		this.equiUniname = equiUniname;
		this.equiContent = equiContent;
		this.img = img;
	}

	@PrePersist
	public void prePersist() {
		this.reservation = this.reservation == null ? Reservation.IMPOSSIBLE : this.reservation;
		this.process = this.process == null ? Process.UNAPPROVE : this.process;
	}

}
