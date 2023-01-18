package heritage;

import java.util.Random;
import java.util.Scanner;

public class AdvancedGuessingGame {
	static Random rnd = new Random();

	public static void main(String[] args) {

		int d1 = getRandomInt(0, 100)+1;

		Scanner kin = new Scanner(System.in);

		int guess;
		int i;

		for (i = 0; i < 5; i++) {

			System.out.println("Guess the number between 1 to 100 within 5 trials.");


			guess = Integer.parseInt(kin.nextLine().trim());
			
			kin.close();


			if (d1 == guess) {
				System.out.println("Congratulations! You guessed the number.");
				break;
			} else if (d1 > guess && i != 4) {
				System.out.println("The number is greater than " + guess);
			} else if (d1 < guess && i != 4) {
				System.out.println("The number is less than " + guess);
			}
		}

		if (i == 5) {
			System.out.println("You have exhausted 5 trials, so you're lost, sorry!");

			System.out.println("The number was " + d1);
		}
	}

	public static int getRandomInt(int low, int high) {
		int r = rnd.nextInt(high - low) + low;

		return r;
	}

}
