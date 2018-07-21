## Mail Sender Demo with Spring Boot

### Configuring SMTP to send email with Gmail

To test this feature, a few Java files must be configure, but most important, you need to configure the gmail account you will use to send the test mail. The 2-steps-authentication must be enable. Once this part is enabled in your Google account, there will be an option below called "App Passwords" in which you will generate a new password and that's the one we must put in the *@application.properties* file:

```
spring.mail.host = smtp.gmail.com
spring.mail.username = your_email@gmail.com
spring.mail.password = app_password

spring.mail.properties.mail.smtp.auth = true
spring.mail.properties.mail.smtp.socketFactory.port = 465
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback = false
spring.mail.properties.mail.smtp.ssl.enable = true
```

Make sure the *pom* XML file has the needed dependencies:

```
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>

	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-devtools</artifactId>
	        <optional>true</optional>
	    </dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

After that, we create a new instance of JavaMailSender and do Constructor Injection in the *SmtpMailSender* java class. :

```
private JavaMailSender javaMailSender;

	public SmtpMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
```

The send method in the *SmtpMailSender* java class must look like the code below and make sure to include the MessagingException in the interface and mail() method on the Controller:

```
@Override
	public void send(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true); // true indicates
 					  				                   // multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); // true indicates html

		// continue using helper for more functionalities
        // like adding attachments, etc.  

		javaMailSender.send(message);
	}
```

Finally, the *MailConfig* java configuration file must look like the code below, where we pass the JavaMailSender as a parameter:

```
@Configuration
public class MailConfig {

	@Bean
	@ConditionalOnProperty(name="spring.mail.host",
			havingValue="foo", matchIfMissing=true)
	public MailSender mockMailSender() {
		return new MockMailSender();
	}

	@Bean
	@ConditionalOnProperty("spring.mail.host")
	public MailSender smtpMailSender(JavaMailSender javaMailSender) {
		return new SmtpMailSender(javaMailSender);
	}
}
```

By Danny Lantigua
lantiguagrullon@gmail.com
