package twozerofoureight;

import javax.swing.*;
import static twozerofoureight.Direction.*;

public class Bot extends Thread implements Runnable {

    private static boolean running;
    private Grid grid;
    private JButton trigger;

    public Bot(Grid grid, JButton trigger) {
        running = false;
        this.grid = grid;
        this.trigger = trigger;
    }

    public Bot() {
        running = false;
    }

    @Override
    public void run() {
        trigger.setText("Stop");
        running = true;
        try {
            while (running) {
                if (running) {
                    if (grid.handleInput(UP)) {
                        Thread.sleep(100);
                    }
                } else {
                    break;
                }
                if (running) {
                    if (grid.handleInput(LEFT)) {
                        Thread.sleep(100);
                    }
                } else {
                    break;
                }
                if (running) {
                    if (!grid.checkIfAllowed(UP) && !grid.checkIfAllowed(LEFT)) {
                        if (grid.handleInput(DOWN) || grid.handleInput(RIGHT)) {
                            Thread.sleep(100);
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        trigger.setText("Auto");
    }

    public static void killBot() {
        running = false;
    }

}
