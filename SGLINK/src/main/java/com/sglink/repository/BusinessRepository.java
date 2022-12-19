package com.sglink.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sglink.common.constant.Process;
import com.sglink.entity.Business;

public interface BusinessRepository extends JpaRepository<Business, String> {

	Business findOneByBusiId(String busiId);
	
	static String updateBusinessProcess = "update business set process = :busiProcess where busi_id=:busiId";
	
	static String updateBusinessReservation = "update business set reservation = :reservation where busi_id=:busiId";

	@Transactional
	@Modifying
	@Query(value = updateBusinessReservation, nativeQuery = true)
	void updateBusinessReservation(@Param("busiId") String busiId, @Param("reservation") String reservation);
	

	@Transactional
	@Modifying
	@Query(value = updateBusinessProcess, nativeQuery = true)
	void updateBusinessProcess(@Param("busiId") String busiId, @Param("busiProcess") String business);

	Page<Business> findByProcess(Pageable pageable,Process process);
	
	Optional<Business> findByBusiIdAndProcess(String id, Process process);
	
	Page<Business> findByBusiRegisterIdAndProcess(Pageable pageable,String userid,Process process);
	int deleteAllByBusiRegisterId(String userId);
}