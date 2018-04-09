package twozerofoureight;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Function;
import static twozerofoureight.Direction.*;

public final class TwoZeroFourEight {

    private Window window;
    private Grid grid;
    private Dashboard dashboard;
    private HighscorePanel highscorePanel;
    private final int gridSize;
    private final int levelGoal;
    private final Function<Integer, Integer> increment;
    private Bot bot;
    private final Color backgroundColor;

    public static void main(String[] args) {
        TwoZeroFourEight tzfe = new TwoZeroFourEight();
        tzfe.start();
    }

    public TwoZeroFourEight() {
        gridSize = 2;
        levelGoal = 11;
        increment = (x -> {
            return (int) Math.pow(2, x);
        });
        backgroundColor = new Color(30, 30, 30);
    }

    private void start() {
        highscorePanel = new HighscorePanel();
        highscorePanel.setup(backgroundColor);
        grid = new Grid(gridSize, levelGoal, increment, highscorePanel.getCurrentScoreLabel(), highscorePanel.getTopScoreLabel());
        grid.setup(backgroundColor);
        dashboard = new Dashboard();
        dashboard.setup(backgroundColor);
        dashboard.getNewGameButton().addActionListener(e -> newGame());
        dashboard.getExitGameButton().addActionListener(e -> System.exit(0));
        dashboard.getAutoButton().addActionListener(e -> {
            if (dashboard.getAutoButton().getText().equals("Auto") && !grid.isDefeated()) {
                bot = new Bot(grid, dashboard.getAutoButton());
                bot.start();
            } else {
                bot.killBot();
            }
            window.requestFocus();
        });
        window = new Window();
        window.setup(grid, dashboard, highscorePanel, String.valueOf(increment.apply(levelGoal)));
        window.addKeyListener(keyAdapter);
        window.requestFocus();
        bot = new Bot();
    }

    private void newGame() {
        window.remove(grid);
        grid = new Grid(gridSize, levelGoal, increment, highscorePanel.getCurrentScoreLabel(), highscorePanel.getTopScoreLabel());
        grid.setup(backgroundColor);
        window.setGrid(grid);
        window.requestFocus();
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
                case KeyEvent.VK_SPACE:
                    dashboard.getAutoButton().doClick();
                default:
                    break;
            }
            if (direction != NONE) {
                grid.handleInput(direction);
            }
        }
    };
}
