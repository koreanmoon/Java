package Moon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class phoneBook {


	public static void main(String[] args) throws FileNotFoundException {

		Scanner keyIn1 = new Scanner(System.in);

		char choice = 0;

		
		
		do {
			choice = showMenuChoices();
			
			switch(choice) {
			case 's':
				
				File lab6List = new File("lab6List.txt");
				Scanner listFile = new Scanner(lab6List);
				while (listFile.hasNextLine()) {
					String contact = listFile.nextLine().trim();
					String[] contact2 = contact.split(":");
					System.out.println(contact2[0]+ "  "+ contact2[1]);
					
				}
				listFile.close();

				break;
			case 'a':
				System.out.println("Add the new name");
				String name = keyIn1.nextLine().trim();
				System.out.println("Add the new phone number");
				String number = keyIn1.nextLine().trim();
				PrintStream psFile = new PrintStream(new FileOutputStream("lab6List.txt",true));
				psFile.println(name + ":" + number);
				
				psFile.close();
				

				break;	
			case 'q':
				System.out.println("bye");
				break;

			}

		}while (choice != 'q');
			keyIn1.close();
	}


	public static char showMenuChoices() {


		System.out.println("Menu Choices");

		System.out.println("s. Show numbers:");
		System.out.println("a. Add entry:");

		System.out.println("q. Quit");

		Scanner keyIn1 = new Scanner(System.in);

		char menuChoice = keyIn1.nextLine().trim().toLowerCase().charAt(0);


		return menuChoice;
	}

}
