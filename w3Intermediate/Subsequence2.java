package w3Intermediate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

public class Subsequence2 {
	private class Node{
		int value;
		int count;
		public Node(int v) {
			value = v;
			count = 1;
		}
	}
	
	private void processCommand() {
		try {
			Scanner in = new Scanner(new File("input10.txt"));
			int T = in.nextInt();
			for(int t = 0; t < T; t++) {
				int N = in.nextInt();
				int K = in.nextInt();
				Queue<Integer> q = new LinkedList<>();
				LinkedList<Node> node = new LinkedList<>();
				int max = 0, count = 0;
				for(int n = 0; n < N; n++) {
					int tmp = in.nextInt();
					q.offer(tmp);
					count++;
					
					Node tmpNode = addCount(node, tmp);
					if(tmpNode.count > K) {
						while(q.peek() != tmp) {
							int tmpNum = q.poll();
							subCount(node, tmpNum);
							count--;
							
						}
						int tmpNum = q.poll();
						subCount(node, tmpNum);
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
	
	private void subCount(LinkedList<Node> node, int tmpNum) {
		ListIterator<Node> iter = node.listIterator();
		while(iter.hasNext()) {
			Node temp = iter.next();
			if(temp.value == tmpNum) {
				temp.count--;
				return;
			}
		}
	}

	private Node addCount(LinkedList<Node> node, int tmp) {
		ListIterator<Node> iter = node.listIterator();
		while(iter.hasNext()) {
			Node temp = iter.next();
			if(temp.value == tmp) {
				temp.count++;
				return temp;
			}
		}
		Node temp = new Node(tmp);
		node.add(temp);
		return temp;
	}
	
	public static void main(String[] args) {
		Subsequence2 app = new Subsequence2();
		app.processCommand();
	}

}
