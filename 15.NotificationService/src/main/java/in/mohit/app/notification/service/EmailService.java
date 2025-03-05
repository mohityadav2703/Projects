package in.mohit.app.notification.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		this.mailSender=mailSender;
	}
	
	@SneakyThrows
	public boolean sendEmail(String to, String subject, String body) {
		MimeMessage mimeMsg= mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
		
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		mailSender.send(mimeMsg);
		
		return true;
	}

}
