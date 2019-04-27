package w3Intermediate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Subsequence {
	
	private void processCommand() {
		Scanner in;
		try {
			in = new Scanner(new File("input09.txt"));
			int T = in.nextInt();
			for(int t = 0; t < T; t++) {
				int N = in.nextInt();
				ArrayList<Integer> data = new ArrayList<>();
				int count = 0, max = 0;
				for(int i = 0; i < N; i++) {
					data.add(in.nextInt());
					count++;
					int idx = isExistData(data, i);
					if( idx >= 0) {
						count = i - idx;
						for(int j = 0; j <= idx; j++) {
							data.remove(j);
							i--;N--;
						}
					}
					if(count > max)
						max = count;
				}
				System.out.println(max);
			}
			in.close();			
		} catch (FileNotFoundException e) {
			System.out.println("No file");
		}
	}
	private int isExistData(ArrayList<Integer> data, int idx) {
		for(int i = 0; i < idx ; i++) {
			if(data.get(i) == data.get(idx))
				return i;
		}
		return -1;
	}
	public static void main(String[] args) {
		Subsequence app = new Subsequence();
		app.processCommand();
	}
}
