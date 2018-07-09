## Mail Sender Demo with Spring Boot

### Use of Java Class Configuration File

Using a Java Class configuration file, we are able to declare the beans manually. What we have to do is to add the *@Configuration* annotation above the Class and the *@Bean* annotation above the methods that will return the correct class implementation.

```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

	@Bean
	public MailSender mockMailSender() {
		return new MockMailSender();
	}

	@Bean
	public MailSender smtpMailSender() {
		return new SmtpMailSender();
	}
}
```

How do I tell Spring what class do I want to Inject?

You only have to add the *@Qualifier ("methodName")* annotation passed as a parameter on the constructor. Spring will know we want to inject 'smtpMailSender':

```
//Constructor Injection
public MailController(@Qualifier("smtpMailSender") MailSender mail){
	this.mailSender = mail;
}
```

By Danny Lantigua
lantiguagrullon@gmail.com
