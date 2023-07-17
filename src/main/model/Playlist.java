package model;

import java.util.ArrayList;
import java.util.Random;


public class Playlist {
    String name;
    ArrayList<Song> songs;

    //EFFECTS: creates new playlist with given playlist name
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
    }

    // MODIFIES: this
    // EFFECTS: Produces true if given song is added to playlist
    //         produces false if given song already exists in playlist
    public Boolean addSong(Song song) {
        return false; //stub
    }


    //getters
    public String getPlaylistName() {
        return this.name;
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

}
