import org.junit.jupiter.api.Test;

class PhlosopherTest {

    @Test
    void myFirstTest() {
        Philosopher[] philosophers = new Philosopher[5];
        String[] forks = new String[5];


        for (int i = 0; i < forks.length; i++) {
            forks[i] = new String("fork" + i);

        }

        for (int i = 0; i < philosophers.length; i++) {
            String leftFork = forks[i];
            String rightFork = forks[(i + 1) % forks.length];


            philosophers[i] = (i == 0) ? new Philosopher(rightFork, leftFork) : new Philosopher(leftFork, rightFork);


            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }

    }

}