
/*************************************************************************
 *  Compilation:  javac -classpath .:jl1.0.jar MP3.java         (OS X)
 *                javac -classpath .;jl1.0.jar MP3.java         (Windows)
 *  Execution:    java -classpath .:jl1.0.jar MP3 filename.mp3  (OS X / Linux)
 *                java -classpath .;jl1.0.jar MP3 filename.mp3  (Windows)
 *
 *  Plays an MP3 file using the JLayer MP3 library.
 *
 *  Reference:  http://www.javazoom.net/javalayer/sources.html
 *
 *
 *  To execute, get the file jl1.0.jar from the website above or from
 *
 *      http://www.cs.princeton.edu/introcs/24inout/jl1.0.jar
 *
 *  and put it in your working directory with this file MP3.java.
 *
 *************************************************************************/
package sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import helperClasses.Player;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MP3 {
    private String filename;
    private File media;
    private Player player;
    private boolean stop;
    private boolean playingNow;
    
    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
//        this(new File(filename));
        this.filename = filename;
    }

    public MP3(File file) {
        this.media = file;
        stop = false;
        setPlayingNow(false);
    }
    
    public void close() {
        System.out.println("set stop to true");
        stop = true;
        setPlayingNow(false);
        if (player != null){
            player.close();
        }
    }
    
    // play the MP3 file to the sound card
    public void play() {
        System.out.println("set stop to false");
        stop = false;
        System.out.println("stop: "+stop);
        setPlayingNow(true);
        try {
//            System.out.println("File.exists(): "+media.exists());
            
//            FileInputStream fis     = new FileInputStream(media);
//            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(this.getClass().getResourceAsStream(filename));
        } catch (Exception e) {
            Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, e);
        }
        
        // run in new thread to play in background
        new Thread() {
            public void run() {
                System.out.println("play the sound file");
                try {
                    player.play();
                    while(!player.isComplete() && !stop){
                        Thread.currentThread().sleep(100);
                        System.err.println("inside the thread");
                    }
                    
                    if(!stop){
                        System.out.println("play again");
                        play();
                    }
                    
                } catch (Exception e) { 
                    Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }.start();
        
    }
    
    
    // test client
//    public static void main(String[] args) {
//        String filename = "C:\\netBeans_Projects\\sketch_app\\build\\classes\\playSoundFiles\\FF_VII_Victory_Fanfare.mp3";
//        MP3 mp3 = new MP3(filename);
//        mp3.play();
    
    // do whatever computation you like, while music plays
//        int N = 4000;
//        double sum = 0.0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                sum += Math.sin(i + j);
//            }
//        }
//        System.out.println(sum);
//
//        // when the computation is done, stop playing it
//        mp3.close();
//
//        // play from the beginning
//        mp3 = new MP3(filename);
//        mp3.play();
    
//    }
    
    public boolean isPlayingNow() {
        return playingNow;
    }
    
    public void setPlayingNow(boolean playingNow) {
        this.playingNow = playingNow;
    }
    
}

