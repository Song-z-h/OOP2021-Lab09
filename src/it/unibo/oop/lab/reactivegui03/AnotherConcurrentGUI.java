package it.unibo.oop.lab.reactivegui03;

import it.unibo.oop.lab.reactivegui02.ConcurrentGUI;

/**
 * A simple GUI app.
 *
 */
public class AnotherConcurrentGUI extends ConcurrentGUI {
    /**
     * construction.
     */
    public AnotherConcurrentGUI() {
        final SleeperAgent stopter = new SleeperAgent();
        new Thread(stopter).start();
    }

    private class SleeperAgent implements Runnable {
        private static final int STOP = 3000;

        @Override
        public void run() {
            try {
                Thread.sleep(STOP);
                System.out.println("it's been 3 s");
                stopAgent();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
