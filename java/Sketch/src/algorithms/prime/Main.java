package algorithms.prime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader  br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					System.in));

			System.out
					.print("Please enter the max number to generate Prime numbers: ");
			String input = br.readLine();
			System.out.println("Number Entered: " + input);

			System.out.println("Starting the generation!");
			long time = System.nanoTime();
			int[] primes = Sieve.generatePrimes(Integer.parseInt(input));
			time = System.nanoTime() - time;
			System.out.println("Finished!");
			System.out.println("Time: " + (double) (time / 1000000000.0)
					+ " Seconds");
			br.readLine();
			System.out.println("Printing Primes:");
			for (int i = 0; i < primes.length; i++) {
				System.out.print(primes[i] + ", ");
			}

		} finally {
			if(br != null)
				br.close();
		}
	}

}
