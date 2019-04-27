package w1WarmingUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Platforms {
	
	private static final int MAX = 100000;
	private int []platform = new int [2500];
	private int nplatform = 0;
	
	private void readFile() {
		try {
			Scanner in = new Scanner(new File("input02.txt"));
			int T, N, destin, depart;
			T = in.nextInt();
			if(T >20) {
				System.out.println("The number of test case shouldn't be greater than 20");
				in.close();
				return;
			}
			for(int i = 0; i < T; i++) {
				initializePlatform();
				N = in.nextInt();
				if(N > MAX){
					System.out.println("The number of train shouldn't be greater than 100,000");
					in.close();
					return;
				}
				for(int j = 0; j < N ;j++) {
					destin = in.nextInt();
					depart = in.nextInt();
					for(int k = destin; k < depart ; k++) {
						platform[k]++;
						if(platform[k] > nplatform)
							nplatform = platform[k];
					}
				}
				System.out.println(nplatform);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("No such file exists.");
		}
	}
	private void initializePlatform() {
		for(int i=0; i < 2500 ; i++) {
			platform[i] = 0;
		}
	}
	public static void main(String[] args) {
		Platforms pf = new Platforms();
		pf.readFile();
	}

}
