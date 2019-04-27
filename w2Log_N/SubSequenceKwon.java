package w2Log_N;

import java.util.PriorityQueue;
import java.util.Scanner;

public class SubSequenceKwon {
	int N, X;
	int []data;
	private class Item implements Comparable<Item>{
		private int index;
		private int value;
		public Item(int i, int s) {
			index =i;
			this.value =s;
		}
		@Override
		public int compareTo(Item other) {
			return value <= other.value ? -1:1;
		}
	}
	private void processCommand() {
		Scanner kb = new Scanner(System.in);
		int T = kb.nextInt();
		for(int i=0; i<T; i++) {
			N = kb.nextInt();
			X = kb.nextInt();
			data = new int[N+1];
			for(int j = 1; j <= N; j++)
				data[j] = kb.nextInt();
			int len = shortestLength();
			System.out.println(len);
		}
		kb.close();
	}
	private int shortestLength() {
		int []sum = new int[N + 1];
		int base = 0, begin = 1;
		int maxIndex = begin-1;
		int minLen = (int)Double.POSITIVE_INFINITY;
		PriorityQueue<Item> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			sum[i] = sum[i-1] + data[i];
			pq.add(new Item(i, sum[i]));
			if(sum[i] - base <= 0) {
				base = sum[i];
				begin = i+1;
			}
			else if(sum[i]-base >= X) {
				while(pq.peek().value <= sum[i] - X) {
					Item item = pq.remove();
					if(item.index > maxIndex)
						maxIndex = item.index;
				}
				if(minLen > i - maxIndex)
					minLen = i - maxIndex;
				base = sum[maxIndex];
				begin = maxIndex + 1;
			}
		}
		if(minLen == (int)Double.POSITIVE_INFINITY)
			return -1;
		return minLen;
	}
	public static void main(String[] args) {
		SubSequenceKwon app = new SubSequenceKwon();
		app.processCommand();
	}

}
