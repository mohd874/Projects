/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;

/**
 *
 * @author m.alhabshi
 */
public interface WordsManager {
    
    public ArrayList<String> getAllSecretWords();
    
    public String getTheBoss(int bossLvl);
}
