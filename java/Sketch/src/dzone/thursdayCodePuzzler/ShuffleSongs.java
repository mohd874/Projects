/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dzone.thursdayCodePuzzler;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author m.alhabshi
 */
public class ShuffleSongs {

    static List<Song> songsList;

    public static void main(String[] args) {
        //init list
        initSongsList();
        System.out.println("Before Shuffle");
        printSongsList(songsList);
        
        //Solution # 1
        printSongsList(shuffle1(songsList));
        
        initSongsList();
        //Solution # 2
        printSongsList(shuffle2(songsList));
    }

    private static void initSongsList() {
        songsList = new LinkedList<Song>();
        songsList.add(new Song("Moon Light", "Nature", "AL"));
        songsList.add(new Song("Go Next", "Future", "B. J"));
        songsList.add(new Song("Realllllly", "K", "CCC"));
        songsList.add(new Song("Fear", "Hell G.", "SD"));
        songsList.add(new Song("My Dear", "Future", "B. J"));
        songsList.add(new Song("To the School", "K", "Mike"));
        songsList.add(new Song("My name is", "Future", "Khan"));
        songsList.add(new Song("How want some", "Hell G.", "S.Fire"));
        songsList.add(new Song("My Lord", "K", "Ali"));
    }
    
    private static void printSongsList(List<Song> list){
        for (int i = 0; i < list.size(); i++) {
            Song song = list.get(i);
            System.out.printf("# %d - Title: %13s | Album: %10s | Artist: %10s\n", i+1, song.title, song.album, song.artist);
        }
        
        System.out.println("--------------------------------------------------------");
    }
    
    private static List shuffle1(List list){
        List res = new LinkedList();
        int size = list.size();
        int rand = 0;
        Object[] temp = list.toArray();
        int count = 0;;
        
        while(count != size){
            rand = (int)(Math.random()*list.size());
            if(!res.contains(temp[rand])){
                res.add(temp[rand]);
                count++;
            }
        }
        
        return res;
    }
    
    private static List shuffle2(List list){
        if(list.size() == 1){
            return list;
        }else{
            int rand = (int)(Math.random()*list.size());
            Object o = list.get(rand);
            list.remove(rand);
            list = shuffle2(list);
            list.add(o);
            return list;
        }
    }
    
    private static class Song {
        private String title;
        private String album;
        private String artist;

        public Song() {
        }

        public Song(String title, String album, String artist) {
            this.title = title;
            this.album = album;
            this.artist = artist;
        }       
    }
}
