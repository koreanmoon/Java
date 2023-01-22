package heritage;

import java.util.Random;

public class xoBoard {
	static Random rnd = new Random();

	public static void main(String[] args) {
		char[][] board = createBoard('x','o');
		printBoard(board);
		
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
	public static int getRandomInt(int low, int high) {
		int r = rnd.nextInt(high - low) + low;
		return r;
		
	}
	

}
