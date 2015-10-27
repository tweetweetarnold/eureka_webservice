package services;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class EmailChecker {

	public static void main(String[] args) {

		UrlValidator urlValidator = new UrlValidator();
		boolean valid = urlValidator.isValid("http://sandeeppateltechblogs.blogspot.in/");
		System.out.print("URL is :" + valid + "\n");

		EmailValidator emailValidator = EmailValidator.getInstance();
		valid = emailValidator.isValid("sandeep_giet@yahoo.com");
		System.out.print("Mail is : " + valid + "\n");
		valid = emailValidator.isValid("taiyi@@@@@long.com");
		System.out.print("Second Mail is : " + valid + "\n");

		// VISA cards are 16 digit, starts with 4
		CreditCardValidator cardValidator = new CreditCardValidator(CreditCardValidator.VISA);
		valid = cardValidator.isValid("4111111111111111");
		System.out.print("Credit Card : " + valid + "\n");

	}

}
