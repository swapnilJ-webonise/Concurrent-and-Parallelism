package dining;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.*; 

public class DiningPhilosophers {
	 static int fork ;
	 
	 void takeFork() {
			fork--;
		}
		
		void putFork() {
			fork++;
		}
		
		int getFork() {
			return fork;
		}
		
	 
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		Semaphore sem = new Semaphore(1);
		System.out.println("total philosophers you want: ");
		
		int phils = Integer.parseInt(br.readLine());
		fork = phils;
		String[] names =new String[phils];
		 
		for(int i=0;i<phils;i++) {
			names[i] = br.readLine();
		}
		
		final Philosopher1[] philosophers = new Philosopher1[phils];
		Object[] chopsticks = new Object[philosophers.length];
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new Object();
		}
		for (int i = 0; i < philosophers.length; i++) {
			Object left = chopsticks[i];
			Object right = chopsticks[(i + 1) % chopsticks.length];

			if (i == (philosophers.length - 1)) {
				philosophers[i] = new Philosopher1(sem,right, left);
			} else {
				philosophers[i] = new Philosopher1(sem,left, right);
			}
			Thread t = new Thread(philosophers[i], names[i]);
			t.start();
		}
	}
	
	
	
}
