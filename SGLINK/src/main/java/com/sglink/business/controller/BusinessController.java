package com.sglink.business.controller;

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

import com.sglink.business.dto.BusinessRequestDto;
import com.sglink.business.service.BusinessService;
import com.sglink.common.constant.Role;
import com.sglink.entity.Business;
import com.sglink.entity.Company;
import com.sglink.entity.Member;
import com.sglink.file.service.FileUploadService;
import com.sglink.member.dto.BusinessReservationRequestDto;
import com.sglink.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/business")
@RequiredArgsConstructor
@Controller
public class BusinessController {
	
	private final MemberService memberService;
	private final BusinessService businessService;
	private final FileUploadService fileUploadService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String viewBusiness( Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "9") Integer size){
		model.addAttribute("resultMap", businessService.findAll(page, size));
		return "/business/business/businessList";
	}
	
	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public String viewBusiness2( Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "9") Integer size){
		model.addAttribute("resultMap", businessService.findAll(page, size));
		return "/business/business/businessList2";
	}

	@GetMapping(value = "/new")
	public String newBusiness(Model model, Principal principal) {
		String userId = principal.getName();
		Role role = memberService.findbyId(userId).getRole();
		if(!role.equals(Role.COM)) {
			model.addAttribute("msg", "기업로그인이 필요합니다.");
			
			return "/business/business/comAlert";
		}
		
		Member userInfo = memberService.findbyId(userId);
		Company company = memberService.findbyId(userId).getCompany();
		String comUniname = company.getComUniname();
		model.addAttribute("businessRequestDto", new BusinessRequestDto());
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("comUniname", comUniname);
		return "/business/business/businessForm";
	}

	@PostMapping(value = "/new")
	public String createBusiness(@ModelAttribute("businessRequestDto") BusinessRequestDto businessRequestDto,
			Model model, @RequestParam("files") List<MultipartFile> files) throws Exception {
		String busiId = businessService.save(businessRequestDto);
		Business business = businessService.findByBusiId(busiId);
		fileUploadService.addFile(files, busiId, business);
		return "redirect:/business/list";
	}
	
	@GetMapping("/view")
	public String viewBusiness(@RequestParam("busiId") String id, Model model,
			BusinessRequestDto businessRequestDto, Principal principal) throws Exception {
		try {
			if (businessRequestDto.getBusiId() != null) {
				Business business = businessService.viewfindById(id).get();
				String registerId = business.getBusiRegisterId();
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", businessService.findById(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/business/business/businessView";

	}
	
	@GetMapping("/view2")
	public String viewBusiness2(@RequestParam("busiId") String id, Model model,
			BusinessRequestDto businessRequestDto, Principal principal) throws Exception {
		try {
			if (businessRequestDto.getBusiId() != null) {
				Business business = businessService.viewfindById(id).get();
				String registerId = business.getBusiRegisterId();
				String loginUserId = principal.getName();
				model.addAttribute("loginUserId", loginUserId);
				model.addAttribute("registerId", registerId);
				model.addAttribute("info", businessService.findById(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/business/business/businessView2";

	}
	
	@GetMapping(value="/popup")
	public String businessPopup(@RequestParam("busiId")String busiId,Model model, Principal principal) {
		Business  business =businessService.findByBusiId(busiId);
		String userId = principal.getName();
		Member member = memberService.findbyId(userId);
		model.addAttribute("info", business);
		model.addAttribute("member",member);
		return "/business/business/businessPopup";
	}
	
	@ResponseBody
	@PostMapping(value="/popup")
	public String businessPopupSubmit(@ModelAttribute("errDto") BusinessReservationRequestDto errDto) {
		System.out.println(errDto);
		businessService.save(errDto);
		return "성공";
	}
}