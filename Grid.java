package twozerofoureight;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public final class Grid extends JPanel {

    private final Tile[][] tiles;
    private final int gridSize;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.tiles = new Tile[gridSize][gridSize];
    }

    public void setup() {
        setLayout(new GridLayout(4, 4, 10, 10));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(500, 500));
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].setup();
                add(tiles[i][j]);
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

    public void handleInput(String input) {
        if (checkIfAllowed(input)) {
            scanGrid(input);
        }
    }

    public boolean checkIfAllowed(String input) {
        int u = input.equals("up") ? 1 : 0;
        int d = input.equals("down") ? 1 : 0;
        int l = input.equals("left") ? 1 : 0;
        int r = input.equals("right") ? 1 : 0;
        
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

    public void scanGrid(String input) {
        boolean up = input.equals("up");
        boolean down = input.equals("down");
        boolean left = input.equals("left");
        Tile[] line = new Tile[gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                line[j] = tiles[(up || down
                        ? (up || left
                                ? j : gridSize - (j + 1)) : (up || left
                                ? i : gridSize - (i + 1)))][(up || down
                                ? (up || left
                                        ? i : gridSize - (i + 1)) : (up || left
                                        ? j : gridSize - (j + 1)))];
            }
            handleLine(line);
        }
        activateRandomTile();
    }

    private void handleLine(Tile[] line) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            if (!line[i].getText().equals("")) {
                values.add(line[i].getValue());
            }
            line[i].deactivate();
        }
        for (int i = 0; i < values.size() - 1; i++) {
            if ((int)values.get(i) == (int)values.get(i + 1) && values.get(i) > 0) {
                values.set(i, values.get(i) + 1);
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
    
    public boolean checkIfVictory() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if(tile.getValue() == 11) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkIfFull() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if(!tile.isActive()) {
                    return false;
                }
            }
        }
        return true;
    }
}
