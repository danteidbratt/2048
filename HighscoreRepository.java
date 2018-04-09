package twozerofoureight;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HighscoreRepository {

    File file;

    public HighscoreRepository(int gridSize) {
        file = new File("records/highscore" + gridSize + "x" + gridSize + ".txt");
    }

    public String getTopScore() {
        String topScore = "0";
        try {
            if (file.exists()) {
                BufferedReader buf = new BufferedReader(new FileReader(file));
                String temp;
                while ((temp = buf.readLine()) != null) {
                    if(Integer.parseInt(temp) > Integer.parseInt(topScore)) {
                        topScore = temp;
                    }
                }
                buf.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return topScore;
    }

    public void save(String highscore) {
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter buf = new BufferedWriter(new FileWriter(file, true));
            buf.write(highscore);
            buf.newLine();
            buf.close();
        } catch (IOException ex) {
            Logger.getLogger(HighscoreRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
