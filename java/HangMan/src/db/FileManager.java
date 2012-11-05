/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.alhabshi
 */
public class FileManager implements WordsManager, ScoreManager {

    File scoresFile = new File("hangMan");

    @Override
    public ArrayList<String> getAllSecretWords() {
        ArrayList<String> words = new ArrayList<String>();

        words.add("java");
        words.add("architecture");
        words.add("computer");
        words.add("anagram");
        words.add("hangman");
        words.add("ant");
        words.add("construction");

        return words;
    }

    @Override
    public String getTheBoss(int bossLvl) {
        return "Supercalifragilisticexpialidocious";
    }

    @Override
    public boolean isNewHighScore(int newScore) {
        String[][] topTen = getTopTenScores();

        for (int i = 0; i < topTen.length; i++) {
            int score = Integer.valueOf(topTen[i][1]);
            if (score < newScore) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addNewHighScore(String playerName, int newScore) {
        try {
            String[][] topTen = getTopTenScores();
            String[][] temp = topTen.clone();

            boolean indexFound = false;

            for (int i = 0; i < topTen.length; i++) {
                int score = Integer.valueOf(topTen[i][1]);
                if (score < newScore && !indexFound) {
                    temp[i][0] = playerName;
                    temp[i][1] = String.valueOf(newScore);
                    indexFound = true;
                    i++;
                } else if (indexFound) {
                    temp[i][0] = topTen[i - 1][0];
                    temp[i][1] = topTen[i - 1][1];
                }
            }
            
            writeTopTen(temp);
            
            return indexFound;
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public String[][] getTopTenScores() {
        String[][] scores = new String[10][2];

        if (!scoresFile.exists()) {
            try {
                createScoresFile();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Scanner scan = null;
        try {
            scan = new Scanner(scoresFile);
            scan.useDelimiter("::");
            int count = 0;
            while (scan.hasNext() && count <= 10) {
                scores[count][0] = scan.next();
                scores[count][1] = scan.next();
                count++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            scan.close();
        }

        return scores;
    }

    private void createScoresFile(String newScores) throws IOException {
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(scoresFile), "utf-8");
            out = new FileWriter(scoresFile);
            out.write(newScores);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    private void createScoresFile() throws IOException {
        String newScores = "abc::1000::"
                + "xyz::900::"
                + "KOKO::800::"
                + "momo::700::"
                + "lolo::600::"
                + "soso::500::"
                + "iack::400::"
                + "popo::300::"
                + "k999::200::"
                + "J::100";
        createScoresFile(newScores);
    }

    private void writeTopTen(String[][] temp) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String[] scores : temp) {
            sb.append(scores[0]+"::"+scores[1]+"::");
        }
        createScoresFile(sb.toString());
    }
}
