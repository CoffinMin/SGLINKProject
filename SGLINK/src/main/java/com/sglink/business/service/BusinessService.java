package com.sglink.business.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.business.dto.BusinessRequestDto;
import com.sglink.business.dto.BusinessResponseDto;
import com.sglink.common.constant.Process;
import com.sglink.entity.Business;
import com.sglink.member.dto.BusinessReservationRequestDto;
import com.sglink.repository.BusinessRepository;
import com.sglink.repository.BusinessReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BusinessService {

	private final BusinessRepository businessRepository;
	private final BusinessReservationRepository businessReservationRepository;

	public Business findByBusiId(String busiId) {
		return businessRepository.findOneByBusiId(busiId);
	}

	@Transactional
	public String save(BusinessRequestDto businessRequestDto) {
		return businessRepository.save(businessRequestDto.toEntity()).getBusiId();
	}
	
	@Transactional
	public void save(BusinessReservationRequestDto errDto) {
		businessReservationRepository.save(errDto.toEntity());
	}

	@Transactional(readOnly = true)
	public HashMap <String, Object> findAll(Integer page, Integer size){
		HashMap<String, Object> resultMap= new HashMap<String, Object>();	
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Business> list= businessRepository.findByProcess(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")),Process.APPROVE);
		resultMap.put("list", list.stream().map(BusinessResponseDto::new).collect(Collectors.toList()));
		System.out.println(list.stream().map(BusinessResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());	
		return resultMap;	
	}
	public Optional<Business> viewfindById(String id) {
		return businessRepository.findById(id);
	}
	public BusinessResponseDto findById(String id) {
		return new BusinessResponseDto(businessRepository.findByBusiIdAndProcess(id,Process.APPROVE).get());
	}
}