package week05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PlanarGraph {
	public class Point{
		int x, y;
		public Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	public class Vertex{
		int no;
		Point point;
		boolean visit = false;
		public Vertex(int no, int x, int y) {
			this.no = no;
			point = new Point(x, y);
		}
		public Vertex(int no) {
			this.no = no;
		}
	}
	public void readFile() {
		try {
			Scanner in = new Scanner(new File("input13.txt"));
			int T = in.nextInt();
			for(int t = 0; t < T; t++) {
				int N = in.nextInt();
				int K = in.nextInt();
				@SuppressWarnings("unchecked")
				ArrayList<Vertex> []ver = new ArrayList[N]; 
				for(int n = 0; n < N; n++) {
					ver[n] = new ArrayList<>();
					ver[n].add(new Vertex(in.nextInt(), in.nextInt(), in.nextInt()));
					int numNext = in.nextInt();
					for(int i = 0; i < numNext; i++) {
						ver[n].add(new Vertex(in.nextInt()));
					}
				}
				clockwiseAngularSort(ver);
				int result = countKsizeCycle(ver, K);
				System.out.println(result);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No File");
		}
	}
	private void clockwiseAngularSort(ArrayList<Vertex>[] ver) {
		for(int idx = 0; idx < ver.length; idx++) {
			for(int i = ver[idx].size() - 1; i > 1; i--) {
				for(int j = 1; j < i; j++) {
					Point q = ver[ver[idx].get(j).no - 1].get(0).point;
					Point r = ver[ver[idx].get(j+1).no - 1].get(0).point;
					if(compare(ver[idx].get(0).point, q, r) > 0){
						Vertex tmp = ver[idx].get(j);
						ver[idx].set(j, ver[idx].get(j+1));
						ver[idx].set(j + 1, tmp);
					}
				}
			}	
		}
	}
	private int countKsizeCycle(ArrayList<Vertex>[] ver, int k) {
		int count = 0;
		for(int idx = 0; idx < ver.length; idx++) {
			Iterator<Vertex> iter = ver[idx].iterator();
			ArrayList<Integer> v = new ArrayList<>();
			v.add(iter.next().no - 1); 
			while(iter.hasNext()){
				Vertex cur = iter.next();
				v.add(1, cur.no - 1);
				if(!cur.visit) {
					cur.visit = true;
					int i = 1;
					while(v.get(i) != v.get(0)) {
						v.add(i+1, circularNextNode(v.get(i-1), ver[v.get(i)])-1);
						i += 1;
					}
					if(i == k) {
						ArrayList<Vertex> left = findLeftVertex(ver);
						if(left.get(0).no - 1 == v.get(0) &&
								left.get(1).no -1 == v.get(1))
							continue;
						count++;
					}
				}
			}
		}
		return count;
	}
	private ArrayList<Vertex> findLeftVertex(ArrayList<Vertex>[] ver) {
		ArrayList<Vertex> min = ver[0];
		for(int i = 1; i < ver.length; i++)
			if(min.get(0).point.x > ver[i].get(0).point.x)
				min = ver[i];
		return min;
	}
	private int circularNextNode(int i, ArrayList<Vertex> ver) {
		Iterator<Vertex> iter = ver.iterator();
		while(iter.hasNext()) {
			Vertex next = iter.next();
			if(next.no == i + 1)
				break;
		};
		Vertex tmp;
		if(!iter.hasNext())
			tmp = ver.get(1);
		else
			tmp  =iter.next();
		tmp.visit = true;
		return tmp.no;
	}
	public int compare(Point p, Point q, Point r) {
		Point q2 = new Point(q.x - p.x, q.y - p.y);
		Point r2 = new Point(r.x - p.x, r.y - p.y);
		
		if(q2.x >= 0 && r2.x < 0)
			return -1;
		if(q2.x <= 0 && r2.x > 0)
			return 1;
		if(q2.x == 0 && r2.x == 0) {
			if(q2.y >= 0 && r2.y < 0)
				return -1;
			else
				return 1;
		}
		return crossProduct(q2, r2);
	}
	private int crossProduct(Point q2, Point r2) {
		return q2.x * r2.y - q2.y * r2.x;
	}
	public static void main(String []args) {
		PlanarGraph app = new PlanarGraph();
		app.readFile();
	}
}
