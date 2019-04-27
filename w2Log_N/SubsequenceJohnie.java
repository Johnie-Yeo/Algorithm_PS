package w2Log_N;
import java.util.Scanner;

public class SubsequenceJohnie {
	int N, X;
	int []data;
	private void processCommand() {
		Scanner kb = new Scanner(System.in);
		int T = kb.nextInt();
		for(int i=0; i<T; i++) {
			N = kb.nextInt();
			X = kb.nextInt();
			data = new int[N];
			for(int j = 0; j < N; j++)
				data[j] = kb.nextInt();
			int len = shortestLength();
			System.out.println(len);
		}
		kb.close();
	}
	private int shortestLength() {
		int max = getMaximum();
		if(X < 0 && X > max)
			return -1;
		if(X <= max)
			return 1;
		int i = 2;
		for(; i <= N; i++) {
			for(int j = 0; j < N-i+1; j++) {
				int val = 0;
				for(int k = j; k < j+i ;k++) {
					val+=data[k];
				}
				if(val >= X)
					return i;
			}
		}
		return -1;
	}
	private int getMaximum() {
		int max = data[0];
		for(int i=1; i<N; i++)
			if(max < data[i])
				max = data[i];
		return max;
	}
	public static void main(String[] args) {
		SubsequenceJohnie app = new SubsequenceJohnie();
		app.processCommand();
	}

}
