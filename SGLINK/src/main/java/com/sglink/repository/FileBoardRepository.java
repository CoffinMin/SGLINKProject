package com.sglink.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sglink.board.dto.FileBoardRequestDto;
import com.sglink.entity.FileBoard;

@Repository
public interface FileBoardRepository extends JpaRepository<FileBoard, Long> {
	
	FileBoard findOneById(Long id);
	
	static final String UPDATE_FILEBOARD = "UPDATE file_board "
			+ "SET TITLE = :#{#fileBoardRequestDto.title}, "
			+ "CONTENT = :#{#fileBoardRequestDto.content}, "
			+ "REGISTER_ID = :#{#fileBoardRequestDto.member}, "
			+ "UPDATE_TIME = SYSDATE() "
			+ "WHERE ID = :#{#fileBoardRequestDto.id}";
	
	static final String UPDATE_FILEBOARD_READ_CNT_INC = "UPDATE file_board "
			+ "SET READ_CNT = READ_CNT + 1 "
			+ "WHERE ID = :id";
	
	static final String DELETE_FILEBOARD = "DELETE FROM file_board "
			+ "WHERE ID IN (:deleteList)";
	
	@Transactional
	@Modifying
	@Query (value = UPDATE_FILEBOARD, nativeQuery = true)
	public int updateBoard(@Param("fileBoardRequestDto") FileBoardRequestDto boardRequestDto);
	
	@Transactional
	@Modifying
	@Query (value = UPDATE_FILEBOARD_READ_CNT_INC, nativeQuery = true)
	public int updateFileBoardReadCntInc(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = DELETE_FILEBOARD, nativeQuery = true)
	public int deleteFileBoard(@Param("deleteList") Long[]deleteList);
	
	
	

}
