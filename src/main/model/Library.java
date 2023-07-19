package model;

import java.util.ArrayList;
import java.util.Random;


public class Library {
    String name;
    ArrayList<Song> songs;

    // EFFECTS: Creates new library with given name
    public Library(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // MODIFIES: This
    // EFFECTS: If song is not in library already, adds given song to library and produces true. Otherwise, false.
    public boolean addSong(String title, String artist, String length) {
        Song song = new Song(title, artist, length);
        if (this.songs.contains(song)) {
            return false;
        } else {
            this.songs.add(song);
            return true;
        }
    }


    // REQUIRES: songs must contain at least 1 element
    // EFFECTS: returns the song after given song in list. If song is last in list, return first song in list
    public Song next(Song song) {
        int i = this.songs.indexOf(song);
        if (i >= this.songs.size() - 1) {
            return this.songs.get(0);
        } else {
            return this.songs.get(i + 1);
        }
    }

    // REQUIRES: songs must contain at least 1 element
    // EFFECTS: returns the song before given song in list. If song is first in list, return last song in list
    public Song previous(Song song) {
        int i = this.songs.indexOf(song);
        int max = this.songs.size() - 1;
        if (i == 0) {
            return this.songs.get(max);
        } else {
            return this.songs.get(i - 1);
        }
    }

    // REQUIRES: songs must contain at least 1 element
    // EFFECTS: selects random song in library
    public Song shuffle() {
        Random rndm = new Random();
        return this.songs.get(rndm.nextInt(this.songs.size()));
    }

    // MODIFIES: this
    // EFFECTS: changes name of library to given string
    public void changeName(String name) {
        this.name = name;
    }

    // getters
    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
