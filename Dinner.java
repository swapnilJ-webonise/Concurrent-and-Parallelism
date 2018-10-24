import java.util.*;

class Philosopher implements Runnable {
	private final Object left;
	private final Object right;
	String action = "";
	int timetaken;

	public Philosopher(Object left, Object right) {
		this.left = left;
		this.right = right;
	}

	private void startDoing(String action) throws InterruptedException {
		timetaken = ((int) (Math.random() * 5000));

		System.out.println(Thread.currentThread().getName() + " is " + action);
		Thread.sleep(timetaken);
	}

	private void startEat() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " is Eating for time " + timetaken + "\n\n");
		Thread.sleep(timetaken);
	}

	public void run() {
		try {
			while (true) {
				startDoing("Thinking");
				synchronized (left) {
					startDoing("getting left fork");
					synchronized (right) {
						startDoing("getting right fork\n");
						startEat();
						startDoing("releasing right fork");
					}
					startDoing("release left fork ,Thinking again\n\n");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
}

public class Dinner {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("total philosophers you want: ");
		int phils = sc.nextInt();
		System.out.println(phils);
		final Philosopher[] philosophers = new Philosopher[phils];
		Object[] chopsticks = new Object[philosophers.length];
		for (int i = 0; i < chopsticks.length; i++) {
			chopsticks[i] = new Object();
		}
		for (int i = 0; i < philosophers.length; i++) {
			Object left = chopsticks[i];
			Object right = chopsticks[(i + 1) % chopsticks.length];

			if (i == (philosophers.length - 1)) {
				philosophers[i] = new Philosopher(right, left);
			} else {
				philosophers[i] = new Philosopher(left, right);
			}
			Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
			t.start();
		}
	}
}
