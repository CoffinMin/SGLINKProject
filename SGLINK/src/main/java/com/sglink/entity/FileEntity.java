package com.sglink.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@ToString(exclude = "img")
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue
    private Long ID;
    // 원본 파일이름 과 서버에 저장된 파일 경로 를 분리한 이유?
    // 동일한 이름을 가진 파일이 업로드가 된다면 오류가 생긴다.
    // 이를 해결하기 위함
    @NotEmpty
    private String original_file_name;
    @NotEmpty
    private String stored_file_path;

    private long file_size;
    
    private String icon;
    @ManyToOne
    @JoinColumn
    private FileBoard fileBoard;
    
    
    @ManyToOne
    @JoinColumn
    private Equipment img;
    
    @ManyToOne
    @JoinColumn
    private Business busiImg;
}
