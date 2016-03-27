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

	public SendEmail() {
		emailProperties = new Properties();
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.host", "smtp.gmail.com");
		emailProperties.put("mail.smtp.port", "587");
	}

	private MimeMessage createEmailMessageWithCarbonCopy(String subject, String messageBody,
			String[] recipients, String[] ccEmails, String[] bccEmails) throws MessagingException {

		mailSession = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		MimeMessage emailMessage = null;

		try {
			emailMessage = new MimeMessage(mailSession);

			// set recipients
			if (recipients != null) {
				for (int i = 0; i < recipients.length; i++) {
					emailMessage.addRecipient(Message.RecipientType.TO,
							new InternetAddress(recipients[i]));
				}
			}

			// set cc recipients
			if (ccEmails != null) {
				for (int i = 0; i < ccEmails.length; i++) {
					emailMessage.addRecipient(Message.RecipientType.CC,
							new InternetAddress(ccEmails[i]));
				}
			}

			// set bcc recipients
			if (bccEmails != null) {
				for (int i = 0; i < bccEmails.length; i++) {
					emailMessage.addRecipient(Message.RecipientType.BCC,
							new InternetAddress(bccEmails[i]));
				}
			}

			emailMessage.setSubject(subject);
			emailMessage.setContent(messageBody, "text/html; charset=utf-8");
			emailMessage.setFrom(new InternetAddress("no_reply@lunchtime.com",
					"Koh Bus LunchTime Ordering App"));
			emailMessage.setReplyTo(InternetAddress.parse("no_reply@lunchtime.com", false));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emailMessage;
	}

	public void sendEmail(String subject, String messageBody, String[] recipients,
			String[] ccEmails, String[] bccEmails) throws MessagingException {

		Message emailMessage = createEmailMessageWithCarbonCopy(subject, messageBody, recipients,
				ccEmails, bccEmails);
		Transport.send(emailMessage);
	}

}
