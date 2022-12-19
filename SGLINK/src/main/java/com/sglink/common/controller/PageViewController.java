package com.sglink.common.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sglink.common.dto.PageViewRequestDto;
import com.sglink.common.service.PageViewService;
import com.sglink.entity.Introduce;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class PageViewController {
	private final PageViewService pageViewService;
	
	
	
	//가족기업 관련 페이지 ------------------------------------------------------------------------
	@RequestMapping(value = "/comsup/family/introduce", method = RequestMethod.GET)
	public String familyIntroduce() {
		return "/pageView/comsup/family/introduce";
	}
	@RequestMapping(value = "/comsup/family/application", method = RequestMethod.GET)
	public String familyApplication() {
		return "/pageView/comsup/family/application";
	}
	@RequestMapping(value = "/comsup/family/status", method = RequestMethod.GET)
	public String familyStatus(Model model, @RequestParam(required= false, defaultValue= "0") Integer page, @RequestParam(required = false, defaultValue= "10") Integer size)throws Exception{
		
		try {
			model.addAttribute("resultMap", pageViewService.selectCompanyByProcess(page, size));
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "/pageView/comsup/family/status";
	}
	@RequestMapping(value = "/comsup/family/forum", method = RequestMethod.GET)
	public String familyforum() {
		return "/pageView/comsup/family/forum";
	}
	
	
	
	//기업지원인프라--------------------------------------------------------------------------------
	@RequestMapping(value = "/comsup/infra/introduce", method = RequestMethod.GET)
	public String InfraIntroduce() {
		return "/pageView/comsup/infra/introduce";
	}
	@RequestMapping(value = "/comsup/infra/application", method = RequestMethod.GET)
	public String InfraApplication() {
		return "/pageView/comsup/infra/application";
	}
	@RequestMapping(value = "/comsup/infra/howto", method = RequestMethod.GET)
	public String Infrahowto() {
		return "/pageView/comsup/infra/howto";
	}
	@RequestMapping(value = "/comsup/infra/onlineapplication", method = RequestMethod.GET)
	public String Infraonlineapplication() {
		return "/pageView/comsup/infra/onlineapplication";
	}
	@RequestMapping(value = "/comsup/infra/precaution", method = RequestMethod.GET)
	public String Infraprecaution() {
		return "/pageView/comsup/infra/precaution";
	}
	@RequestMapping(value = "/comsup/infra/equipmentregist", method = RequestMethod.GET)
	public String Infraequipmentregist() {
		return "/pageView/equipment/equipment/equipmentRegist";
	}
	
	
	
	//사업단소개--------------------------------------------------------------------------
//	@RequestMapping(value = "/introduce/greeting", method = RequestMethod.GET)
//	public String greeting() {
//		return "/pageView/introduce/greeting";
//	}
	
	@GetMapping("/introduce/greeting")
	public String greetingView(@RequestParam("id") Long id, Model model, PageViewRequestDto pageRequestDto)  throws Exception{
		
		try {
			if (pageRequestDto.getId() != null) {
				Introduce introduce= pageViewService.viewfindById(id).get();
				model.addAttribute("info", pageViewService.findById(pageRequestDto.getId()));
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		model.addAttribute(pageRequestDto);
	
		return "/pageView/introduce/greeting";
	}
	
	
	@GetMapping("/introduce/greeting/edit")
	public String greetingEdit(@RequestParam("id") Long id, Model model, PageViewRequestDto pageRequestDto)  throws Exception{
		
		try {
			if (pageRequestDto.getId() != null) {
				Introduce introduce= pageViewService.viewfindById(id).get();
				model.addAttribute("info", pageViewService.findById(pageRequestDto.getId()));
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		model.addAttribute(pageRequestDto);
	
		return "/pageView/introduce/greetingEdit";
	}
	@PostMapping("/introduce/greeting/edit/action")
	public String greetingEditActionPage(Model model, PageViewRequestDto pageRequestDto) throws Exception{
		try {
			Long result = pageViewService.save(pageRequestDto);
			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/introduce/greeting?id=1";
	}
	
	
	@GetMapping("/introduce/greeting/write")
	public String greeting(Model model, PageViewRequestDto pageRequestDto) {
		
		return "/pageView/introduce/greetingWrite";
	}
	
	@PostMapping("/introduce/greeting/write/action")
	public String greetingWriteActionPage(Model model, PageViewRequestDto pageRequestDto) throws Exception{
		try {
			Long result = pageViewService.save(pageRequestDto);
			if (result < 0) {
				throw new Exception("#Exception boardWriteAction!");
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "redirect:/introduce/greeting?id=1";
	}
	
	
	@RequestMapping(value = "/introduce/vision", method = RequestMethod.GET)
	public String vision() {
		return "/pageView/introduce/vision";
	}
	
	@RequestMapping(value = "/introduce/organization", method = RequestMethod.GET)
	public String organization() {
		return "/pageView/introduce/organization";
	}
	
	@RequestMapping(value = "/introduce/direction", method = RequestMethod.GET)
	public String direction() {
		return "/pageView/introduce/direction";
	}
	
	//오픈 이노베이션-------------------------------------------------------------------------
	@RequestMapping(value = "/comsup/openino/introduce", method = RequestMethod.GET)
	public String OpenIntroduce() {
		return "/pageView/comsup/openino/introduce";
	}
	@RequestMapping(value = "/comsup/openino/model", method = RequestMethod.GET)
	public String OpenModel() {
		return "/pageView/comsup/openino/model";
	}
	@RequestMapping(value = "/comsup/openino/board", method = RequestMethod.GET)
	public String OpenBoard() {
		return "/pageView/comsup/openino/board";
	}
	
	//학생지원 관련 페이지-----------------------------------------------------------------
	@RequestMapping(value = "/stusup/cooperation/introduce", method = RequestMethod.GET)
	public String StuIntroduce() {
		return "/pageView/stusup/cooperation/introduce";
	}
	@RequestMapping(value = "/stusup/cooperation/open", method = RequestMethod.GET)
	public String StuOpen() {
		return "/pageView/stusup/cooperation/open";
	}
	@RequestMapping(value = "/stusup/cooperation/independent", method = RequestMethod.GET)
	public String StuIndependent() {
		return "/pageView/stusup/cooperation/independent";
	}
	
	//창업지원 관련 페이지-----------------------------------------------------------------
	@RequestMapping(value = "/stusup/founded/introduce", method = RequestMethod.GET)
	public String FoundedIntroduce() {
		return "/pageView/stusup/founded/introduce";
	}
	@RequestMapping(value = "/stusup/founded/camp", method = RequestMethod.GET)
	public String FoundedCamp() {
		return "/pageView/stusup/founded/camp";
	}
	@RequestMapping(value = "/stusup/founded/contest", method = RequestMethod.GET)
	public String FoundedContest() {
		return "/pageView/stusup/founded/contest";
	}
	@RequestMapping(value = "/stusup/founded/conteststatus", method = RequestMethod.GET)
	public String FoundedContestStatus() {
		return "/pageView/stusup/founded/conteststatus";
	}
	@RequestMapping(value = "/stusup/founded/mentoring", method = RequestMethod.GET)
	public String FoundedMentoring() {
		return "/pageView/stusup/founded/mentoring";
	}
	
	//현장실습 관련 페이지------------------------------------------------------------------
	@RequestMapping(value = "/stusup/training/introduce", method = RequestMethod.GET)
	public String TrainingIntroduce() {
		return "/pageView/stusup/training/introduce";
	}
	@RequestMapping(value = "/stusup/training/guide", method = RequestMethod.GET)
	public String TrainingGuide() {
		return "/pageView/stusup/training/guide";
	}
	@RequestMapping(value = "/stusup/training/program", method = RequestMethod.GET)
	public String TrainingProgram() {
		return "/pageView/stusup/training/program";
	}
	//학생지원인프라--------------------------------------------------------------------------------
		@RequestMapping(value = "/stusup/infra/introduce", method = RequestMethod.GET)
		public String stuInfraIntroduce() {
			return "/pageView/stusup/infra/introduce";
		}
		@RequestMapping(value = "/stusup/infra/application", method = RequestMethod.GET)
		public String stuInfraApplication() {
			return "/pageView/stusup/infra/application";
		}
		@RequestMapping(value = "/stusup/infra/howto", method = RequestMethod.GET)
		public String stuInfrahowto() {
			return "/pageView/stusup/infra/howto";
		}
		@RequestMapping(value = "/stusup/infra/onlineapplication", method = RequestMethod.GET)
		public String stuInfraonlineapplication() {
			return "/pageView/stusup/infra/onlineapplication";
		}
		@RequestMapping(value = "/stusup/infra/precaution", method = RequestMethod.GET)
		public String stuInfraprecaution() {
			return "/pageView/stusup/infra/precaution";
		}
	
	//커뮤니티------------------------------------------------------------------------------
	
	@GetMapping(value="/community/cmntEvent")
	public String cmntEvent() {
		return "/pageView/cmnt/cmntEvent";
	}
	
	@GetMapping(value="/community/eventSchedule")
	public String eventSchedule() {
		return "/pageView/cmnt/eventSchedule";
	}
	

	@GetMapping(value="/community/news")
	public String news() {
		return "/pageView/cmnt/news";
	}
	
	//빅데이터------------------------------------------------------------------------------
	
		@GetMapping(value="/bigdata/bigdataList")
		public String bigdataEvent() {
			return "/pageView/bigdata/bigdataList";
		}
		
		@GetMapping(value="/bigdata/bigdataIssue")
		public String bigdataIssueEvent() {
			return "/pageView/bigdata/bigdataIssue";
		}
		
	//헤더--------------------------------------------------------------------------------------
		@GetMapping(value="/header/userIdView")
		@ResponseBody
		public String userIdView(Principal principal,Model model) {
			if(principal.getName().equals(null)) {
				return "";
			}
			String userId = principal.getName();
			System.out.println(userId);
			return userId;
		}
		
		@GetMapping("/ex")
		public String ex(){
			return "/business/business/ex";
		}
}
