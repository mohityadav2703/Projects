package com.phnbk.service;

import java.util.List;

import com.phnbk.dto.Contact;

public interface ContactService {
	
	boolean saveContact(Contact c);
	List<Contact> getAllContacts();
	Contact getContactById(Integer id);
	boolean updateContact(Contact c);
	boolean deleteContact(Integer id);
}
