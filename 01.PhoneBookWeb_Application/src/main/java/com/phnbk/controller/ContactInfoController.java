package com.phnbk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.phnbk.dto.Contact;
import com.phnbk.service.ContactService;

@Controller
public class ContactInfoController {
	
	private final ContactService contactService;

	public ContactInfoController(ContactService contactService) {
		this.contactService=contactService;
	}
	
	@GetMapping(value= {"/", "/addContact"})
	public String loadForm(@ModelAttribute Contact contact, Model model) {
		Contact c = new Contact();
		model.addAttribute("contact",c);
		return "contactInfo";
	}
	
	@PostMapping("/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact") Contact c, Model model) {
		boolean isSaved=contactService.saveContact(c);
		if(isSaved) {
			model.addAttribute("succMsg", "Contact Saved");
		}
		else {
			model.addAttribute("errMsg", "Failed to Save Contact");
		}	
		return "contactInfo";
	}
	
	@GetMapping("/viewContacts")
	public String handleViewContactsLink(Model model) {
		List<Contact> contactsList=contactService.getAllContacts();
		model.addAttribute("contacts", contactsList);
		return "viewContacts";
	}
}
