package heritage;

import java.io.File;

import java.io.IOException;

import java.util.Scanner;

public class smilingFace {

	public static void main(String[] args) throws IOException {
		File file = new File("smile.tbi");
		Scanner listFile = new Scanner(file);

		String firstLine = listFile.nextLine().trim(); 
		String[] data1 = firstLine.split("x");

		char[][] bored = new char[Integer.parseInt(data1[1])]
				[Integer.parseInt(data1[0])];
		fillArray(bored, ' ');

		while (listFile.hasNextLine()){
			String restLine = listFile.nextLine().trim();

			String[] data2 = restLine.split(",");

			switch (data2[1].charAt(0)) {
			
			case 'd': {
				bored[Integer.parseInt(data2[3])][Integer.parseInt(data2[2])]
						= data2[0].charAt(0);
			}
			break;
			}
		}
		listFile.close();
		printBoard(bored);
	}
	public static char[][] createBoard(int j, int i, char fillValue){
		char[][] tempBoard = new char [j][i];
		
		fillArray(tempBoard, fillValue);
		return tempBoard;
	}

	public static void fillArray(char[][] board, char fillValue) {

		for (int j=0; j<board.length; j++) {
			for (int i =0; i<board[0].length;i++) {
				board[j][i]=fillValue  ;
			}
		}
	}
	public static void printBoard(char[][] board) {
		for (int j=0; j<board.length; j++) {
			for (int i =0; i<board[0].length;i++) {
				System.out.print(board[j][i]) ;
			}
			System.out.println();
		}
	}
}