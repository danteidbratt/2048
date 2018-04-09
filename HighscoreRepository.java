package twozerofoureight;

import java.io.FileNotFoundException;
import java.util.*;

public class HighscoreRepository {
    
    private Formatter formatter;

    public void save(String highscore) {
        try {
            formatter = new Formatter("records/highscores.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        formatter.format("%s", highscore);
        formatter.close();
    }
}