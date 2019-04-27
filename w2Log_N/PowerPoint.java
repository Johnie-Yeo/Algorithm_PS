package w2Log_N;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PowerPoint {
	private int N, M;
	private int []data;
	private void processCommand() {
		try {
			Scanner kb = new Scanner(new File("input05.txt"));
			int T = kb.nextInt();
			for(int i = 0; i < T; i++) {
				N = kb.nextInt();
				M = kb.nextInt();
				data = new int[N];
				for(int j = 0;j < N; j++) 
					data[j] = kb.nextInt();
				Arrays.sort(data, 0, N);
				int result = binarySearch(data[0], data[N-1]);
				System.out.println(result);
			}
			kb.close();
		} catch (FileNotFoundException e) {
			System.out.println("No File");
		}
	}
	private int binarySearch(int begin, int end) {
		int ans=0;
		while(begin <= end) {
			int middle = (begin + end)/2;
			if(isFeasible(middle)) {
				ans = middle;
				end = middle-1;
			}
			else
				begin = middle + 1;
		}
		return ans;
	}
	private boolean isFeasible(int middle) {
		int sum = 0;
		for(int i = 0; i < N; i++) 
			sum += (data[i]-1)/middle + 1;
		if(sum <= M)
			return true;
		return false;
	}
	public static void main(String[] args) {
		PowerPoint app = new PowerPoint();
		app.processCommand();
	}

}
