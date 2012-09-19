/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonderboy;

import com.golden.gamedev.GameLoader;
import com.wonderboy.game.WBGame;
import java.awt.Dimension;

/**
 *
 * @author Mohamed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new WBGame(),new Dimension(640,480),false);
//        game.setup(new WBGame(),new Dimension(840, 640),false);
        game.start();
    }

}
