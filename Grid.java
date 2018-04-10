package twozerofoureight;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.swing.*;
import static twozerofoureight.Direction.*;

public final class Grid extends JPanel {

    private final Tile[][] tiles;
    private final int gridSize;
    private final int levelGoal;
    private boolean victorious;
    private boolean defeated;
    private final JLabel currentScoreLabel;
    private final JLabel topScoreLabel;
    private final HighscoreRepository repository;
    private int currentScore;
    private final Function<Integer, Integer> increment;

    public Grid(int gridSize, int levelGoal, Function increment, JLabel currentScoreLabel, JLabel topScoreLabel) {
        this.gridSize = gridSize;
        this.tiles = new Tile[gridSize][gridSize];
        this.levelGoal = levelGoal;
        this.increment = increment;
        this.currentScoreLabel = currentScoreLabel;
        this.topScoreLabel = topScoreLabel;
        repository = new HighscoreRepository(gridSize);
        currentScore = 0;
    }

    public void setup(Color backgroundColor) {
        topScoreLabel.setText(repository.getTopScore());
        currentScoreLabel.setText("0");
        victorious = false;
        defeated = false;
        setLayout(new GridLayout(gridSize, gridSize, 10, 10));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        setPreferredSize(new Dimension(600, 600));
        for (Tile[] tile : tiles) {
            for (int j = 0; j < tile.length; j++) {
                tile[j] = new Tile(increment);
                tile[j].setup();
                add(tile[j]);
            }
        }
        activateRandomTile();
    }

    public void activateRandomTile() {
        List<Tile> inactiveTiles = new ArrayList<>();
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (!tile.isActive()) {
                    inactiveTiles.add(tile);
                }
            }
        }
        if (inactiveTiles.size() > 0) {
            inactiveTiles.get((int) Math.floor(Math.random() * inactiveTiles.size())).activate(1);
        }
    }

    public boolean handleInput(Direction direction) {
        if (isAllowed(direction)) {
            processGrid(direction);
            currentScoreLabel.setText(String.valueOf(currentScore));
            checkIfGameOver();
            return true;
        }
        return false;
    }

    public boolean isAllowed(Direction direction) {
        int u = direction == UP ? 1 : 0;
        int d = direction == DOWN ? 1 : 0;
        int l = direction == LEFT ? 1 : 0;
        int r = direction == RIGHT ? 1 : 0;

        for (int i = (0 + u); i < gridSize - (0 + d); i++) {
            for (int j = (0 + l); j < gridSize - (0 + r); j++) {
                if (tiles[i][j].isActive() && !tiles[i - u + d][j - l + r].isActive()) {
                    return true;
                }
                if (!tiles[i][j].getText().equals("") && tiles[i][j].getValue() == tiles[i - u + d][j - l + r].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void processGrid(Direction direction) {
        boolean up = direction == UP;
        boolean down = direction == DOWN;
        boolean left = direction == LEFT;
        Tile[] line = new Tile[gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                line[j] = tiles[(up || down ? (up || left ? j : gridSize - (j + 1)) : (up || left ? i : gridSize - (i + 1)))][(up || down ? (up || left ? i : gridSize - (i + 1)) : (up || left ? j : gridSize - (j + 1)))];
            }
            processLine(line);
        }
        activateRandomTile();
        revalidate();
        repaint();
    }

    private void processLine(Tile[] line) {
        List<Integer> values = new ArrayList<>();
        for (Tile tile : line) {
            if (tile.isActive()) {
                values.add(tile.getValue());
            }
            tile.deactivate();
        }
        for (int i = 0; i < values.size() - 1; i++) {
            if ((int) values.get(i) == (int) values.get(i + 1) && values.get(i) > 0) {
                values.set(i, values.get(i) + 1);
                currentScore += increment.apply(values.get(i));
                values.remove(i + 1);
            }
        }
        int counter = 0;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) != 0) {
                line[counter++].activate(values.get(i));
            }
        }
    }

    public void checkIfGameOver() {
        if (checkIfVictory()) {
            JOptionPane.showMessageDialog(null, "You Win!");
        }
        if (checkIfDefeat()) {
            String topScore = repository.getTopScore();
            if (currentScore > Integer.parseInt(topScore)) {
                JOptionPane.showMessageDialog(null, "New Highscore!");
                topScoreLabel.setText(String.valueOf(currentScore));
            } else {
                JOptionPane.showMessageDialog(null, "You Lose");
            }
            repository.save(String.valueOf(currentScore));
        }
    }

    private boolean checkIfVictory() {
        if (!victorious) {
            for (Tile[] row : tiles) {
                for (Tile tile : row) {
                    if (tile.getValue() == levelGoal) {
                        victorious = true;
                        Bot.killBot();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkIfFull() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (!tile.isActive()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIfDefeat() {
        if (!defeated
                && checkIfFull()
                && !isAllowed(UP)
                && !isAllowed(DOWN)
                && !isAllowed(LEFT)
                && !isAllowed(RIGHT)) {
            Bot.killBot();
            defeated = true;
            return true;
        }
        return false;
    }

    public void setVictorious(boolean victorious) {
        this.victorious = victorious;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getNumberOfFilledRows() {
        for (int i = 0; i < tiles.length; i++) {
            boolean full = true;
            for (int j = 0; j < tiles[i].length - 1; j++) {
                if (!tiles[i][j].isActive() || tiles[i][j].getValue() == tiles[i][j+1].getValue()) {
                    full = false;
                    break;
                }
            }
            if (!full) {
                return i;
            }
        }
        return 3;
    }

}
