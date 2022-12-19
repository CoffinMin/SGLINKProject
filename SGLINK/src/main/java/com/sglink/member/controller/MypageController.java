package com.sglink.member.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sglink.entity.Member;
import com.sglink.member.dto.MemberUpdateDto;
import com.sglink.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/mypage")
@Controller
@RequiredArgsConstructor
public class MypageController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	
	@GetMapping(value="/update")
	public String mypageForm(Model model, Principal principal) {
		model.addAttribute("memberUpdateDto", new MemberUpdateDto());
		String userId = principal.getName();
		Member member = memberService.findbyId(userId);
		model.addAttribute("member",member);
		model.addAttribute("userId",userId);
		return "/member/mypage/memberUpdateForm";
	}
	
	@PostMapping(value="/update")
	public String modifyMember(@Valid @ModelAttribute("memberUpdateDto")MemberUpdateDto memberFormDto
			, BindingResult bindingResult,Model model,Principal principal) {	
		if (bindingResult.hasErrors()) {
			String userId = principal.getName();
			Member member = memberService.findbyId(userId);
			model.addAttribute("member",member);
			return "/member/mypage/memberUpdateForm";			
		}
		try {
			Member member = Member.modifyMember(memberFormDto, passwordEncoder);
			String userId = memberService.getUserId(principal);
			memberService.updateMember(member,userId);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/mypage/memberUpdateForm";
		}
		return "redirect:/mypage/view";
	}
	
	
	@GetMapping(value="/view")
	public String viewMypage(Model model, Principal principal) {
		String userId = memberService.getUserId(principal);
		Member member = memberService.getMember(userId);
		System.out.println(member);
		model.addAttribute("info", member);
		return "/member/mypage/mypage";
	}
	
	@PostMapping(value="/delete")
	public String deleteMyMember(@RequestParam("userId")String userId) {
		memberService.deleteMember(userId);	
		return "redirect:/members/logout";
	}
	
	@GetMapping(value="/equipment/list")
	public String equipmentReservationListPage(Model model, @RequestParam(required= false, defaultValue= "0") Integer page,
			@RequestParam(required = false, defaultValue= "10") Integer size, Principal principal) throws Exception{
		try {
			String userId = principal.getName();
			Member member = memberService.findbyId(userId);
			model.addAttribute("resultMap", memberService.MyselectEquipmentReservation(member,page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/member/mypage/mypageEquiList";
	}
	
	@GetMapping(value="/business/list")
	public String businessReservationListPage(Model model, @RequestParam(required= false, defaultValue= "0") Integer page,
			@RequestParam(required = false, defaultValue= "10") Integer size, Principal principal) throws Exception{
		try {
			String userId = principal.getName();
			Member member = memberService.findbyId(userId) ;
			model.addAttribute("resultMap", memberService.MyselectBusinessReservation(member ,page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/member/mypage/mypageBusiList";
	}
	
}