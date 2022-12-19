package com.sglink.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.sglink.entity.Introduce;


@Repository
public interface IntroduceRepository extends JpaRepository<Introduce, Long>{

	
//	static final String UPDATE_BOARD = "UPDATE board "
//			+ "SET TITLE = :#{#boardRequestDto.title}, "
//			+ "CONTENT = :#{#boardRequestDto.content}, "
//			+ "REGISTER_ID = :#{#boardRequestDto.member}, "
//			+ "UPDATE_TIME = SYSDATE() "
//			+ "WHERE ID = :#{#boardRequestDto.id}";
//	
//	
//	
//	
//	@Transactional
//	@Modifying
//	@Query(value = UPDATE_BOARD, nativeQuery = true)
//	public int updateBoard(@Param("boardRequestDto") NoticeBoardRequestDto boardRequestDto);
	

}
