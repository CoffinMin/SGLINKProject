package com.sglink.common.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sglink.common.constant.Process;
import com.sglink.admin.dto.CompanyResponseDto;
import com.sglink.common.dto.PageViewRequestDto;
import com.sglink.common.dto.PageViewResponsetDto;
import com.sglink.entity.Company;
import com.sglink.entity.Introduce;
import com.sglink.repository.CompanyRepository;
import com.sglink.repository.IntroduceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PageViewService {
	private final IntroduceRepository introduceRepository;
	private final CompanyRepository companyRepository;
	
	
	@Transactional
	public Long save(PageViewRequestDto boardSaveDto) {
		return introduceRepository.save(boardSaveDto.toEntity()).getId();
	}
	

	public PageViewResponsetDto findById(Long id) {
		return new PageViewResponsetDto(introduceRepository.findById(id).get());
	}
	
	public Optional<Introduce> viewfindById(Long id) {
		return introduceRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public HashMap<String, Object> selectCompanyByProcess(Integer page, Integer size) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Company> list = companyRepository
				.findByProcess(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registerTime")),Process.APPROVE);

		resultMap.put("list", list.stream().map(CompanyResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());

		return resultMap;
	}

}
