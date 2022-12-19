package com.sglink.common.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "/error/404error";
			} else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
				return "redirect:/members/login";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return "/error/403error";
			} else {
				return "/error";
			}
		}

		return "redirect:/members/login/suc";
	}

}
