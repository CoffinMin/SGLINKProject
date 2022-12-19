package com.sglink.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sglink.entity.FileEntity;

public interface FileRepository  extends JpaRepository<FileEntity, Long>{
	
	String delete = "delete from file_entity where file_board_id =:id";
	String deleteAll = "delete from file_entity where file_board_id in (:deleteList)";

	@Transactional
	@Modifying
	@Query(value=delete,nativeQuery = true)
	void deleteFileByBoardId(@Param("id")Long id);
	
	
	@Transactional
	@Modifying
	@Query(value=deleteAll,nativeQuery = true)
	void deleteAllFileByBoardId(@Param("deleteList")Long[] deleteId);
	
	
}
