package com.sglink.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sglink.common.constant.Process;
import com.sglink.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, String> {

	Equipment findOneByEquiId(String equiId);

	static String updateEquipmentProcess = "update equipment set process = :equiProcess where equi_id=:equiId";
	
	static String updateEquipmentReservation = "update equipment set reservation = :reservation where equi_id=:equiId";

	@Transactional
	@Modifying
	@Query(value = updateEquipmentProcess, nativeQuery = true)
	void updateEquipmentProcess(@Param("equiId") String equiId, @Param("equiProcess") String equiProcess);
	
	
	@Transactional
	@Modifying
	@Query(value = updateEquipmentReservation, nativeQuery = true)
	void updateEquipmentReservation(@Param("equiId") String equiId, @Param("reservation") String reservation);
	
	Page<Equipment> findByProcess(Pageable pageable,Process process);
	
	Page<Equipment> findByEquiRegisterIdAndProcess(Pageable pageable,String userid,Process process);
	
	Optional<Equipment> findByEquiIdAndProcess(String id, Process process);
	
	int deleteAllByEquiRegisterId(String userId);
}