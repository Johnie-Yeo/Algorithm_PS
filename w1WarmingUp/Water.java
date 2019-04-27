package w1WarmingUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Water {
	private static final int MAX = 1000000;
	private static final int BLOCK = 1;
	private int [][]blocks;
	
	private void readFile() {
		int []tmp;
		int T, N;
		try {
			Scanner in = new Scanner(new File("input03.txt"));
			T = in.nextInt();
			if(T > 20) {
				System.out.println("The number of test case shouldn't be greater than 20");
				in.close();
				return;
			}
			for(int i = 0; i < T; i++) {
				N = in.nextInt();
				if(N > MAX) {
					System.out.println("N shouldn't be greater than a million.");
					in.close();
					return;
				}
				tmp = new int[N];
				int max = 0;
				for(int j = 0; j < N; j++) {
					tmp[j] = in.nextInt();
					if(tmp[j] > max)
						max = tmp[j];
				}
				blocks = new int[max][N];
				buildBlocks(N, tmp);
				calculateAmount(max, N);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No such file exists");
		}
		
	}
	private void calculateAmount(int m, int n) {
		int result = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(blocks[i][j] == 0) {
					int tmp = betweenBlocks(i, j, n);
					if(tmp > 0) {
						result += tmp;
						j += tmp;
					}
				}
			}
		}
		System.out.println(result);
	}
	private int betweenBlocks(int i, int j, int n) {
		if(j == 0)
			return -1;
		if(blocks[i][j-1] == BLOCK) {
			for(int k = j+1; k < n ;k++) {
				if(blocks[i][k] == BLOCK)
					return k-j;
			}
		}
		return 0;
	}
	private void buildBlocks(int N, int[] tmp) {
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < tmp[i]; j++) {
				blocks[j][i] = BLOCK;
			}
		}
	}
	public static void main(String[] args) {
		Water app = new Water();
		app.readFile();
	}

}
