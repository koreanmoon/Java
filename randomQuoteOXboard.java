package heritage;

import java.io.File;

import java.util.Scanner;
import java.util.Random;

public class randomQuoteOXboard {
	static Random rnd = new Random();
	static Scanner keyIn1 = new Scanner(System.in);



	public static void main(String[] args) throws Exception{

//		Scanner keyIn1 = new Scanner(System.in);

		int choice;
		
		
	
		do {
			choice = showMenuChoices();
			
			switch(choice) {
			case 1:
				char[][] board = createBoard('x','o');
				printBoard(board);
				break;
				
			case 2:
				File file = new File("quotes.txt");
				Scanner fileScanner = new Scanner(file);
				
				int numLines = 0;
				while (fileScanner.hasNextLine()){
					fileScanner.nextLine();
					numLines++;
					
				}
				fileScanner.close();
				fileScanner = new Scanner(file);
				String[] quotes = new String[numLines];
				int quoteNumber = 0;
				while (fileScanner.hasNextLine()) {
					quotes[quoteNumber] = fileScanner.nextLine().trim();
					quoteNumber++;
					
				}
				System.out.println("Quote of the day:");
				System.out.println(quotes[getRandomInt(0,numLines)]);
				break;

			case 3:
				System.out.println("bye");
				break;

			}

		}while (choice != 3);
			keyIn1.close();
	}


	public static int getRandomInt(int low, int high) {
		int r = rnd.nextInt(high - low) + low;
		return r;
		
	}


	public static int showMenuChoices() {


		System.out.println("Menu Choices");

		System.out.println("1: game board:");
		System.out.println("2: random quote:");

		System.out.println("3: quit");

//		Scanner keyIn1 = new Scanner(System.in);

		int menuChoice = Integer.parseInt(keyIn1.nextLine().trim());


		return menuChoice;
	}

	public static char[][] createBoard(char x, char o){	
		boolean marker = false;
		int width = getRandomInt(3,8)*2;
		int height = getRandomInt(3,6);
		char[][]myBoard = new char[height][width];
		for (int j=0; j<height; j++) {
			for (int i=0; i<width; i++) {
				if(marker) {
					myBoard[j][i]=x;
				}else {
					myBoard[j][i]=o;
				}
				marker = !marker;
			}
			marker = !marker;
		}
		return myBoard;
	}
	public static void printBoard(char[][] board) {
		for (int j=0; j<board.length; j++) {
			for (int i =0; i<board[j].length;i++) {
				System.out.print(board[j][i]) ;

			}
			System.out.println();
		}

	}
}
