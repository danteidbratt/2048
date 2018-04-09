package twozerofoureight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*;

public class HighscorePanel extends JPanel {

    private final JPanel topPanel, currentPanel;
    private final JLabel topTextLabel, topScoreLabel, currentTextLabel, currentScoreLabel;

    public HighscorePanel() {
        topPanel = new JPanel();
        currentPanel = new JPanel();
        topTextLabel = new JLabel("Best:");
        topScoreLabel = new JLabel();
        currentTextLabel = new JLabel("Score:");
        currentScoreLabel = new JLabel();
    }

    public void setup(Color backgroundColor) {
        setBackground(backgroundColor);
        setLayout(new GridLayout(1, 2, 20, 0));
        setPreferredSize(new Dimension(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        Arrays.asList(topPanel, currentPanel).forEach(panel -> {
            panel.setLayout(new GridLayout(1, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            panel.setBackground(backgroundColor);
        });
        
        Arrays.asList(topTextLabel, currentTextLabel, topScoreLabel, currentScoreLabel).forEach(label -> {
            label.setFont(new Font("SansSerif", 1, 18));
            label.setForeground(Color.WHITE);
        });
        
        topTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        currentTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        topPanel.add(topTextLabel);
        topPanel.add(topScoreLabel);
        currentPanel.add(currentTextLabel);
        currentPanel.add(currentScoreLabel);
        add(topPanel);
        add(currentPanel);
    }
    
    public JLabel getTopScoreLabel() {
        return topScoreLabel;
    }
    

    public JLabel getCurrentScoreLabel() {
        return currentScoreLabel;
    }
}
