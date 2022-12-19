package com.sglink.member.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.business.dto.BusinessResponseDto;
import com.sglink.common.constant.Process;
import com.sglink.entity.Business;
import com.sglink.entity.BusinessReservation;
import com.sglink.entity.Equipment;
import com.sglink.entity.EquipmentReservation;
import com.sglink.entity.Member;
import com.sglink.equipment.dto.EquipmentResponseDto;
import com.sglink.member.dto.BusinessReservationResponseDto;
import com.sglink.member.dto.EquipmentReservationResponseDto;
import com.sglink.repository.BusinessRepository;
import com.sglink.repository.BusinessReservationRepository;
import com.sglink.repository.EquipmentRepository;
import com.sglink.repository.EquipmentReservationRepository;
import com.sglink.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
	private final MemberRepository memberRepository;
	private final EquipmentRepository equipmentRepository;
	private final BusinessRepository businessRepository;
	private final EquipmentReservationRepository equipmentReservationRepository;
	private final BusinessReservationRepository businessReservationRepository;
	

	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}
	
	public int updateMember(Member member,String userId) {
		duplicateMember(member, userId);
		return memberRepository.updateMember(member,userId);
	}
	
	public void deleteMember(String userId) {
		memberRepository.deleteById(userId);
		 equipmentRepository.deleteAllByEquiRegisterId(userId); 
		businessRepository.deleteAllByBusiRegisterId(userId); 
	}

	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByUserEmail(member.getUserEmail());
		Member userId = memberRepository.findByUserId(member.getUserId());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다."); // 이미 가입된 회원의 경우 예외를 발생시킨다.
		}else if(userId != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	private void duplicateMember(Member member ,String userId) {
		String userPw = memberRepository.duplicatePw(userId, member.getUserPw());
		String userName = memberRepository.duplicateName(userId, member.getUserName());
		if (userPw != null) {
			throw new IllegalStateException("비밀번호가 이전과 동일합니다."); 
		}else if(userName != null) {
			throw new IllegalStateException("이름이 이전과 동일합니다.");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member member = memberRepository.findByUserId(id);
		if (member == null) {
			throw new UsernameNotFoundException(id);
		}
		return User.builder().username(member.getUserId()).password(member.getUserPw())
				.roles(member.getRole().toString()).build();
	}
	
	public String getUserId(Principal pirncipal) {
		return pirncipal.getName();	
	}
	
	public Member getMember(String userId) {
		return memberRepository.getById(userId);
	}
	
	public Member findbyId(String id) {
		return memberRepository.findByUserId(id);
	}
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> selectEquipmentReservation(String UserId,Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<EquipmentReservation> list = equipmentReservationRepository
				.findByEquiRegisterId(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate")), UserId);

		resultMap.put("list", list.stream().map(EquipmentReservationResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}
	
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> MyselectEquipmentReservation(Member member,Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<EquipmentReservation> list = equipmentReservationRepository
				.findByMember(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate")), member);

		resultMap.put("list", list.stream().map(EquipmentReservationResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> selectEquipment(String userId,Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<Equipment> list = equipmentRepository
				.findByEquiRegisterIdAndProcess(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")),userId,Process.APPROVE);

		resultMap.put("list", list.stream().map(EquipmentResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}
	
	public void approveEquipmentReservation(Long id, String equiProcess) {
		if (equiProcess.equals("UNAPPROVE")) {
			String process = "APPROVE";
			equipmentReservationRepository.updateEquipmentReservationProcess(id, process);
		} else if (equiProcess.equals("APPROVE")) {
			String process = "UNAPPROVE";
			equipmentReservationRepository.updateEquipmentReservationProcess(id, process);
		}

	}
	
	public void possibleEquipment(String equiId, String reservation) {
		if (reservation.equals("IMPOSSIBLE")) {
			String process = "POSSIBLE";
			equipmentRepository.updateEquipmentReservation(equiId, process);
		} else if (reservation.equals("POSSIBLE")) {
			String process = "IMPOSSIBLE";
			equipmentRepository.updateEquipmentReservation(equiId, process);
		}

	}
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> selectBusinessReservation(String UserId ,Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<BusinessReservation> list = businessReservationRepository
				.findByBusiRegisterId(PageRequest.of(page, size), UserId);

		resultMap.put("list", list.stream().map(BusinessReservationResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}

	
	@Transactional(readOnly = true)
	public HashMap<String, Object> MyselectBusinessReservation(Member member,Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<BusinessReservation> list = businessReservationRepository
				.findByMember(PageRequest.of(page, size), member);

		resultMap.put("list", list.stream().map(BusinessReservationResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> selectBusiness(String userId,Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Page<Business> list = businessRepository
				.findByBusiRegisterIdAndProcess(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")),userId,Process.APPROVE);

		resultMap.put("list", list.stream().map(BusinessResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}
	
	public void approveBusinessReservation(Long id, String busiProcess) {
		if (busiProcess.equals("UNAPPROVE")) {
			String process = "APPROVE";
			businessReservationRepository.updateBusinessReservationProcess(id, process);
		} else if (busiProcess.equals("APPROVE")) {
			String process = "UNAPPROVE";
			businessReservationRepository.updateBusinessReservationProcess(id, process);
		}

	}
	
	public void possibleBusiness(String busiId, String reservation) {
		if (reservation.equals("IMPOSSIBLE")) {
			String process = "POSSIBLE";
			businessRepository.updateBusinessReservation(busiId, process);
		} else if (reservation.equals("POSSIBLE")) {
			String process = "IMPOSSIBLE";
			businessRepository.updateBusinessReservation(busiId, process);
		}

	}
	
}
