package w4Enumerating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OnlyTurnRight {
	int N;
	int [][]maze;
	Pos [][]dirMaze;
	
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
			Scanner in = new Scanner(new File("input12.txt"));
			int T = in.nextInt();
			for(int t = 0; t < T; t++) {
				N = in.nextInt();
				maze = new int[N][N];
				dirMaze = new Pos[N][N];
				initializeDirMaze();
				for(int i = 0; i < N; i++)
					for(int j = 0; j < N; j++)
						maze[i][j] = in.nextInt();
				String result;
				Pos cur = new Pos(0, 0);
				if(findMaze(cur, East))
					result = "Yes";
				else
					result = "No";
				System.out.println(result);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file");
		}
	}

	private void initializeDirMaze() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				dirMaze[i][j] = new Pos(0,0);		
	}

	private boolean findMaze(Pos cur, Pos dir) {
		if(cur.x < 0 ||cur.y < 0 || cur.x >= N || cur.y >= N || maze[cur.x][cur.y] == BLOCK)
			return false;
		else if(dirMaze[cur.x][cur.y].equals(dir))
			return false;
		else if(cur.x == N-1 && cur.y == N-1)
			return true;
		else {
			dirMaze[cur.x][cur.y] = dir;
			if(findMaze(cur.add(dir), dir) || findMaze(cur.add(dir = turnRight(dir)), dir))
				return true;
		}
		return false;
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
	public static void main(String[] args) {
		OnlyTurnRight app = new OnlyTurnRight();
		app.readFile();
	}

}
