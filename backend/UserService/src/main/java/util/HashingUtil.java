package util;

import org.mindrot.jbcrypt.BCrypt;

public class HashingUtil {

	private static final int num_rounds = 10;
	
	public static String hashPassword(String plainTextPassword)
	{
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(num_rounds));
	}
	
	public static boolean checkPassword(String plainTextPassword, String hashedPassword)
	{
		return BCrypt.checkpw(plainTextPassword, hashedPassword);
	}
}
