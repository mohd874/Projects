package algorithms.prime;


/*
 * This code was copied from "Clean Code" book by Robert C. Martin, Page 71
 */

/**
 * This class Generates prime numbers up to a user specified
 * maximum. The algorithm used is the Sieve of Eratosthenes.
 * <p>
 * Eratosthenes of Cyrene, b. c. 276 BC, Cyrene, Libya --* d. c. 194, Alexandria. The first man to calculate the
 * circumference of the Earth. Also known for working on
 * calendars with leap years and ran the library at Alexandria.
 * <p>
 * The algorithm is quite simple. Given an array of integers
 * starting at 2. Cross out all multiples of 2. Find the next
 * uncrossed integer, and cross out all of its multiples.
 * Repeat untilyou have passed the square root of the maximum
 * value.
 *
 * @author Alphonse
 * @version 13 Feb 2002 atp
 */

public class Sieve {
	/**
	 * @param maxValue
	 *            is the generation limit.
	 */
	public static int[] generatePrimes(int maxValue) {
		if (maxValue >= 2) // the only valid case
		{
			// declarations
			int s = maxValue + 1; // size of array
			boolean[] f = initBooleanArray(s);
			// sieve
			findPrimes(s, f);
			// how many primes are there?
			int count = countPrimes(s, f);
			int[] primes = getPrimes(s, f, count);
			return primes; // return the primes
		} else
			// maxValue < 2
			return new int[0]; // return null array if bad input.
	}

	private static int[] getPrimes(int s, boolean[] f, int count) {
		int i;
		int j;
		int[] primes = new int[count];
		// move the primes into the result
		for (i = 0, j = 0; i < s; i++) {
			if (f[i]) // if prime
				primes[j++] = i;
		}
		return primes;
	}

	private static int countPrimes(int s, boolean[] f) {
		int i;
		int count = 0;
		for (i = 0; i < s; i++) {
			if (f[i])
				count++; // bump count.
		}
		return count;
	}

	private static void findPrimes(int s, boolean[] f) {
		int i;
		for (i = 2; i < Math.sqrt(s) + 1; i++) {
			if (f[i]) // if i is uncrossed, cross its multiples.
			{
				markMultipliesFalse(s, f, i);
			}
		}
	}

	private static void markMultipliesFalse(int s, boolean[] f, int i) {
		for (int j = 2 * i; j < s; j += i)
			f[j] = false; // multiple is not prime
	}

	private static boolean[] initBooleanArray(int s) {
		boolean[] f = createBooleanArray(s);
		// initialize array to true.
		setAllToTrue(s, f);
		// get rid of known non-primes
		getRidOfKnowNonPrime(f);
		return f;
	}

	private static boolean[] createBooleanArray(int s) {
		return new boolean[s];
	}

	private static void getRidOfKnowNonPrime(boolean[] f) {
		f[0] = f[1] = false;
	}

	private static void setAllToTrue(int s, boolean[] f) {
		for (int i = 0; i < s; i++)
			f[i] = true;
	}
}