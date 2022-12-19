package com.sglink.board.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sglink.board.dto.FileBoardRequestDto;
import com.sglink.board.dto.NoticeBoardRequestDto;
import com.sglink.board.dto.OpeninoBoardRequestDto;
import com.sglink.board.service.BoardService;
import com.sglink.entity.Board;
import com.sglink.entity.FileBoard;

import com.sglink.entity.OpeninoBoard;

import com.sglink.file.service.FileUploadService;
import com.sglink.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/boards")
@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService boardService;
	private final MemberService memberService;
	private final FileUploadService fileUploadService;
//통합검색
	
	@GetMapping("/total/list")
	public String gettotalBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "100") Integer size, @RequestParam(value="searchKeyword", required = false) String searchKeyword) throws Exception {
		
		if(searchKeyword ==null) {
			try {
				model.addAttribute("resultMap", boardService.findAll(page, size));
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}else {
//			model.addAttribute("resultMap", boardService.findByTitleContaining(page, size, searchKeyword ));
			model.addAttribute("resultMap", boardService.findByTotalTitleContaining(page, size, searchKeyword));
			
		}
		
		return "/board/board/totalList";
	}
	
	@GetMapping("/total/delete")
	public String totalBoardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {

		try {
			boardService.deleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/total/list";
	}
	
	

	
	
	
//	 공지사항게시판
//	@RequestParam(required = false, defaultValue= "10")  defaultValue= "10"<-숫자 변경시 페이지당 게시글숫자달라짐
	@GetMapping("/notice/list")
	public String getnoticeBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(value="searchKeyword", required = false) String searchKeyword) throws Exception {
		
		if(searchKeyword ==null) {
			try {
				model.addAttribute("resultMap", boardService.findAll(page, size));
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}else {
			model.addAttribute("resultMap", boardService.findByTitleContaining(page, size, searchKeyword ));
		}
		
		return "/board/board/noticeList";
	}
	
	
	


	@GetMapping("/notice/write")
	public String getnoticeBoardWritePage(Model model, NoticeBoardRequestDto boardRequestDto, Principal principal) {

		String userId = memberService.getUserId(principal);

		model.addAttribute("userId", userId);
		return "/board/board/noticeWrite";
	}

	@GetMapping("/notice/edit")
	public String getnoticeBoardViewPage(@RequestParam("id") Long id, Model model,
			NoticeBoardRequestDto boardRequestDto, Principal principal) throws Exception {
		try {
			if (boardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				Board noticeBoard = boardService.viewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = noticeBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.findById(boardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/noticeEdit";

	}

	@GetMapping("/notice/view")
	public String testgetnoticeBoardViewPage(@RequestParam("id") Long id, Model model,
			NoticeBoardRequestDto boardRequestDto, Principal principal) throws Exception {
		try {
			if (boardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				Board noticeBoard = boardService.viewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = noticeBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.findById(boardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/noticeView";

	}

	@PostMapping("/notice/write/action")
	public String noticeBoardWriteAction(Model model, NoticeBoardRequestDto boardRequestDto) throws Exception {

		try {

			Long result = boardService.save(boardRequestDto);

			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/notice/list";

	}

	@PostMapping("/notice/edit/action")
	public String getBoardViewAction(Model model, @ModelAttribute NoticeBoardRequestDto boardRequestDto)
			throws Exception {

		try {
			int result = boardService.updateBoard(boardRequestDto);

			if (result < 1) {
				throw new Exception("#Exception boardViewAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/notice/list";
	}

	@GetMapping("/notice/view/delete")
	public String noticeBoardViewDeleteAction(Model model, @RequestParam("id") Long id) throws Exception {

		try {
			System.out.println(id);
			boardService.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/notice/list";
	}

	@GetMapping("/notice/delete")
	public String noticeBoardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {

		try {
			boardService.deleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/notice/list";
	}

//	자유게시판======================================
	@GetMapping("/free/list")
	public String getFreeBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {
		try {
			model.addAttribute("resultMap", boardService.findAll(page, size));
	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/board/board/freeList";
	}

	@GetMapping("/free/write")
	public String getFreeBoardWritePage(Model model, NoticeBoardRequestDto boardRequestDto, Principal principal) {

		String userId = memberService.getUserId(principal);

		model.addAttribute("userId", userId);
		return "/board/board/freeWrite";
	}

	@GetMapping("/free/view")
	public String getFreeBoardViewPage(@RequestParam("id") Long id, Model model, NoticeBoardRequestDto boardRequestDto,
			Principal principal) throws Exception {
		try {
			if (boardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				Board noticeBoard = boardService.viewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = noticeBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.findById(boardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/freeView";

	}

	@GetMapping("/free/edit")
	public String getFreeBoardEditPage(@RequestParam("id") Long id, Model model, NoticeBoardRequestDto boardRequestDto,
			Principal principal) throws Exception {
		try {
			if (boardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				Board noticeBoard = boardService.viewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = noticeBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.findById(boardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/freeEdit";

	}

	@PostMapping("/free/write/action")
	public String freeBoardWriteAction(Model model, NoticeBoardRequestDto boardRequestDto) throws Exception {
		try {

			Long result = boardService.save(boardRequestDto);

			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/free/list";

	}

	@PostMapping("/free/edit/action")
	public String getFreeBoardViewAction(Model model, @ModelAttribute NoticeBoardRequestDto boardRequestDto)
			throws Exception {

		try {
			int result = boardService.updateBoard(boardRequestDto);
			if (result < 1) {
				throw new Exception("#Exception boardViewAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/free/list";
	}

	@GetMapping("/free/view/delete")
	public String FreeBoardViewDeleteAction(Model model, @RequestParam("id") Long id) throws Exception {

		try {
			System.out.println(id);
			boardService.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/free/list";
	}

	@GetMapping("/free/delete")
	public String FreeBoardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {

		try {
			boardService.deleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/free/list";
	}

	// 자료실
	// 게시판------------------------------------------------------------------------------------
	@GetMapping("/reference/list")
	public String getfileBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {
		try {
			model.addAttribute("resultMap", boardService.findAllFile(page, size));
			System.out.println(boardService.findAllFile(page, size));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/referenceList";

	}

	@GetMapping("/reference/write")
	public String getfileBoardWritePage(Model model, FileBoardRequestDto boardRequestDto, Principal principal)
			throws Exception {
		String userId = memberService.getUserId(principal);

		model.addAttribute("userId", userId);
		return "/board/board/referenceWrite";
	}

	@GetMapping("/reference/view")
	public String getfileBoardViewPage(@RequestParam("id") Long id, Model model,
			FileBoardRequestDto fileboardRequestDto, Principal principal) throws Exception {
		try {
			if (fileboardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				FileBoard fileBoard = boardService.fileviewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = fileBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.filefindById(fileboardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/referenceView";

	}

	@GetMapping("/reference/edit")
	public String getfileBoardEditPage(@RequestParam("id") Long id, Model model,
			FileBoardRequestDto fileboardRequestDto, Principal principal) throws Exception {
		try {
			if (fileboardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				FileBoard fileBoard = boardService.fileviewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = fileBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.filefindById(fileboardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/referenceEdit";

	}

	@PostMapping("/reference/write/action")
	public String fileboardWriteAction(@RequestParam("files") List<MultipartFile> files, Principal principal,
			Model model, FileBoardRequestDto fileboardRequestDto) throws Exception {
		try {

			Long result = boardService.save(fileboardRequestDto);
			FileBoard fileboard = boardService.findByFileId(result);
			fileUploadService.addFile(files, result, fileboard);

			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/reference/list";

	}

	@PostMapping("/reference/edit/action")
	public String getfileBoardViewAction(Model model, @ModelAttribute FileBoardRequestDto boardRequestDto)
			throws Exception {

		try {
			int result = boardService.updatefileBoard(boardRequestDto);

			if (result < 1) {
				throw new Exception("#Exception boardViewAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/reference/list";
	}

	@GetMapping("/reference/view/delete")
	public String fileBoardViewDeleteAction(Model model, @RequestParam() Long id) throws Exception {

		try {
			boardService.filedeleteById(id);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/reference/list";
	}

	@GetMapping("/reference/delete")
	public String BoardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {

		try {
			boardService.filedeleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/reference/list";
	}

	@ResponseBody
	@GetMapping("/reference/findAll")
	public String boardFindAll(@Param("id") Long id) {
		return boardService.boardfindById(id).getFile().get(0).getIcon();

	}

//openino
	@GetMapping("/openino/list")
	public String getopeninoBoardListPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {
		try {
			model.addAttribute("resultMap", boardService.openinoFindAll(page, size));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/openinoList";

	}

	@GetMapping("/openino/write")
	public String getopeninoBoardWritePage(Model model, OpeninoBoardRequestDto boardRequestDto, Principal principal)
			throws Exception {
		String userId = memberService.getUserId(principal);

		model.addAttribute("userId", userId);
		return "/board/board/openinoWrite";
	}

	@GetMapping("/openino/view")
	public String getopeninoBoardViewPage(@RequestParam("id") Long id, Model model,
			OpeninoBoardRequestDto openinoboardRequestDto, Principal principal) throws Exception {
		try {
			if (openinoboardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				OpeninoBoard openinoBoard = boardService.openinoviewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = openinoBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.openinofindById(openinoboardRequestDto.getId()));
				System.out.println(boardService.openinofindById(openinoboardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/openinoView";

	}

	@GetMapping("/openino/edit")
	public String getopeninoBoardEditPage(@RequestParam("id") Long id, Model model,
			OpeninoBoardRequestDto openinoboardRequestDto, Principal principal) throws Exception {
		try {
			if (openinoboardRequestDto.getId() != null) {
				// 게시판에서 게시판테이블에있는 아이디 파라메서 가져와 그 아이디로 게시판정보 가져오기
				OpeninoBoard openinoBoard = boardService.openinoviewfindById(id).get();
				// 게시판에서 회원테이블을 참조하기 때문에 회원테이블에 있는 값을 가져올 수 있음
				String registerId = openinoBoard.getMember().getUserId();
				// 로그인시 로그인한 회원의 아이디를 가져옴
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", boardService.openinofindById(openinoboardRequestDto.getId()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/board/openinoEdit";

	}

	@PostMapping("/openino/write/action")
	public String openinoboardWriteAction(Model model, OpeninoBoardRequestDto openinoboardRequestDto) throws Exception {
		try {

			Long result = boardService.save(openinoboardRequestDto);

			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/openino/list";

	}

	@PostMapping("/openino/edit/action")
	public String getopeinoBoardViewAction(Model model, @ModelAttribute OpeninoBoardRequestDto openinoboardRequestDto)
			throws Exception {

		try {
			int result = boardService.updateBoard(openinoboardRequestDto);

			if (result < 1) {
				throw new Exception("#Exception boardViewAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/openino/list";
	}

	@GetMapping("/openino/view/delete")
	public String openinoBoardViewDeleteAction(Model model, @RequestParam("id") Long id) throws Exception {

		try {
			System.out.println(id);
			boardService.openinoDeleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/boards/openino/list";
	}

	@GetMapping("/openino/delete")
	public String openinoBoardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {

		try {
			boardService.openinoDeleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/boards/openino/list";
	}

}
