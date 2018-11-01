package diningChanges;

import java.util.Scanner;

public class Dinner {
  public static void main(String[] args) throws Exception {
    Scanner philosopherName = new Scanner(System.in);
    System.out.println("total philosophers you want: ");
    int philosopherCount = philosopherName.nextInt();
    System.out.println(philosopherCount);
    final Philosophers[] philosophers = new Philosophers[philosopherCount];
    Object[] chopsticks = new Object[philosophers.length];

    for (int chopstickNumber = 0; chopstickNumber < chopsticks.length; chopstickNumber++) {
      chopsticks[chopstickNumber] = new Object();
    }

    for (int philosopherNumber = 0; philosopherNumber < philosophers.length; philosopherNumber++) {
      Object leftChopstic = chopsticks[philosopherNumber];
      Object rightChopstic = chopsticks[(philosopherNumber + 1) % chopsticks.length];

      if (philosopherNumber == (philosophers.length - 1)) {
        philosophers[philosopherNumber] = new Philosophers(rightChopstic, leftChopstic);
      } else {
        philosophers[philosopherNumber] = new Philosophers(leftChopstic, rightChopstic);
      }
      Thread philosopherThread = new Thread(philosophers[philosopherNumber], "Philosopher " + (philosopherNumber + 1));
      philosopherThread.start();
    }
  }
}