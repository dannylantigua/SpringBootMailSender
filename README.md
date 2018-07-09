## Mail Sender Demo with Spring Boot

When using the standard Spring framework in order to do Dependency Injection, we need to configure the application-context.xml file. Spring Boot is annotation driven configuration, which means we don't have to touch the xml file/s. It is as easy as creating a new project with the Spring Initializr.

How do I inject a class/object?

You can do that with Property Injection, adding *@Autowired* when declaring the object:

```
@Autowired
private MailSender mailSender;
```

Also Constructor Injection can be used:

```
// Not necessary to use @Autowired annotation
private MailSender mailSender;

// Constructor Injection
public MailController(MailSender mailSender){
    this.mailSender = mailSender;
}
```

How do I tell Spring what class do I want to Inject?

You only have to add the *@Component* annotation on top of the Java class and it will understand that THIS is the class it has to inject on the constructor:

```
@Component
public class MockMailSender implements MailSender {
    //content
}
```

By Danny Lantigua
lantiguagrullon@gmail.com
