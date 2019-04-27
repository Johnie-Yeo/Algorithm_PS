package w3Intermediate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SubString {
	int N, K;
	private void processCommand() { // read input08
		Scanner in;
		try {
			in = new Scanner(new File("input08.txt"));
			int T = in.nextInt();
			for(int i = 0; i < T; i++) {
				N = in.nextInt();
				K = in.nextInt();
				Queue<Character> q = new LinkedList<>();
				int []alpa = new int[26];
				String str = in.next();
				int max = 0;
				int count = 0;
				for(int j = 0; j < N; j++) {
					char tmp = str.charAt(j);
					q.offer(tmp);
					count++;
					int num = tmp - 65;
					if(alpa[num] < K) {
						alpa[num]++;
					}
					else {				
						while(q.peek() != tmp) {
							int tmpNum = q.poll() - 65;
							alpa[tmpNum]--;
							count--;
						}
						q.poll();
						count--;
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
	public static void main(String[] args) {
		SubString app = new SubString();
		app.processCommand();
	}

}
