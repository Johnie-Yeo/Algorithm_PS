package w4Enumerating;

import java.util.Scanner;

public class P10EnumerateCombination {
	int N, K;
	private class K_naryNumber{ //조합의 모든 경우의 수를 K진수라고 봐도 무방. 단 각 자리 숫자는 앞 자리 숫자보다 항상 커야함.
		private int k;
		private int []num;
		public K_naryNumber(int k) {
			this.k = k;
			num = new int[k];
			for(int i = 0; i < k; i++)
				num[i] = i + 1;
		}
		public int add() {
			if(getFirst() == N - K + 1) {
				return -1;
			}
			num[k-1]++;
			carryDigit(k-1);
			return 1;
		}
		private void carryDigit(int idx) {
			while(num[idx] > N - (k - (idx + 1))) { // 각 자리 숫자의 최댓값은 N - (k - (index + 1))
				num[idx-1]++;
				num[idx] = num[idx-1] + 1;
				if(num[idx] > N - (k - (idx + 1))) {
					carryDigit(idx-1);
					num[idx] = num[idx-1] + 1;
				}
				idx--;
			}
		}
		public int getFirst() {
			return num[0];
		}
		public void printNum() {
			for(int i = 0; i < k; i++)
				System.out.print(num[i] + " ");
			System.out.println();
		}
	}
	private void processCommand() {
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		K = kb.nextInt();
		kb.close();
	}
	private void getCombination() {
		K_naryNumber comb = new K_naryNumber(K);
		comb.printNum();
		while(comb.add() > 0) {
			comb.printNum();
		}
	}
	public static void main(String[] args) {
		P10EnumerateCombination app = new P10EnumerateCombination();
		app.processCommand();
		app.getCombination();
	}

}
