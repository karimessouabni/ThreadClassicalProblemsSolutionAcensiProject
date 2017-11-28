public class Philosopher implements Runnable {

    private String rightFork, leftFork;

    public Philosopher(String leftFork, String rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action + " at : " + System.nanoTime());
        Thread.sleep(((int) (Math.random() * 2)));
    }


    public void run() {
        try {
            while (true) {

                // thinking
                doAction(": Thinking");
                synchronized (leftFork) {
                    doAction(": Picked up left fork");
                    synchronized (rightFork) {
                        // eating
                        doAction(": Picked up right fork - eating");

                        doAction(": Put down right fork");
                    }

                    // Back to thinking
                    doAction(": Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("error" + e);
            Thread.currentThread().interrupt();
            return;
        }
    }


    public static void main(String[] args) throws Exception {
        Philosopher[] philosophers = new Philosopher[5];
        String[] forks = new String[5];


        for (int i = 0; i < forks.length; i++) {
            forks[i] = new String("fork" + i);

        }

        for (int i = 0; i < philosophers.length; i++) {
            String leftFork = forks[i];
            String rightFork = forks[(i + 1) % forks.length];


            philosophers[i] = (i == 0) ? new Philosopher(rightFork, leftFork) : new Philosopher(leftFork, rightFork);
            //philosophers[i] = new Philosopher(rightFork, leftFork); //DeadLock


            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }

    }
}
