package heritage;

import java.util.Scanner;

public class lab5 {


	public static void main(String[] args) {

		Scanner keyIn1 = new Scanner(System.in);

		char choice = 0;


		do {
			if(choice!='c') {System.out.println("your name?");}
			Scanner keyIn = new Scanner(System.in);
			String myInput = keyIn.nextLine();
			choice = showNameChoices();
			switch(choice) {
			case 's':

				System.out.println(myInput);
				break;
			case 'c':
				System.out.println("your new name?");

				break;	
			case 'q':
				System.out.println("bye");
				break;

			}

		}while (choice != 'q');
			keyIn1.close();
	}


	public static char showNameChoices() {


		System.out.println("Name Choices");

		System.out.println("s. Show name:");
		System.out.println("c. Change name:");

		System.out.println("q. Quit");

		Scanner keyIn1 = new Scanner(System.in);

		char nameChoice = keyIn1.nextLine().trim().toLowerCase().charAt(0);


		return nameChoice;
	}

}
