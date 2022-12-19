package com.sglink.board.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sglink.board.dto.FileBoardRequestDto;
import com.sglink.board.dto.FileBoardResponseDto;
import com.sglink.board.dto.NoticeBoardRequestDto;
import com.sglink.board.dto.NoticeBoardResponseDto;
import com.sglink.board.dto.OpeninoBoardRequestDto;
import com.sglink.board.dto.OpeninoBoardResponseDto;
import com.sglink.business.dto.BusinessResponseDto;
import com.sglink.entity.Board;
import com.sglink.entity.Business;
import com.sglink.entity.FileBoard;
import com.sglink.entity.OpeninoBoard;
import com.sglink.repository.BusinessRepository;
import com.sglink.repository.FileBoardRepository;
import com.sglink.repository.FileRepository;
import com.sglink.repository.MemberRepository;
import com.sglink.repository.NoticeBoardRepository;
import com.sglink.repository.OpeninoBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final NoticeBoardRepository noticeboardRepository;
	private final FileBoardRepository fileboardRepository;
	private final FileRepository fileRepository;
	private final OpeninoBoardRepository openinoboardRepository;
	private final MemberRepository memberRepository;
	private final BusinessRepository businessRepository;
	
	
	public FileBoard boardfindById(Long id) {
		return fileboardRepository.findOneById(id);
		
	}
	
	
	@Transactional
	public HashMap<String, Object> findByTotalTitleContaining(Integer page, Integer size, String searchKeyword){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		Page<Board> list= noticeboardRepository.findByTitleContaining(pageable, searchKeyword);
		Page<OpeninoBoard> list1= openinoboardRepository.findByTitleContaining(pageable, searchKeyword);
		
		
		resultMap.put("list1", list1.stream().map(OpeninoBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging1", list1.getPageable());
		resultMap.put("totalCnt1", list1.getTotalElements());
		resultMap.put("totalPage1", list1.getTotalPages());
		
		
		
		resultMap.put("list", list.stream().map(NoticeBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());
		
		return resultMap;
	}
	
	
//	메인페이지 보드출력
	@Transactional(readOnly = true)
	public HashMap <String, Object> findAllBoard(Integer page, Integer size){
		HashMap<String, Object> resultMap= new HashMap<String, Object>();	
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Board> list= noticeboardRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")));
		Page<OpeninoBoard> list1= openinoboardRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")));
		Page<Business> list2= businessRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")));
		
		resultMap.put("list2", list2.stream().map(BusinessResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging2", list2.getPageable());
		resultMap.put("totalCnt2", list2.getTotalElements());
		resultMap.put("totalPage2", list2.getTotalPages());
		
		resultMap.put("list1", list1.stream().map(OpeninoBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging1", list1.getPageable());
		resultMap.put("totalCnt1", list1.getTotalElements());
		resultMap.put("totalPage1", list1.getTotalPages());
		
		resultMap.put("list", list.stream().map(NoticeBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());	
		return resultMap;	
	}
	
	
//	공지게시판 기능구현--------------------------------------------
	
	
	@Transactional
	public Long save(NoticeBoardRequestDto boardSaveDto) {
		return noticeboardRepository.save(boardSaveDto.toEntity()).getId();
	}
	
	@Transactional(readOnly = true)
	public HashMap <String, Object> findAll(Integer page, Integer size){
		HashMap<String, Object> resultMap= new HashMap<String, Object>();	
//		게시글 순서를 내림차순으로 변경Sort.by(Sort.Direncion.DESC,"registerTime")
		Page<Board> list= noticeboardRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")));
		
		resultMap.put("list", list.stream().map(NoticeBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());	
		return resultMap;	
	}
	
	@Transactional
	public HashMap<String, Object> findByTitleContaining(Integer page, Integer size, String searchKeyword){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		Page<Board> list= noticeboardRepository.findByTitleContaining(pageable, searchKeyword);
		
		resultMap.put("list", list.stream().map(NoticeBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());
		
		return resultMap;
	}
	

	public NoticeBoardResponseDto findById(Long id) {
		noticeboardRepository.updateBoardReadCntInc(id);
		return new NoticeBoardResponseDto(noticeboardRepository.findById(id).get());
	}
	
	public Optional<Board> viewfindById(Long id) {
		return noticeboardRepository.findById(id);
	}
	
	public int updateBoard(NoticeBoardRequestDto boardRequestDto) {
		return noticeboardRepository.updateBoard(boardRequestDto);
	}
	
	public int updateBoardReadCntInc(Long id) {
		return noticeboardRepository.updateBoardReadCntInc(id);
	}
	
	public void deleteById(Long id) {
		noticeboardRepository.deleteById(id);
	}
	
	public void deleteAll(Long[] deleteId) {
		noticeboardRepository.deleteBoard(deleteId);
	}
	
	
	
	
//  자료실게시판기능구현---------------------------------
	@Transactional
	public Long save(FileBoardRequestDto fileboardSaveDto) {
		return fileboardRepository.save(fileboardSaveDto.toEntity()).getId();
	}
	
	public FileBoard findByFileId(Long id) {
		
		return fileboardRepository.findOneById(id);
	}
	
	@Transactional(readOnly = true)
	public HashMap <String, Object> findAllFile(Integer page, Integer size){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		Page<FileBoard> list= fileboardRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")));
		
		resultMap.put("list",list.stream().map(FileBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt",list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());
		
		return resultMap;
	}
	
	public FileBoardResponseDto filefindById(Long id) {
		fileboardRepository.updateFileBoardReadCntInc(id);
		return new FileBoardResponseDto(fileboardRepository.findById(id).get());
	}
	
	public Optional<FileBoard> fileviewfindById(Long id){
		return fileboardRepository.findById(id);
	}
	
	public int updatefileBoard(FileBoardRequestDto boardRequestDto) {
		return fileboardRepository.updateBoard(boardRequestDto);
	}
	
	public int updatefileBoardReadCntInc(Long id) {
		return fileboardRepository.updateFileBoardReadCntInc(id);
	}
	
	public void filedeleteById(Long id) {
		fileRepository.deleteFileByBoardId(id);
		fileboardRepository.deleteById(id);
	}
	
	public void filedeleteAll(Long[] deleteId) {
		fileRepository.deleteAllFileByBoardId(deleteId);
		fileboardRepository.deleteFileBoard(deleteId);
	}
//	자유게시판기능구현=================================
	
	
// Openino게시판	
	
	@Transactional
	public Long save(OpeninoBoardRequestDto openinoboardSaveDto) {
		return openinoboardRepository.save(openinoboardSaveDto.toEntity()).getId();
	}

	@Transactional(readOnly = true)
	public HashMap <String, Object> openinoFindAll(Integer page, Integer size){
		HashMap<String, Object> resultMap= new HashMap<String, Object>();	

		Page<OpeninoBoard> list= openinoboardRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"registerTime")));
		resultMap.put("list", list.stream().map(OpeninoBoardResponseDto::new).collect(Collectors.toList()));
		resultMap.put("paging", list.getPageable());
		resultMap.put("totalCnt", list.getTotalElements());
		resultMap.put("totalPage", list.getTotalPages());	
		return resultMap;	
	}	
	

	public OpeninoBoardResponseDto openinofindById(Long id) {
		openinoboardRepository.updateOpeninoBoardReadCntInc(id);
		return new OpeninoBoardResponseDto(openinoboardRepository.findById(id).get());
	}
	
	public Optional<OpeninoBoard> openinoviewfindById(Long id) {
		return openinoboardRepository.findById(id);
	}
	
	public int updateBoard(OpeninoBoardRequestDto openinoRequestDto) {
		return openinoboardRepository.updateBoard(openinoRequestDto);
	}
	
	public int updateopeninoBoardReadCntInc(Long id) {
		return openinoboardRepository.updateOpeninoBoardReadCntInc(id);
	}
	
	public void openinoDeleteById(Long id) {
		openinoboardRepository.deleteById(id);
	}
	
	public void openinoDeleteAll(Long[] deleteId) {
		openinoboardRepository.deleteOpeninoBoard(deleteId);
	}
	
}

