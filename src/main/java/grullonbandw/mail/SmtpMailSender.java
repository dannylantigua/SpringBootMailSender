package grullonbandw.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SmtpMailSender implements MailSender {
	
	// to write on the console
	private static Log Log = LogFactory.getLog(SmtpMailSender.class);

	@Override
	public void send(String to, String subject, String body) {
		Log.info("Sending SMTP mail to " + to);
		Log.info("with subject " + subject);
		Log.info("and body " + body);
	}
}
