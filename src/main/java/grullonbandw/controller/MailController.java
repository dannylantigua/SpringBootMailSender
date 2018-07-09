package grullonbandw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grullonbandw.mail.MailSender;

@RestController
public class MailController {
	
	// Not necessary to use @Autowired annotation
	private MailSender mailSender;
	
	//Constructor Injection
	public MailController(MailSender mailSender){
		this.mailSender = mailSender;
	}
		
	@RequestMapping("/mail")
	public String mail() {
		
		mailSender.send("mail@example.com", "Test Mail", "This is a body example for Test Mail");
		return "Mail Sent!";
	}
}
