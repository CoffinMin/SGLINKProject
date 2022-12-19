package com.sglink.member.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sglink.board.service.BoardService;
import com.sglink.company.service.CompanyService;
import com.sglink.entity.Company;
import com.sglink.entity.Member;
import com.sglink.member.dto.COM_MemberFormDto;
import com.sglink.member.dto.STU_MemberFormDto;
import com.sglink.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final CompanyService companyService;
	private final PasswordEncoder passwordEncoder;
	private final BoardService boardService;

	@GetMapping(value = "/com/new")
	public String memberComForm(Model model) {
		model.addAttribute("memberFormDto", new COM_MemberFormDto());
		return "member/members/comMemberForm";
	}

	@PostMapping(value = "/com/new")
	public String newComMember(@Valid @ModelAttribute("memberFormDto") COM_MemberFormDto memberFormDto,
			BindingResult bindingResult, Model model, @RequestParam("comId") String comId) {
		if (bindingResult.hasErrors()) {
			return "member/members/comMemberForm";
		}
		try {
			Company company = companyService.findByComId(comId);
			Member member = Member.createComMember(company, memberFormDto, passwordEncoder);
			memberService.saveMember(member);
			model.addAttribute("msg", "정상적으로 회원가입되었습니다.");
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/members/comMemberForm";
		}
		return "member/members/signupsuc";
	}

	@GetMapping(value = "/stu/new")
	public String memberStuForm(Model model) {
		model.addAttribute("memberFormDto", new STU_MemberFormDto());
		return "member/members/stuMemberForm";
	}

	@PostMapping(value = "/stu/new")
	public String newStuMember(@Valid @ModelAttribute("memberFormDto") STU_MemberFormDto memberFormDto,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "member/members/stuMemberForm";
		}
		try {
			Member member = Member.createStuMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
			model.addAttribute("msg", "정상적으로 회원가입되었습니다.");
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/members/stuMemberForm";
		}
		return "member/members/signupsuc";
	}

	@GetMapping(value = "/login")
	public String loginMember() {
		return "member/members/memberLoginForm";
	}

	@GetMapping(value = "/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		return "member/members/memberLoginForm";
	}
	
	@RequestMapping(value="/login/suc", method = RequestMethod.GET)
	public String main(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {
		try {
			model.addAttribute("resultMap", boardService.findAllBoard(page, size));
			model.addAttribute("msg", "로그인되었습니다");
	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "member/members/loginSuccess";
	}

	@GetMapping(value = "/memberRole")
	public String memberRole(Model model) {
		return "member/members/memberRole";
	}

	@ResponseBody
	@GetMapping(value = "/com/checkId")
	public String checkComId(@RequestParam("comId") String comId) {
		try {
			String comUniname = companyService.findByComId(comId).getComUniname();
			return comUniname;

		} catch (IllegalStateException e) {
			return e.getMessage();

		}
	}

	@ResponseBody
	@GetMapping("/findMember")
	public Member findMember(@RequestParam("id") String id) {
		return memberService.findbyId(id);
	}

}