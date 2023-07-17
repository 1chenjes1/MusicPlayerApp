package ui;

import model.Library;
import model.Song;
import model.Playlist;

import java.util.Random;

public class MusicPlayer {

    // EFFECTS: runs the music player application
    public MusicPlayer() {
        runMusicPlayer();
    }

    public void runMusicPlayer() {
        setup();
    }

    public void setup() {
        Song s1 = new Song("Virtual Insanity", "Jamiroquai", "3:47");
        Song s2 = new Song("Feel Good Inc.", "Gorrillaz", "3:42");
        Song s3 = new Song("spinning", "thecember", "3:39");
        Song s4 = new Song("My Girl", "The Temptations", "2:45");
        Song s5 = new Song("Song 2","Blur", "2:01");

        Library defaultlib = new Library("Default Library");
        defaultlib.addSong(s1);
        defaultlib.addSong(s2);
        defaultlib.addSong(s3);
        defaultlib.addSong(s4);
        defaultlib.addSong(s5);

        Library userlib = new Library("Your Library");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nCurrently playing:");
        System.out.println("\t1 -> pause");
        System.out.println("\t2 -> skip");
        System.out.println("\t3-> repeat");
        System.out.println("\tp -> view playlists");
        System.out.println("\tl -> view libraries");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: picks and plays random song in given Playlist
    private void playlistShuffle(Playlist playlist) {
        Random rndm = new Random();
        Song song = playlist.getSongs().get(rndm.nextInt());
        System.out.println("Currently playing" + song.getTitle() + "," + song.getArtist() + "," + song.getLength());
    }
}
