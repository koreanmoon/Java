package heritage;

import java.util.Scanner;

public class SumofEvenNumber {

	public static void main(String[] args) {
		Scanner random = new Scanner(System.in);
		System.out.println("Write a positive integer:");

		int enter = Integer.parseInt(random.nextLine().trim());
		random.close();
		if (enter <= 0) {
			System.out.println("Write a positive one please!");



		} else {

			int sum = 0;

			for (int i = 0; i <= enter; i += 2) {

				sum += i;

			}
			System.out.println("the sum of all even number till " +enter+ " is " + sum);
		}
	}

}
