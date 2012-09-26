package bitmapFontGerenation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuffer chars = new StringBuffer();
		int i;
		char ch;
		for (i = 0x22; i <= 0xffff; i++) {
			ch = (char) i;
			if (i % 0x10 == 0){
//				System.out.println();
//			chars.append("\n");
			}
			if (i % 0x100 == 0){
//				System.out.println("****" + Integer.toHexString(i / 0x100)
//						+ "****");
//			System.out.print(ch);
			}
			chars.append(String.valueOf(ch));
		}
//		System.out.println();

		Image img = new BufferedImage(chars.toString().toCharArray().length, 24, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) img.getGraphics();
		Font font = new Font("CourierNew", Font.PLAIN, 18);
		String text = "Hello world!";
		text = chars.toString();
//		System.out.println(text);
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, img.getWidth(null), img.getHeight(null));
		g2.setColor(Color.black);
		g2.setFont(font);
		g2.drawString(text, 0, 18);
//		g2.drawString("TEXT ÃåáÇÇ", 10, 10);

		File outputfile = new File("imagewithtext.png");
		ImageIO.write((RenderedImage) img, "png", outputfile);
		System.out.println("Finished");
	}
}
