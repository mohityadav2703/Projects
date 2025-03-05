package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.CounsellorDTO;
import com.app.dto.DashboardResponseDTO;
import com.app.entity.CounsellorEntity;
import com.app.service.CounsellorService;
import com.app.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorService;

	@Autowired
	private EnquiryService enquiryService;

	@GetMapping("/")
	public String loadLogin(Model model) {
		CounsellorDTO dto = new CounsellorDTO();
		model.addAttribute("counsellor", dto);

		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		session.invalidate();

		CounsellorDTO dto = new CounsellorDTO();
		model.addAttribute("counsellor", dto);

		return "index";
	}

	@PostMapping("/login")
	public String handleLogin(HttpServletRequest req, @ModelAttribute("counsellor") CounsellorDTO counsellor,
			Model model) {
		CounsellorDTO counsellorDTO = counsellorService.login(counsellor);

		if (counsellorDTO == null) {
			model.addAttribute("errMsg", "Invalid Credentials...!!");

			return "index";
		} else {
			Integer counsellorId = counsellorDTO.getCounsellorId();

			// store counsellorId in session object
			HttpSession session = req.getSession(true);
			session.setAttribute("counsellorId", counsellorId);

			DashboardResponseDTO dashboardDto = enquiryService.getDashboardInfo(counsellorId);
			model.addAttribute("dashboardDto", dashboardDto);
			return "dashboard";
		}

	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		CounsellorDTO dto = new CounsellorDTO();
		model.addAttribute("counsellor", dto);

		return "register";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute("counsellor") CounsellorDTO counsellor, Model model) {
		boolean unique = counsellorService.uniqueEmailCheck(counsellor.getEmail());

		if (unique) {
			boolean register = counsellorService.register(counsellor);
			if (register) {
				model.addAttribute("succMsg", "Registration Success ");
			} else {
				model.addAttribute("errMsg", "Registration Failed");
			}
		} else {
			model.addAttribute("errMsg", "Email Already Exists, Use Unique Email..");
		}
		return "register";
	}

	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		CounsellorEntity counsellor = counsellorService.findById(counsellorId);
		model.addAttribute("counsellorName", counsellor.getName());

		DashboardResponseDTO dashboardDto = enquiryService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardDto", dashboardDto);
		return "dashboard";
	}
}
