package services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	private Properties emailProperties;
	private Session mailSession;
	private final String username = "dabaomealorderapp@gmail.com";
	private final String password = "{gxuvimr}";
	
	public void setMailServerProperties() {
		
		emailProperties = new Properties();
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.host", "smtp.gmail.com");
		emailProperties.put("mail.smtp.port", "587");
		
	}
	
	public void sendEmail(String subject, String messageBody,String[] toEmails) throws MessagingException {
	
		Message emailMessage = createEmailMessage(subject, messageBody, toEmails);
		Transport.send(emailMessage);
	}
	
	private MimeMessage createEmailMessage(String subject, String messageBody, String[] toEmails) throws MessagingException {
		String emailSubject = subject;
		String emailBody = messageBody;
		
		mailSession = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		MimeMessage emailMessage = null;
		
		try {
			emailMessage = new MimeMessage(mailSession);
			
			for (int i = 0; i < toEmails.length; i++) {
				emailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(
						toEmails[i]));
			}
			
			emailMessage.setSubject(emailSubject);
			emailMessage.setContent(emailBody, "text/html; charset=utf-8");
			emailMessage.setFrom(new InternetAddress("dabaomealorderapp@gmail.com"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emailMessage;
	}
}