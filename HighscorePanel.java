package twozerofoureight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.*;

public class HighscorePanel extends JPanel {

    private final JPanel bestPanel, currentPanel;
    private final JLabel bestTextLabel, bestScoreLabel, currentTextLabel, currentScoreLabel;

    public HighscorePanel(String highscore) {
        bestPanel = new JPanel();
        currentPanel = new JPanel();
        bestTextLabel = new JLabel("Best:");
        bestScoreLabel = new JLabel(highscore);
        currentTextLabel = new JLabel("Score:");
        currentScoreLabel = new JLabel("0");
    }

    public void setup(Color backgroundColor) {
        setBackground(backgroundColor);
        setLayout(new GridLayout(1, 2, 20, 0));
        setPreferredSize(new Dimension(0, 50));
        setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        Arrays.asList(bestPanel, currentPanel).forEach(panel -> {
            panel.setLayout(new GridLayout(1, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            panel.setBackground(backgroundColor);
        });
        
        Arrays.asList(bestTextLabel, currentTextLabel, bestScoreLabel, currentScoreLabel).forEach(label -> {
            label.setFont(new Font("SansSerif", 1, 18));
            label.setForeground(Color.WHITE);
        });
        
        bestTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        currentTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        bestPanel.add(bestTextLabel);
        bestPanel.add(bestScoreLabel);
        currentPanel.add(currentTextLabel);
        currentPanel.add(currentScoreLabel);
        add(bestPanel);
        add(currentPanel);
    }
    
    public void setBestScoreLabel(String score) {
        bestScoreLabel.setText(score);
    }
    

    public JLabel getCurrentScoreLabel() {
        return currentScoreLabel;
    }
}
