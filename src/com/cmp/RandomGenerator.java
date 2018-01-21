package com.cmp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomGenerator {
	public static String getRandom() {
		String random = null;
		try {
			//Initialize SecureRandom
			//This is a lengthy operation, to be done only upon
			//initialization of the application
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

			//generate a random number
			String randomNum = new Integer(prng.nextInt()).toString();

			//get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result =  sha.digest(randomNum.getBytes());
			random = hexEncode(result);
			System.out.println("Random number: " + randomNum);
			System.out.println("Message digest: " + random);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return random;
	}
	
	
	static private String hexEncode(byte[] aInput){
		StringBuilder result = new StringBuilder();
		char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[ (b&0xf0) >> 4 ]);
			result.append(digits[ b&0x0f]);
		}
		return result.toString();
	}
}
