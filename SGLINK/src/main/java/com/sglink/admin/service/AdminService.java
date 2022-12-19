package com.sglink.admin.service;

import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.admin.dto.CompanyResponseDto;
import com.sglink.admin.dto.EquipmentResponseDto;
import com.sglink.admin.dto.MemberResponseDto;
import com.sglink.admin.dto.BusinessResponseDto;
import com.sglink.entity.Business;
import com.sglink.entity.Company;
import com.sglink.entity.Equipment;
import com.sglink.entity.Member;
import com.sglink.repository.BusinessRepository;
import com.sglink.repository.CompanyRepository;
import com.sglink.repository.EquipmentRepository;
import com.sglink.repository.FileBoardRepository;
import com.sglink.repository.MemberRepository;
import com.sglink.repository.NoticeBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

	private final MemberRepository memberRepository;
	private final CompanyRepository companyRepository;
	private final NoticeBoardRepository noticeBoardRepository;
	private final FileBoardRepository fileBoardRepository;
	private final EquipmentRepository equipmentRepository;
	private final BusinessRepository businessRepository;

	@Transactional(readOnly = true)
	public HashMap<String, Object> selectAllMember(Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();

//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Member> list = memberRepository
				.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")));

		resultMap.put("list", list.stream().map(MemberResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}

	@Transactional 
	public void deleteMember(String userId) {
		memberRepository.deleteById(userId);
		equipmentRepository.deleteAllByEquiRegisterId(userId);
		businessRepository.deleteAllByBusiRegisterId(userId);
	}

	@Transactional(readOnly = true)
	public HashMap<String, Object> selectAllCompany(Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Company> list = companyRepository
				.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")));

		resultMap.put("list", list.stream().map(CompanyResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}

	public void approveCompany(String comId, String comProcess) {
		if (comProcess.equals("UNAPPROVE")) {
			String process = "APPROVE";
			companyRepository.updateCompanyProcess(comId, process);
		} else if (comProcess.equals("APPROVE")) {
			String process = "UNAPPROVE";
			companyRepository.updateCompanyProcess(comId, process);
		}

	}

	@Transactional(readOnly = true)
	public HashMap<String, Object> selectAllEquipment(Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Equipment> list = equipmentRepository
				.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")));

		resultMap.put("list", list.stream().map(EquipmentResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}

	public void approveEquipment(String equiId, String equiProcess) {
		if (equiProcess.equals("UNAPPROVE")) {
			String process = "APPROVE";
			equipmentRepository.updateEquipmentProcess(equiId, process);
		} else if (equiProcess.equals("APPROVE")) {
			String process = "UNAPPROVE";
			equipmentRepository.updateEquipmentProcess(equiId, process);
		}

	}

	@Transactional(readOnly = true)
	public HashMap<String, Object> selectAllBusiness(Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Business> list = businessRepository
				.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")));

		resultMap.put("list", list.stream().map(BusinessResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;

	}

	public void approveBusiness(String busiId, String busiProcess) {
		if (busiProcess.equals("UNAPPROVE")) {
			String process = "APPROVE";
			businessRepository.updateBusinessProcess(busiId, process);
		} else if (busiProcess.equals("APPROVE")) {
			String process = "UNAPPROVE";
			businessRepository.updateBusinessProcess(busiId, process);
		}

	}

}
