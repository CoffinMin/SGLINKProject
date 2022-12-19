package com.sglink.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.sglink.common.constant.Reservation;
import com.sglink.common.constant.Process;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class BusinessReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String busiId;

	@Column(nullable = false)
	private String busiName;

	@Column(nullable = false)
	private String busiRegisterId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;

	@Enumerated(EnumType.STRING)
	private Process process;

	private String startDate;
	private String endDate;

	@Builder
	public BusinessReservation(String busiId, String busiName, Member member, String startDate, String endDate,
			Process process, String busiRegisterId) {

		this.busiId = busiId;
		this.busiName = busiName;
		this.member = member;
		this.process = process;
		this.startDate = startDate;
		this.endDate = endDate;
		this.busiRegisterId = busiRegisterId;
	}

	@PrePersist
	public void prePersist() {
		this.process = this.process == null ? Process.UNAPPROVE : this.process;
	}

}
