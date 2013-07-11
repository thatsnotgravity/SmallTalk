package Server;

import java.security.NoSuchAlgorithmException;

public class AccountHandler {

	public Account loginAccount;


	/**
	 * Constructor for the account.
	 */
	public AccountHandler() {

	}

	/**
	 * Handles logging in for the account.
	 *
	 * @param username The username of the account
	 * @param password The password of the account
	 * @return A boolean value of true if account exists.
	 * @throws NoSuchAlgorithmException
	 */

	public boolean loginAccountHandler(String username, String password) throws NoSuchAlgorithmException {

		if (loginAccount.doesAccountExist(username)) {
			if (attemptLogin(password))
				return true;
		}
		return false;
	}

	/**
	 * Creates an account.
	 *
	 * @param username The username to be associated with the account
	 * @param password The password to be associated with the account
	 * @param email    The email to be assocaited with the account.
	 * @return A boolean value of false if the account already exists
	 */

	public boolean createAccountHandler(String username, String password, String email) {

		if (!loginAccount.doesAccountExist(username)) {
			loginAccount.createUserAccount(username, password, email);
			return true;
		}
		return false;
	}

	/**
	 * Handles password recovery.
	 *
	 * @param email The email to be used for recovery.
	 * @return An account object associated with the email.
	 */

	public Account forgotAccountHandler(String email) {

		if (loginAccount.doesAccountExist(email)) {
			loginAccount.fetchAccountInfo(email);
			return loginAccount;
		}
		return null;

	}

	/**
	 * Attempt to login using the supplied password.
	 *
	 * @param password The password to be used.
	 * @return A boolean value of true if the password is the correct password.
	 * @throws NoSuchAlgorithmException
	 */

	public boolean attemptLogin(String password) throws NoSuchAlgorithmException {

		if (loginAccount.getPassword().equals(loginAccount.hashPassword(password)))
			return true;
		else
			return false;
	}
}
