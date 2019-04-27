package w3Intermediate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class GetMinimum {
	private static class Node{
		int data;
		int index;
		public Node(int data, int index) {
			this.data = data;
			this.index = index;
		}
	}
	public static void main(String[] args) throws IOException {
		int tmp;
		Deque<Node> DQ = new ArrayDeque<Node>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			tmp = Integer.parseInt(st.nextToken());
			while(!DQ.isEmpty() && DQ.peekLast().data > tmp)
				DQ.removeLast();
			DQ.addLast(new Node(tmp, i));
			if(DQ.peekFirst().index <= i - L)
				DQ.remove();
			bw.write(DQ.peekFirst().data + " ");
		}
		bw.flush();
		bw.close();
	}
}