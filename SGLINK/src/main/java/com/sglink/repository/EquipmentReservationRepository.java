package com.sglink.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sglink.entity.EquipmentReservation;
import com.sglink.entity.Member;

public interface EquipmentReservationRepository extends JpaRepository<EquipmentReservation, Long> {

	static String updateEquipmentReservationProcess = "update equipment_Reservation set process = :process where id=:id";

	@Transactional
	@Modifying
	@Query(value = updateEquipmentReservationProcess, nativeQuery = true)
	void updateEquipmentReservationProcess(@Param("id") Long id, @Param("process") String equiProcess);

	Page<EquipmentReservation> findByMember(Pageable pageable, Member member);
	Page<EquipmentReservation> findByEquiRegisterId(Pageable pageable, String UserId);
}
