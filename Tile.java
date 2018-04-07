package twozerofoureight;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Function;
import javax.swing.*;

public final class Tile extends JLabel {
    
    private boolean active;
    private int level;
    private final Function increment;

    public Tile(Function increment) {
        level = 0;
        this.increment = increment;
    }
    
    public void setup() {
        setFont(new Font("SansSerif", 1, 35));
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        setHorizontalAlignment(0);
        setBackground(new Color(40, 40, 40));
        setOpaque(true);
        setForeground(Color.WHITE);
        setText("");
    }

    public boolean isActive() {
        return active;
    }

    public int getValue() {
        return level;
    }

    public void activate(int level) {
        this.level = level;
        setText((int)increment.apply(level));
        setBackground(new Color(0, 50 + (level * 10),50 + (level * 10)));
        active = true;
    }
    
    public void deactivate() {
        level = 0;
        setText("");
        setBackground(new Color(40, 40, 40));
        active = false;
    }
    
    public void setText(int value) {
        setText(String.valueOf(value));
    }

}