package com.sglink.equipment.controller;



import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sglink.common.constant.Role;
import com.sglink.entity.Company;
import com.sglink.entity.Equipment;
import com.sglink.entity.Member;
import com.sglink.equipment.dto.EquipmentRequestDto;
import com.sglink.equipment.service.EquipmentService;
import com.sglink.file.service.FileUploadService;
import com.sglink.member.dto.EquipmentReservationRequestDto;
import com.sglink.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/equipment")
@RequiredArgsConstructor
@Controller
public class EquipmentController {

	private final MemberService memberService;
	private final EquipmentService equipmentService;
	private final FileUploadService fileUploadService;

	// 장비 관련 페이지
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String viewEquipment( Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "9") Integer size){
		model.addAttribute("resultMap", equipmentService.findAll(page, size));
		return "/equipment/equipment/equipmentList";
	}
	
	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public String viewEquipment2( Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "9") Integer size){
		model.addAttribute("resultMap", equipmentService.findAll(page, size));
		return "/equipment/equipment/equipmentList2";
	}

	@GetMapping(value = "/new")
	public String newEquipment(Model model, Principal principal) {
		String userId = principal.getName();
		Role role = memberService.findbyId(userId).getRole();
		if(!role.equals(Role.COM)) {
			model.addAttribute("msg", "기업로그인이 필요합니다.");
			
			return "/equipment/equipment/comAlert";
		}
		
		Member userInfo = memberService.findbyId(userId);
		Company company = memberService.findbyId(userId).getCompany();
		String comUniname = company.getComUniname();
		model.addAttribute("equipmentRequestDto", new EquipmentRequestDto());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("comUniname", comUniname);
		return "/equipment/equipment/equipmentRegist";
	}

	@PostMapping(value = "/new")
	public String createEquipment(@ModelAttribute("equipmentRequestDto") EquipmentRequestDto equipmentRequestDto,

			Model model, @RequestParam("files") List<MultipartFile> files) throws Exception {
			String equiId = equipmentService.save(equipmentRequestDto);
			Equipment equipment = equipmentService.findByEquiId(equiId);
			fileUploadService.addFile(files, equiId, equipment);
		return "redirect:/equipment/list";
	}
	
	
	@GetMapping("/view")
	public String viewEquipment(@RequestParam("equiId") String id, Model model,
			EquipmentRequestDto equipmentRequestDto, Principal principal) throws Exception {
		try {
			if (equipmentRequestDto.getEquiId() != null) {
				Equipment equipment = equipmentService.viewfindById(id).get();
				String registerId = equipment.getEquiRegisterId();
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", equipmentService.findById(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/equipment/equipment/equipmentView";

	}
	
	@GetMapping("/view2")
	public String viewEquipment2(@RequestParam("equiId") String id, Model model,
			EquipmentRequestDto equipmentRequestDto, Principal principal) throws Exception {
		try {
			if (equipmentRequestDto.getEquiId() != null) {
				Equipment equipment = equipmentService.viewfindById(id).get();
				String registerId = equipment.getEquiRegisterId();
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", equipmentService.findById(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/equipment/equipment/equipmentView2";

	}
	
	@GetMapping(value="/popup")
	public String equipmentPopup(@RequestParam("equiId")String equiId,Model model, Principal principal) {
		Equipment  equipment =equipmentService.findByEquiId(equiId);
		String userId = principal.getName();
		Member member = memberService.findbyId(userId);
		model.addAttribute("info", equipment);
		model.addAttribute("member",member);
		return "/equipment/equipment/equipmentPopup";
	}
	
	@ResponseBody
	@PostMapping(value="/popup")
	public String equipmentPopupSubmit(@ModelAttribute("errDto") EquipmentReservationRequestDto errDto) {
		System.out.println(errDto);
		equipmentService.save(errDto);
		return "성공";
	}


}
