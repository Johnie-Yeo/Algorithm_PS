package w2Log_N;

import java.util.Scanner;

public class LanCable {
	private int K, N;
	private long []lanCable;
	private long max;
	
	private void procesCommand() {
		Scanner kb = new Scanner(System.in);
		K = kb.nextInt();
		N = kb.nextInt();
		lanCable = new long[K];
		max = 0;
		for(int i = 0; i < K; i++) {
			lanCable[i] = kb.nextLong();
			if(lanCable[i] > max)
				max = lanCable[i];
		}
		kb.close();
	}
	private long getNumberCable(long val) {
		long sum = 0;
		for(int i = 0; i < K; i++) {
			sum += lanCable[i]/val;
		}
		return sum;
	}
	private long binarySearch(long begin, long end) {
		long result =0;
		while(begin <= end) {
			long middle = (begin+end)/2;
			long nCable = getNumberCable(middle);
			if( nCable < N )
				end = middle - 1;
			else {
				if(result < middle)
					result=middle;
				begin = middle + 1;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		LanCable app = new LanCable();
		app.procesCommand();
		long num = app.binarySearch(1, app.max);
		System.out.println(num);
	}

}

