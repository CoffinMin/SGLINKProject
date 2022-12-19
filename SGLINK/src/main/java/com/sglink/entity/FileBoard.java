package com.sglink.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file_board")
public class FileBoard extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = true, length = 6000)
	private String content;
	private int readCnt;

	@ManyToOne
	@JoinColumn(name = "registerId")
	private Member member;

	@OneToMany(mappedBy = "fileBoard",cascade = CascadeType.ALL)
	private List<FileEntity> file;

	@Builder
	public FileBoard(Long id, String title, String content, Member member, List<FileEntity> file, int readCnt) {

		this.id = id;
		this.title = title;
		this.content = content;
		this.member = member;
		this.file = file;
		this.readCnt = readCnt;
	}

}
