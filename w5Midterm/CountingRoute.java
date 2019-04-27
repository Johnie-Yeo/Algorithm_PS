package w5Midterm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CountingRoute {
	ArrayList<Integer>[] vertex;
	int N;
	
	@SuppressWarnings("unchecked")
	private void processCommand() {
		try {
			Scanner in = new Scanner(new File("input1.txt"));
			int T = in.nextInt();
			for(int test = 0 ; test < T; test++) {
				N = in.nextInt();
				vertex = new ArrayList[N];
				for(int numV = 0; numV < N; numV++) {
					vertex[numV] = new ArrayList<>();
					int d = in.nextInt();
					for(int nextV = 0; nextV < d; nextV++) {
						vertex[numV].add(in.nextInt());
					}
				}
				int s = in.nextInt();
				int t = in.nextInt();
				int L = in.nextInt();
				int []check = new int[N];
				int result = DFS(s, t, 0, L, check);
				System.out.println(result);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file");
		}
	}
	private int DFS(int start, int destin, int length, int L, int[] check) {
		if(length >= L && start != destin)
			return 0;
		if(start == destin)
			return 1;
		else {
			check[start] = 1;
			Iterator<Integer> iter = vertex[start].iterator();
			int result = 0;
			while(iter.hasNext()) {
				int next = iter.next();
				if(check[next] == 1)
					continue;
				result += DFS(next, destin, length + 1, L, check);
			}
			check[start] = 0;
			return result;
		}
	}
	public static void main(String[] args) {
		CountingRoute app = new CountingRoute();
		app.processCommand();
	}
}
