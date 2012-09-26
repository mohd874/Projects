/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 *
 * @author m.alhabshi
 */
public class PathFinding extends Applet implements Runnable {

    public void start() {
        enableEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK
                | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        new Thread(this).start();
    }
    int mouseX;
    int mouseY;
    

    public void run() {
        
        setSize(800, 600); // For AppletViewer, remove later.

        // Set up the graphics stuff, double-buffering.
        BufferedImage screen = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = screen.getGraphics();
        Graphics appletGraphics = getGraphics();

        // Some variables to use for the fps.
        int tick = 0, fps = 0, acc = 0;
        long lastTime = System.nanoTime();

        //oval parameters
        int x = getWidth() / 2;
        int y = getHeight() / 2;

        mouseX = x;
        mouseY = y;

        boolean[][] nodes = new boolean[getWidth()/2][getHeight()/2];
        for (boolean[] bs : nodes) {
            for (int i = 0; i < bs.length; i++) {
                bs[i]=false;
            }
        }
        boolean isOnTarget = true;

        int[] nextStep = new int[2];
        // Game loop.
        while (true) {
            long now = System.nanoTime();
            acc += now - lastTime;
            tick++;
            if (acc >= 1000000000L) {
                acc -= 1000000000L;
                fps = tick;
                tick = 0;
            }

            // Update
            // TODO add some update logic here.
            if(nextStep[0] != mouseX && nextStep[1] != mouseY)isOnTarget=false;
            if (!isOnTarget) {
                nextStep = searchForTarget(x, y);
                if(nextStep[0] == x && nextStep[1] == y)isOnTarget=true;
                else{
                    x = nextStep[0];
                    y = nextStep[1];
                }
            }
            

            lastTime = now;

            // Render
            g.setColor(Color.black);
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.white);
            g.drawString("FPS " + String.valueOf(fps), 20, 30);
            g.fillOval(x-50, y-50, 100, 100);

            // Draw the entire results on the screen.
            appletGraphics.drawImage(screen, 0, 0, null);

            do {
                Thread.yield();
            } while (System.nanoTime() - lastTime < 0);

            if (!isActive()) {
                return;
            }
        }
    }

    public void processEvent(AWTEvent e) {
        // Process the key event.
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
            MouseEvent m = (MouseEvent) e;
            mouseX = m.getX();
            mouseY = m.getY();
            System.out.println("mouseX: "+mouseX+" mouseY: "+mouseY);
        }
    }

    private int[] searchForTarget(int currentX, int currentY) {
        int closestX = currentX, closestY = currentY;
        int H = Math.abs(currentX - mouseX) + Math.abs(currentY - mouseY);
        int up = Math.abs(currentX - mouseX) + Math.abs((currentY - 1) - mouseY);
        int down = Math.abs(currentX - mouseX) + Math.abs((currentY + 1) - mouseY);
        int left = Math.abs((currentX - 1) - mouseX) + Math.abs(currentY - mouseY);
        int right = Math.abs((currentX + 1) - mouseX) + Math.abs(currentY - mouseY);


        if (up < H) {
            closestX = currentY - 1;
        } else if (down < H) {
            closestX = currentY + 1;
        } else if (left < H) {
            closestX = currentY - 1;
        } else if (right < H) {
            closestX = currentY + 1;
        }
        
        return new int[] {closestX, closestY};
    }
}