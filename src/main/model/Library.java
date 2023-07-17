package model;

import java.util.ArrayList;

public class Library {
    String name;
    ArrayList<Song> songs;

    // EFFECTS: Creates new library with given name
    public Library(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    // MODIFIES: This
    // EFFECTS: Adds given song to library
    public void addSong(Song song) {
        //stub
    }
}
