package w5Midterm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MinimumTurnLeft {
	int N;
	int [][]maze;
	Pos [][]dirMaze;
	int MIN;
	private class Pos{
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Object other) {
			if(x == ((Pos)other).x && y == ((Pos)other).y)
				return true;
			return false;
		}
		public Pos add(Pos other) {
			return new Pos(x + other.x, y + other.y);
		}
	}
	private final int BLOCK = 1;
	private final Pos East = new Pos(0, 1);
	private final Pos West = new Pos(0, -1);
	private final Pos North = new Pos(-1, 0);
	private final Pos South = new Pos(1, 0);
	
	private void readFile() {
		try {
			Scanner in = new Scanner(new File("input2.txt"));
			int T = in.nextInt();
			for(int t = 0; t < T; t++) {
				N = in.nextInt();
				maze = new int[N][N];
				dirMaze = new Pos[N][N];
				initializeDirMaze();
				MIN = -1;
				for(int i = 0; i < N; i++)
					for(int j = 0; j < N; j++)
						maze[i][j] = in.nextInt();
				Pos cur = new Pos(0, 0);
				System.out.println(findMaze(cur, East, 0));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file");
		}
	}

	private int findMaze(Pos cur, Pos dir, int turnLeft) {
		if(cur.x < 0 ||cur.y < 0 || cur.x >= N || cur.y >= N || maze[cur.x][cur.y] == BLOCK)
			return -1;
		else if(dirMaze[cur.x][cur.y].equals(dir) || dirMaze[cur.x][cur.y].equals(new Pos(-dir.x, -dir.y)))
			return -1;
		else if(cur.x == N-1 && cur.y == N-1) {
			if(MIN == -1 || turnLeft < MIN)
				MIN = turnLeft;
			if(MIN == 0)
				return MIN;
		}
		else {
			Pos tmp = dirMaze[cur.x][cur.y];
			dirMaze[cur.x][cur.y] = dir;
			
			int a = findMaze(cur.add(dir), dir, turnLeft);
			int b = findMaze(cur.add(turnRight(dir)), turnRight(dir), turnLeft);
			int c = findMaze(cur.add(turnLeft(dir)), turnLeft(dir), turnLeft + 1);
			
			if(a >= 0 || b >= 0 || c>= 0)
				dirMaze[cur.x][cur.y] = tmp;
		}
		return MIN;
	}
	private void initializeDirMaze() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				dirMaze[i][j] = new Pos(0, 0);		
	}
	private Pos turnRight(Pos dir) {
		if(dir.equals(East))
			return South;
		else if(dir.equals(West))
			return North;
		else if(dir.equals(North))
			return East;
		else
			return West;
	}
	private Pos turnLeft(Pos dir) {
		if(dir.equals(East))
			return North;
		else if(dir.equals(West))
			return South;
		else if(dir.equals(North))
			return West;
		else
			return East;
	}
	public static void main(String[] args) {
		MinimumTurnLeft app = new MinimumTurnLeft();
		app.readFile();
	}

}
