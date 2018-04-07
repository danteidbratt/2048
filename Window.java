package twozerofoureight;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    
    public void setup(Grid grid, Dashboard dashboard) {
        setTitle("2048");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(grid, BorderLayout.CENTER);
        add(dashboard, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
        setVisible(true);
        setFocusable(true);
    }
    
    public void setGrid(Grid newGrid) {
        add(newGrid, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}