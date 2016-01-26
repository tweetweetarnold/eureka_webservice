package services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMail {
	public static void main(String[] args) {
	final String username = "dabaomealorderapp@gmail.com";
	final String password = "{gxuvimr}";
	
	Properties props = new Properties();
	props.put("mail.smtp.auth","true");
	props.put("mail.smtp.starttls.enable","true");
	props.put("mail.smtp.host","smtp.gmail.com");
	props.put("mail.smtp.port","587");
	
	Session session = Session.getInstance(props, new javax.mail.Authenticator () {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	});
	
	try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("dabaomealorderapp@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("boonhui.koh.2013@sis.smu.edu.sg"));
		message.setSubject("My First Email");
		String msg = "Dear user,</br>"
				+ "We are happy to grant you access to NEC Monitoring Health Application.</br>Please login with the below: </br>Username: " + username + "</br> Password: " + password + "</br> We hope that you will find our apps useful.</br> Thank you."
				+ "<p><p><p> Regards,</br>Admin";
		message.setText(msg);
		message.setContent(msg,"text/html; charset=utf-8");
		Transport.send(message);
		
		System.out.println("Was the email sent: Done");
		
		
	} catch (MessagingException e) {
		e.printStackTrace();
	}
	}
}
