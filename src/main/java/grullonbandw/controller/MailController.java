package grullonbandw.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grullonbandw.mail.MailSender;

@RestController
public class MailController {
	
	// Not necessary to use @Autowired annotation
	private MailSender mailSender;
	
	//Constructor Injection
	public MailController(@Qualifier("smtpMailSender") MailSender mail){
		this.mailSender = mail;
	}
		
	@RequestMapping("/mail")
	public String mail() throws MessagingException {
		
		mailSender.send("test_email@gmail.com", "Test Mail", "This is a body example for Test Mail");
		return "Mail Sent!";
	}
}
