package com.sglink;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class SglinkApplicationTests {

	@Test
	void contextLoads() {
	}
	
//	공지게시판 테스트코드
	
	
//	@Autowired
//	private NoticeBoardService boardService;
//	
//	@Test
//	void save() {
//		NoticeBoardRequestDto boardSaveDto = new NoticeBoardRequestDto();
//		
//		boardSaveDto.setTitle("제목입니다");
//		boardSaveDto.setContent("내용입니다");
//		boardSaveDto.setRegisterId("작성자");
//		
//		Long result = boardService.save(boardSaveDto);
//		
//		if(result >0) {
//			System.out.println("세이브성공___");
//			findAll();
//			findById(result);
//			
//		}else {
//			System.out.println("세이브 실패___");
//		}
//	}
//	
//	void findAll() {
//		List <NoticeBoardResponseDto> list = boardService.findAll();
//		
//		if(list!=null) {
//			System.out.println("findAll 성공"+list.toString());
//		}else {
//			System.out.println("findAll 실패");
//		}
//	}
//	
//	void findById(Long id) {
//		NoticeBoardResponseDto info = boardService.findById(id);
//		
//		if(info != null) {
//			System.out.println("findById 성공"+ info.toString());
//			updateBoard(id);
//		}else {
//			System.out.println("findById 실패");
//		}
//	}
//	void updateBoard(Long id) {
//		NoticeBoardRequestDto boardRequestDto = new NoticeBoardRequestDto();
//		
//		boardRequestDto.setId(id);
//		boardRequestDto.setTitle("업데이트 제목");
//		boardRequestDto.setContent("업데이트 내용");
//		boardRequestDto.setRegisterId("작성자");
//		
//		int result = boardService.updateBoard(boardRequestDto);
//		
//		if (result>0) {
//			System.out.println("updataBoard 성공");
//		}else {
//			System.out.println("updataBoard 실패");
//		}
//	}

}
