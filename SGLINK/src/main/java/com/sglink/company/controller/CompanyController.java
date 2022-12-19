package com.sglink.company.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sglink.common.constant.CompanySectors;
import com.sglink.company.dto.CompanyRequestDto;
import com.sglink.company.service.CompanyService;
import com.sglink.entity.Company;

import lombok.RequiredArgsConstructor;

@RequestMapping(value="/company")
@Controller
@RequiredArgsConstructor
public class CompanyController {
	
	private final CompanyService companyService;
	
	@GetMapping("/family/new")
	public String companyCreateForm(Model model,CompanySectors companySectors) {
		 model.addAttribute("companyRequestDto", new CompanyRequestDto()); 
		 model.addAttribute("companySectors",companySectors.deliveryCodes());
		return "/company/family/companyForm";
	}
	
	@PostMapping("/family/new")
	public String companyCreate(@Valid @ModelAttribute("companyRequestDto")CompanyRequestDto companyRequestDto,
			BindingResult bindingResult,Model model,CompanySectors companySectors) {
		 model.addAttribute("companySectors",companySectors.deliveryCodes());
		if (bindingResult.hasErrors()) {
			return "/company/family/companyForm";
		}
		try {
			Company company = Company.createCompay(companyRequestDto);
			companyService.saveCompany(company);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/company/family/companyForm";
		}
		return "redirect:/";
		
		
	}
	
	@GetMapping("/family/introduce")
	public String introduce() {
		return "/company/family/introduce";
	}
	
	@GetMapping("/family/status")
	public String status() {
		return "/company/family/status";
	}
	
	@GetMapping("/family/forum")
	public String forum() {
		return "/company/family/forum";
	}
	
}
