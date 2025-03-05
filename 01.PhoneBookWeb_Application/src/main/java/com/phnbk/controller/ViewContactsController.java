package com.phnbk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phnbk.dto.Contact;
import com.phnbk.service.ContactService;

@Controller
public class ViewContactsController {
	
	private final ContactService contactService;
	
	public ViewContactsController(ContactService contactService) {
		this.contactService=contactService;
	}
	
	  @RequestMapping(path="/editContact")
	  public String editContact(@RequestParam("cid") Integer contactId, Model model) {
		 Contact c = contactService.getContactById(contactId);
		 model.addAttribute("contact", c);
		 return "contactInfo";  
	  }

	  @RequestMapping(path="/deleteContact")
	  public String deleteContact(@RequestParam("cid") Integer id) {
		  boolean isDeleted = contactService.deleteContact(id);
		  if(isDeleted) {
			  return "redirect:/viewContacts";
		  }
		  return null;
	  }
}
