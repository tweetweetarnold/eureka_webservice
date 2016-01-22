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
	private final String password = "{ryusoken}";
	private final String username = "kohbuslunchtime@gmail.com";

	private MimeMessage createEmailMessage(String subject, String messageBody, String[] toEmails)
			throws MessagingException {
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
				emailMessage.addRecipient(Message.RecipientType.TO,
						new InternetAddress(toEmails[i]));
			}

			emailMessage.setSubject(emailSubject);
			emailMessage.setContent(emailBody, "text/html; charset=utf-8");
			emailMessage.setFrom(new InternetAddress("no_reply@lunchtime.com",
					"Koh Bus LunchTime Ordering App"));
			emailMessage.setReplyTo(InternetAddress.parse("no_reply@lunchtime.com", false));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emailMessage;
	}

	private MimeMessage createEmailMessageWithCarbonCopy(String subject, String messageBody,
			String[] toEmails, String[] ccEmails) throws MessagingException {
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
				emailMessage.addRecipient(Message.RecipientType.TO,
						new InternetAddress(toEmails[i]));
			}

			if (ccEmails != null) {

				for (int i = 0; i < ccEmails.length; i++) {
					emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(
							ccEmails[i]));
				}
			}

			emailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(
					"chris.cheng.2013@sis.smu.edu.sg"));

			emailMessage.setSubject(emailSubject);
			emailMessage.setContent(emailBody, "text/html; charset=utf-8");
			emailMessage.setFrom(new InternetAddress("no_reply@lunchtime.com",
					"Koh Bus LunchTime Ordering App"));
			emailMessage.setReplyTo(InternetAddress.parse("no_reply@lunchtime.com", false));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emailMessage;
	}

	public void sendEmail(String subject, String messageBody, String[] toEmails)
			throws MessagingException {

		Message emailMessage = createEmailMessage(subject, messageBody, toEmails);
		Transport.send(emailMessage);
	}

	public void sendEmailWithCarbonCopy(String subject, String messageBody, String[] toEmails,
			String[] ccEmails) throws MessagingException {

		Message emailMessage = createEmailMessageWithCarbonCopy(subject, messageBody, toEmails,
				ccEmails);
		Transport.send(emailMessage);
	}

	public void setMailServerProperties() {
		emailProperties = new Properties();
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.host", "smtp.gmail.com");
		emailProperties.put("mail.smtp.port", "587");
	}
}
