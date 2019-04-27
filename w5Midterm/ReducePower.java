package w5Midterm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class ReducePower {
	private class Attribute{
		int power;
		int charge;
		public Attribute(int power, int charge){
			this.power = power;
			this.charge = charge;
		}
		public Attribute add(Attribute other) {
			return new Attribute(power + other.power, charge + other.charge);
		}
	}
	Attribute MAX;
	private void readFile() {
		try {
			Scanner in = new Scanner(new File("input3.txt"));
			int T = in.nextInt();
			for(int t = 0; t < T; t++) {
				int M = in.nextInt();
				@SuppressWarnings("unchecked")
				ArrayList<Attribute>[] ecm = new ArrayList[M];
				MAX = new Attribute(0, 0);
				for(int m = 0; m < M; m++) {
					ecm[m] = new ArrayList<>();
					int N = in.nextInt();
					for(int n = 0; n < N; n++) {
						int charge = in.nextInt();
						int power = in.nextInt();
						ecm[m].add(new Attribute(power, charge));
					}
					ecm[m].add(new Attribute(0,0)); // 클래스에서 속성이 하나도 안들어가는 경우를 나타냄
				}
				int C = in.nextInt();
				Attribute tmp = new Attribute(0,0);
				System.out.println(maximumReducingPower(ecm, 0, C, tmp));
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file");
		}
	}
	private boolean isFeasible(Attribute cur, int C) {
		if(cur.charge <= C)
			return true;
		return false;
	}
	private int maximumReducingPower(ArrayList<Attribute>[] ecm, int level, int C, Attribute tmp) {
		if(level == ecm.length) {
			if(isFeasible(tmp, C)) {
				if((MAX.charge == 0 && MAX.power == 0) || MAX.power < tmp.power)
					MAX = tmp;
			}
		}
		else {
			Iterator<Attribute> iter = ecm[level].iterator();
			while(iter.hasNext()) {
				Attribute cur = iter.next();
				maximumReducingPower(ecm, level + 1, C, tmp.add(cur));
			}
		}
		return MAX.power;
	}
	public static void main(String[] args) {
		ReducePower app = new ReducePower();
		app.readFile();
	}

}
