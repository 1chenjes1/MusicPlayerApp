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
    public boolean addSong(String title, String artist, String length) {
        Song song = new Song(title, artist, length);
        if (this.songs.contains(song)) {
            return false;
        } else {
            this.songs.add(song);
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
