package twozerofoureight;

import java.awt.*;
import javax.swing.*;

public final class Dashboard extends JPanel{
    
    private final JButton newGameButton;
    private final JButton exitGameButton;

    public Dashboard() {
        newGameButton = new JButton("New Game");
        exitGameButton = new JButton("Exit");
    }
    
    public void setup() {
        setLayout(new GridLayout(1, 2, 30, 0));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        newGameButton.setFont(new Font("SansSerif", 1, 30));
        exitGameButton.setFont(new Font("SansSerif", 1, 30));
        add(newGameButton);
        add(exitGameButton);
        setPreferredSize(new Dimension(0, 80));
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitGameButton() {
        return exitGameButton;
    }
    
}