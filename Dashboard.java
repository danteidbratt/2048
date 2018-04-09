package twozerofoureight;

import java.awt.*;
import javax.swing.*;

public final class Dashboard extends JPanel{
    
    private final JButton newGameButton, exitGameButton, autoButton;

    public Dashboard() {
        newGameButton = new JButton("New Game");
        exitGameButton = new JButton("Exit");
        autoButton = new JButton("Auto");
    }
    
    public void setup(Color backgroundColor) {
        setLayout(new GridLayout(1, 2, 30, 0));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        newGameButton.setFont(new Font("SansSerif", 1, 25));
        exitGameButton.setFont(new Font("SansSerif", 1, 25));
        autoButton.setFont(new Font("SansSerif", 1, 25));
        add(newGameButton);
        add(autoButton);
        add(exitGameButton);
        setPreferredSize(new Dimension(0, 80));
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitGameButton() {
        return exitGameButton;
    }

    public JButton getAutoButton() {
        return autoButton;
    }
    
}