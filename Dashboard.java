package twozerofoureight;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Dashboard extends JPanel{
    
    private final JButton newGameButton;
    private final JButton exitGameButton;

    public Dashboard() {
        newGameButton = new JButton("New Game");
        exitGameButton = new JButton("Exit");
    }
    
    public void setup(ActionListener al) {
        setLayout(new GridLayout(1, 2, 30, 0));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        newGameButton.setFont(new Font("SansSerif", 1, 30));
        exitGameButton.setFont(new Font("SansSerif", 1, 30));
        newGameButton.addActionListener(al);
        exitGameButton.addActionListener(al);
        add(newGameButton);
        add(exitGameButton);
        setPreferredSize(new Dimension(0, 100));
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getExitGameButton() {
        return exitGameButton;
    }
    
}