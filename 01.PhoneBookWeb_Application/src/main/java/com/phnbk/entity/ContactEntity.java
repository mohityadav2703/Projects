package com.phnbk.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="CONTACT_DTLS")
public class ContactEntity{
	
	@Id
	@Column(name="CONTACT_ID")
	@SequenceGenerator(name="cid_seq_gen",
				sequenceName = "CONTACT_ID_SEQ",allocationSize = 1)
	@GeneratedValue(generator = "cid_seq_gen",strategy = GenerationType.SEQUENCE)
	private Integer contactId;
	
	@Column(name="CONTACT_NAME")
	private String contactName;
	
	@Column(name="CONTACT_EMAIL")
	private String contactEmail;
	
	@Column(name="CONTACT_NUMBER")
	private Long contactNumber;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE", updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE) //it use to specify in which formate you want to insert date
	@Column(name="UPDATED_DATE", insertable = false)
	private Date updatedDate;	

}
