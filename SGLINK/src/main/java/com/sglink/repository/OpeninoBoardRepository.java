package com.sglink.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sglink.board.dto.FileBoardRequestDto;
import com.sglink.board.dto.OpeninoBoardRequestDto;

import com.sglink.entity.OpeninoBoard;

@Repository
public interface OpeninoBoardRepository extends JpaRepository<OpeninoBoard, Long> {

	static final String UPDATE_OPENINO = "UPDATE openino "
			+ "SET TITLE = :#{#openinoRequestDto.title}, "
			+ "CONTENT = :#{#openinoRequestDto.content}, "
			+ "REGISTER_ID = :#{#openinoRequestDto.member}, "
			+ "UPDATE_TIME = SYSDATE() "
			+ "WHERE ID = :#{#openinoRequestDto.id}";
			
	static final String UPDATE_OPENINO_READ_CNT_INC = "UPDATE openino "
			+ "SET READ_CNTS = READ_CNTS + 1 "
			+ "WHERE ID = :id";

	static final String DELETE_OPENINO = "DELETE FROM openino " + "WHERE ID IN (:deleteList)";
	
	static final String OPENINO_BOARD = "SELECT boardName FROM board "
			+ "WHERE BOARD_NAME = OpeninoBoard";
	
	public Page<OpeninoBoard> findByTitleContaining(Pageable pageable, String searchKeyword);
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_OPENINO, nativeQuery = true)
	public int updateBoard(@Param("openinoRequestDto") OpeninoBoardRequestDto openinoRequestDto);

	@Transactional
	@Modifying
	@Query(value = UPDATE_OPENINO_READ_CNT_INC, nativeQuery = true)
	public int updateOpeninoBoardReadCntInc(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = DELETE_OPENINO, nativeQuery = true)
	public int deleteOpeninoBoard(@Param("deleteList") Long[] deleteList);

}
