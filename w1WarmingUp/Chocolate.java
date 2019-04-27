package w1WarmingUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Chocolate {
	private static final int MAX = 1000000;
	private int[] packet;
	private int N, M, K;
	
	private void readFile() {
		try {
			Scanner in = new Scanner(new File("input01.txt"));
			K = in.nextInt();
			if(K > 20) {
				System.out.println("K shouldn't be greater than 20");
				in.close();
				return;
			}
			for(int i = 0; i < K ;i++) {
				N = in.nextInt();
				M = in.nextInt();
				if(N > MAX || M > MAX) {
					System.out.println("N and M shouldn't be greater than a million.");
					continue;
				}
				packet = new int [N];
				for(int j = 0; j < N; j++) {
					packet[j] = in.nextInt();
				}
				Arrays.sort(packet,0,N);
				int result = getMinimumDifference(packet, M);
				System.out.println(result);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No data file.");
		}
	}

	private int getMinimumDifference(int[] arr, int m) {
		int min = arr[m-1]-arr[0];
		for(int i=1; i <= arr.length-m; i++) {
			int tmp = arr[i+m-1] - arr[i];
			if(tmp < min)
				min = tmp;
		}
		return min;
	}

	public static void main(String[] args) {
		Chocolate app = new Chocolate();
		app.readFile();
	}
}
