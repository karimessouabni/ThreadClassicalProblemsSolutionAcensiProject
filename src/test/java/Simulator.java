public class Simulator {
    /**
     * Creates the specified number of readers and writers and starts them.
     */
    public static void main(String[] args) {

        final int READERS = 2;
        final int WRITERS = 4;
        Database database = new Database();
        for (int i = 0; i < READERS; i++) {
            new Reader(database).start();
        }
        for (int i = 0; i < WRITERS; i++) {
            new Writer(database).start();
        }
    }
}