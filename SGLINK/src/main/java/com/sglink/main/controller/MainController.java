package com.sglink.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sglink.board.service.BoardService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class MainController {
	private final BoardService boardService;
	
//	@RequestMapping(value="/", method = RequestMethod.GET)
//	public String main() {
//		return "/main/main";
//	}
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String main(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "7") Integer size) throws Exception {
		try {
			model.addAttribute("resultMap", boardService.findAllBoard(page, size));
	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
		return "/main/main";
	}


	

}