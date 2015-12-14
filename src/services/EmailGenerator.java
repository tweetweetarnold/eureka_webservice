package services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGenerator {

	private Properties emailProperties;
	private Session mailSession;

	// private MimeMessage emailMessage;

	// public static void main(String args[]) throws AddressException,
	// MessagingException {
	//
	// EmailGenerator javaEmail = new EmailGenerator();
	//
	// javaEmail.setMailServerProperties();
	// javaEmail.createEmailMessage();
	// javaEmail.sendEmail();
	// }

	public void setMailServerProperties() {

		String emailPort = "587";// gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void sendEmail(String subject, String messageBody, String[] toEmails)
			throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "dabaomealorderapp";// just the id alone without
												// @gmail.com
		String fromUserEmailPassword = "{gxuvimr}";
		MimeMessage emailMessage = createEmailMessage(subject, messageBody, toEmails);
		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

	private MimeMessage createEmailMessage(String subject, String messageBody, String[] toEmails)
			throws AddressException, MessagingException {
		// String[] toEmails = {
		// "ginji_volts@hotmail.com","boonhui.koh.2013@sis.smu.edu.sg" };
		String emailSubject = subject;
		String emailBody = messageBody;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		MimeMessage emailMessage = null;
		try {
			emailMessage = new MimeMessage(mailSession);

			for (int i = 0; i < toEmails.length; i++) {
				emailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(
						toEmails[i]));
			}

			emailMessage.setSubject(emailSubject);
			emailMessage.setContent(emailBody, "text/html");// for a html email
			// emailMessage.setText(emailBody);// for a text email
			emailMessage.setFrom(new InternetAddress("no_reply@kohBus_DaoBaoApp.com",
					"KOH BUS DABAO APP"));

			emailMessage.setReplyTo(InternetAddress.parse("no_reply@kohBus_DaoBaoApp.com", false));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailMessage;
	}

	/*
	 * public void createEmailMessageTemplate(String[] toEmails) throws
	 * AddressException, MessagingException { // String[] toEmails = { //
	 * "ginji_volts@hotmail.com","boonhui.koh.2013@sis.smu.edu.sg" }; String
	 * emailSubject = "Outstanding Payment"; String emailBody =
	 * "Dear User,</br>" +
	 * " You have an outsanding payments. Please log in to the DABAO App to resolve your outstanding payments.</br>"
	 * +
	 * " You will not be able to place new orders until these outstanding payments have been resolved.</br>"
	 * + "</br>Regards,</br>Admin</br>" +
	 * "</br>This is a system-generated email; please DO NOT REPLY to this email."
	 * ;
	 * 
	 * mailSession = Session.getDefaultInstance(emailProperties, null); try {
	 * emailMessage = new MimeMessage(mailSession);
	 * 
	 * for (int i = 0; i < toEmails.length; i++) {
	 * emailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(
	 * toEmails[i])); }
	 * 
	 * emailMessage.setSubject(emailSubject); emailMessage.setContent(emailBody,
	 * "text/html");// for a html email // emailMessage.setText(emailBody);//
	 * for a text email emailMessage.setFrom(new
	 * InternetAddress("no_reply@kohBus_DaoBaoApp.com", "KOH BUS DABAO APP"));
	 * 
	 * emailMessage.setReplyTo(InternetAddress.parse("no_reply@kohBus_DaoBaoApp.com"
	 * , false)); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void sendEmailTemplate() throws AddressException,
	 * MessagingException {
	 * 
	 * String emailHost = "smtp.gmail.com"; String fromUser =
	 * "dabaomealorderapp";// just the id alone without // @gmail.com String
	 * fromUserEmailPassword = "{gxuvimr}";
	 * 
	 * Transport transport = mailSession.getTransport("smtp");
	 * 
	 * transport.connect(emailHost, fromUser, fromUserEmailPassword);
	 * transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
	 * transport.close(); System.out.println("Email sent successfully."); }
	 */

}