package dining;



import java.util.concurrent.*; 

class Philosopher1 extends Thread {
	DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
	private final Object left;
	private final Object right;
	String action = "";
	int timetaken;
	Semaphore sem;
	
	public Philosopher1(Semaphore sem,Object left, Object right) {
		this.sem = sem;
		this.left = left;
		this.right = right;
	}
	
	public void run() {
		try {
			while (true) {
				startDoing("Thinking");
				synchronized (left) {
					diningPhilosophers.takeFork();
					startDoing("getting left fork");
					synchronized (right) {
						diningPhilosophers.takeFork();
						startDoing("getting right fork\n");
						startEat();
						diningPhilosophers.putFork();
						startDoing("releasing right fork");
					}
					diningPhilosophers.putFork();
					startDoing("release left fork ,Thinking again\n\n");
					sem.release();

				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
	
	private void startDoing(String action) throws InterruptedException {
		timetaken = ((int) (Math.random() * 3000));
		System.out.println("remaining forks: "+diningPhilosophers.getFork()+" "+Thread.currentThread().getName() + " is " + action);
		Thread.sleep(timetaken);
	}

	private void startEat() throws InterruptedException {
		sem.acquire();
		System.out.println("remaining forks: "+diningPhilosophers.getFork()+" "+Thread.currentThread().getName() + " is Eating for time " + timetaken + "\n\n");
		Thread.sleep(timetaken);
	}

	
}


