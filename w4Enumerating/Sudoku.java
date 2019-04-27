package w4Enumerating;

import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku {
	int size = 9;
	int [][]sudoku = new int[size][size];
	
	private class Point{
		int x,  y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	ArrayList<Point> vertex = new ArrayList<>();
	private void processCommand() {
		Scanner kb = new Scanner(System.in);
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++) {
				sudoku[i][j] = kb.nextInt();
				if(sudoku[i][j] == 0)
					vertex.add(new Point(i, j));
			}
		backTracking(0);
		kb.close();
	}
	private boolean backTracking(int index) {
		if(index == vertex.size())
			return true;

		int x = vertex.get(index).x;
		int y = vertex.get(index).y;
		for(int n = 1; n < 10; n++) {			
			if(isFeasible(x, y, n)) {
				sudoku[x][y] = n;
				if(backTracking(index + 1))
					return true;
				sudoku[x][y] = 0;
			}
		}
		return false;
	}

	private boolean isFeasible(int row, int col, int k) {
		if(k == 0)
			return false;
		for(int i = 0; i < size; i++) {
			if(sudoku[row][i] == k)
				return false;
			else if(sudoku[i][col] == k )
				return false;
		}
		int startX = (row/3) * 3;
		int startY = (col/3) * 3;
		for(int i = startX; i < startX + 3; i++)
			for(int j = startY; j < startY + 3; j++)
				if(sudoku[i][j] == k)
					return false;
		return true;
	}
	private void printSudoku() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++)
				System.out.print(sudoku[i][j] + " ");
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Sudoku app = new Sudoku();
		app.processCommand();
		app.printSudoku();
	}
}
