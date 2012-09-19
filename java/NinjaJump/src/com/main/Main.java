package com.main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import com.game.NinjaJump;

/*
 * To Run this game, make sure the following VM argument is set:
 * -Djava.library.path=<Path to lwgl.dll>
 * in my case
 * -Djava.library.path=C:\\Users\\mohammad\\Downloads\\slick
 */

public class Main {
	public static void main(String[] args) {
		
		String path = new File("nin_libz").getAbsolutePath();
		Log.info(path);
		
		System.setProperty("java.library.path", path);
		
		AppGameContainer app;
		
		try {
			app = new AppGameContainer(new NinjaJump("Ninja Jump"));
			app.setDisplayMode(300, 600, false);
			app.setMaximumLogicUpdateInterval(100);
			app.setTargetFrameRate(80);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
//			String msg = "An Error has occured preventing the game from starting. "+e.getMessage();
//			new JOptionPane();
//			JOptionPane.showMessageDialog(null, msg , "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
