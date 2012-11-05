/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author m.alhabshi
 */
public interface ScoreManager {
    
    public boolean isNewHighScore(int newScore);
    
    public boolean addNewHighScore(String playerName, int newScore);
    
    public String[][] getTopTenScores();
}
