package diningChanges;

class Philosophers implements Runnable {

  private final Object leftChopstic;
  private final Object rightChopstic;
  String philosopherAction = "";
  int timetaken;
  private static final int timeMultiplierInSeconds = 100;

  public Philosophers(Object leftChopstic, Object rightChopstic) {
    this.leftChopstic = leftChopstic;
    this.rightChopstic = rightChopstic;
  }

  private void startDoing(String philosopherAction) throws InterruptedException {
    timetaken = ((int) (Math.random() * timeMultiplierInSeconds));

    System.out.println(Thread.currentThread().getName() + " is " + philosopherAction);
    Thread.sleep(timetaken);
  }

  private void startEat() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " is Eating for time " + timetaken + " ms\n\n");
    Thread.sleep(timetaken);
  }

  public void run() {
    try {
      while (true) {
        startDoing("Thinking");
        synchronized (leftChopstic) {
          startDoing("getting left fork");
          synchronized (rightChopstic) {
            startDoing("getting right fork\n");
            startEat();
            startDoing("releasing right fork");
          }
          startDoing("release left fork ,Thinking again\n\n");
        }
      }
    } catch (InterruptedException interruptedException) {
      Thread.currentThread().interrupt();
      return;
    }
  }
}
