package twozerofoureight;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Function;
import javax.swing.JOptionPane;
import static twozerofoureight.Direction.*;

public final class TwoZeroFourEight {

    private Window window;
    private Grid grid;
    private Dashboard dashboard;
    private boolean victorious;
    private boolean defeated;
    private final int gridSize;
    private final int levelGoal;
    private final Function increment;

    public static void main(String[] args) {
        TwoZeroFourEight tzfe = new TwoZeroFourEight();
        tzfe.start();
    }

    public TwoZeroFourEight() {
        gridSize = 4;
        levelGoal = 11;
        increment = ((x) -> {
            return (int)Math.pow(2, (int)x);
        });
    }

    private void start() {
        victorious = false;
        defeated = false;
        grid = new Grid(gridSize, levelGoal);
        grid.setup(increment);
        dashboard = new Dashboard();
        dashboard.setup();
        dashboard.getNewGameButton().addActionListener(e -> newGame());
        dashboard.getExitGameButton().addActionListener(e -> System.exit(0));
        window = new Window();
        window.setup(grid, dashboard, String.valueOf(increment.apply(levelGoal)));
        window.addKeyListener(keyAdapter);
        window.requestFocus();
    }

    private void newGame() {
        victorious = false;
        defeated = false;
        window.remove(grid);
        grid = new Grid(gridSize, levelGoal);
        grid.setup(increment);
        window.setGrid(grid);
        window.requestFocus();
    }
    
    private void checkIfGameOver() {
        if (!victorious && grid.checkIfVictory()) {
            victorious = true;
            JOptionPane.showMessageDialog(null, "You Win!");
        }
        if (!defeated && grid.checkIfFull()) {
            if (!grid.checkIfAllowed(UP)
                    && !grid.checkIfAllowed(DOWN)
                    && !grid.checkIfAllowed(LEFT)
                    && !grid.checkIfAllowed(RIGHT)) {
                defeated = true;
                JOptionPane.showMessageDialog(null, "You Lose");
            }
        }
    }

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            Direction direction = NONE;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    direction = UP;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    direction = DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    direction = LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    direction = RIGHT;
                    break;
                default:
                    break;
            }
            if (direction != NONE) {
                grid.handleInput(direction);
                checkIfGameOver();
            }
        }
    };
}