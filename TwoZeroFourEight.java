package twozerofoureight;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class TwoZeroFourEight {
    
    private Window window;
    private Grid grid;
    private Dashboard dashboard;
    private boolean victorious;
    private boolean defeated;

    public static void main(String[] args) {
        TwoZeroFourEight tzfe = new TwoZeroFourEight();
        tzfe.newGame();
    }
    
    private void newGame() {
        victorious = false;
        defeated = false;
        grid = new Grid(4);
        grid.setup();
        dashboard = new Dashboard();
        dashboard.setup(actionHandler);
        window = new Window();
        window.setup(grid, dashboard);
        window.addKeyListener(keyAdapter);
        window.requestFocus();
    }
    
    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    grid.handleInput("up");
                    break;
                case KeyEvent.VK_DOWN:
                    grid.handleInput("down");
                    break;
                case KeyEvent.VK_LEFT:
                    grid.handleInput("left");
                    break;
                case KeyEvent.VK_RIGHT:
                    grid.handleInput("right");
                    break;
            }
            if(!victorious && grid.checkIfVictory()) {
                victorious = true;
                JOptionPane.showMessageDialog(null, "You Win!");
            }
            if(!defeated && grid.checkIfFull()) {
                if(!grid.checkIfAllowed("up") &&
                   !grid.checkIfAllowed("down") &&
                   !grid.checkIfAllowed("left") &&
                   !grid.checkIfAllowed("right")) {
                    defeated = true;
                    JOptionPane.showMessageDialog(null, "You Lose");
                }
            }
        }
    };

    ActionListener actionHandler = ((x) -> {
        if (x.getSource() == dashboard.getNewGameButton()) {
            newGame();
        }
        else if (x.getSource() == dashboard.getExitGameButton()) {
            System.exit(0);
        }
    });
}