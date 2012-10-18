package ch12;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyDrawPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = this.getWidth();
		int h = this.getHeight();
		
		for(int i=1; i<=w; i++){
			g.setColor(new Color(i, w-i, i));
			g.fillRect(i, i, w, h);
		}
		System.out.println("Done!");
	}
}
