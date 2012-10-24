package project.euler;

import java.math.BigInteger;

public class Problem20 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigInteger target = new BigInteger("100");
		
		for(int i = target.intValue(); i > 0; i--){
			target = target.multiply(new BigInteger(i+""));
		}

		char[] targetChars = target.toString().toCharArray();
		
		int res = 0;
		
		for (int i = 0; i < targetChars.length; i++) {
			int n = Character.getNumericValue(targetChars[i]);
			res += n;
		}
		
		System.out.println(res);
	}

}
