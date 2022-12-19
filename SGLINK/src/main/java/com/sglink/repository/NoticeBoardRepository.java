package com.sglink.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.board.dto.NoticeBoardRequestDto;
import com.sglink.entity.Board;
import com.sglink.entity.Member;

@Repository
public interface NoticeBoardRepository extends JpaRepository<Board, Long>{
	@Transactional
	int deleteByMember(Member member);

	
	static final String UPDATE_BOARD = "UPDATE board "
			+ "SET TITLE = :#{#boardRequestDto.title}, "
			+ "CONTENT = :#{#boardRequestDto.content}, "
			+ "REGISTER_ID = :#{#boardRequestDto.member}, "
			+ "UPDATE_TIME = SYSDATE() "
			+ "WHERE ID = :#{#boardRequestDto.id}";
	
	static final String UPDATE_BOARD_READ_CNT_INC = "UPDATE board "
			+ "SET READ_CNT = READ_CNT + 1 "
			+ "WHERE ID = :id";
	
	static final String DELETE_BOARD = "DELETE FROM board "
			+ "WHERE ID IN (:deleteList)";
	
	static final String NOTICE_BOARD = "SELECT boardName FROM board "
			+ "WHERE BOARD_NAME = NoticeBoard";
	
	
	
	public Page<Board> findByTitleContaining(Pageable pageable, String searchKeyword);
		
	
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD, nativeQuery = true)
	public int updateBoard(@Param("boardRequestDto") NoticeBoardRequestDto boardRequestDto);
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD_READ_CNT_INC, nativeQuery = true)
	public int updateBoardReadCntInc(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = DELETE_BOARD, nativeQuery = true)
	public int deleteBoard(@Param("deleteList") Long[] deleteList);
}
