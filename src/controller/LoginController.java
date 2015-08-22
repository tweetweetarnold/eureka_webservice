package controller;

public class LoginController {
	
	public boolean authenticateUser(String username, String password) {
		String testUsername = "arnold";
		String testPassword = "123";
		return (testUsername.equals(username) && testPassword.equals(password));
	}

}
