
public class Database {
    private int readers; // number of active readers
    final int DELAY = 1000;

    /**
     * Initializes this database.
     */
    public Database() {
        this.readers = 0;
    }

    /**
     * Read from this database.
     *
     * @param number Number of the reader.
     */
    public void read(int number) { // Multiple Thread can access to the read methode

        // Start read Data Section
        synchronized (this) { // make sure no one have access to the Database
            this.readers++;
            System.out.println("Reader " + number + " starts reading.");
        }
        // End read Data Section

        // Start manipulating data ( multiple Thread can manipulate the same data at the same time ) in this section
        try {
            Thread.sleep((int) (Math.random() * DELAY));
        } catch (InterruptedException e) {
        }
        // end manipulating Data Section


        // Disconnect frome the Database
        synchronized (this) {
            System.out.println("Reader " + number + " stops reading.");
            this.readers--;
            if (this.readers == 0) { // if there is no reader no more -> notify all the Thread Waiting in queue
                this.notifyAll();
            }
        }
    }

    /**
     * Writes to this database.
     *
     * @param number Number of the writer.
     */
    public synchronized void write(int number) { // only one Thread ca access to the Write methode
        while (this.readers != 0) { // wait until there is no reader on the DB ( linked with line 33 )
            try {
                this.wait(); // making a Queue of Writer Thread waiting to access BD
            } catch (InterruptedException e) {
            }
        }

        // Start of Writing Section
        System.out.println("Writer " + number + " starts writing.");
        try {
            Thread.sleep((int) (Math.random() * DELAY));
        } catch (InterruptedException e) {
        }
        // End of Writing Section


        System.out.println("Writer " + number + " stops writing.");
        this.notifyAll(); // notify all the waiting Thread to resume there execution
    }
}