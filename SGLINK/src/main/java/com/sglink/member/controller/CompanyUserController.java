package com.sglink.member.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sglink.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/comuser")
@Controller
@RequiredArgsConstructor
public class CompanyUserController {
	private final MemberService memberService;
	
	
	@GetMapping("/business/list")
	public String businessListPage(Model model, @RequestParam(required= false, defaultValue= "0") Integer page,
			@RequestParam(required = false, defaultValue= "10") Integer size, Principal principal)throws Exception{
		try {
			String userId = principal.getName();
			model.addAttribute("resultMap", memberService.selectBusinessReservation(userId,page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/member/comuser/comuserBusiList";
	}
	
	@GetMapping("/business/list/approve")
	public String approveBusinessReservation(@RequestParam("id")Long id,@RequestParam("busiProcess")String busiProcess) {
		memberService.approveBusinessReservation(id,busiProcess);
		return "redirect:/comuser/business/list";
	}
	
	@GetMapping("/business/management")
	public String businessManagement(Model model, @RequestParam(required= false, defaultValue= "0") Integer page,
			@RequestParam(required = false, defaultValue= "10") Integer size, Principal principal)throws Exception{
		try {
			String userId = principal.getName();
			model.addAttribute("resultMap", memberService.selectBusiness(userId,page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/member/comuser/comuserBusiManagement";
	}
	
	@GetMapping("/business/management/possible")
	public String possibleBusiness(@RequestParam("busiId")String busiId,@RequestParam("reservation")String reservation) {
		memberService.possibleBusiness(busiId,reservation);
		return "redirect:/comuser/business/management";
	}



	@GetMapping("/equipment/list")
	public String equipmentListPage(Model model, @RequestParam(required= false, defaultValue= "0") Integer page,
			@RequestParam(required = false, defaultValue= "10") Integer size, Principal principal)throws Exception{
		try {
			String userId = principal.getName();
			model.addAttribute("resultMap", memberService.selectEquipmentReservation(userId,page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/member/comuser/comuserEquiList";
	}
	
	@GetMapping("/equipment/list/approve")
	public String approveEquipmentReservation(@RequestParam("id")Long id,@RequestParam("equiProcess")String equiProcess) {
		memberService.approveEquipmentReservation(id,equiProcess);
		return "redirect:/comuser/equipment/list";
	}
	
	
	@GetMapping("/equipment/management")
	public String equipmentManagement(Model model, @RequestParam(required= false, defaultValue= "0") Integer page,
			@RequestParam(required = false, defaultValue= "10") Integer size, Principal principal)throws Exception{
		try {
			String userId = principal.getName();
			model.addAttribute("resultMap", memberService.selectEquipment(userId,page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/member/comuser/comuserEquiManagement";
	}
	
	@GetMapping("/equipment/management/possible")
	public String possibleEquipment(@RequestParam("equiId")String equiId,@RequestParam("reservation")String reservation) {
		memberService.possibleEquipment(equiId,reservation);
		return "redirect:/comuser/equipment/management";
	}

	


}