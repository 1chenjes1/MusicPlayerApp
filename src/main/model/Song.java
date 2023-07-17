package model;

public class Song {
    String title;
    String artist;
    String duration;

    // REQUIRES: length must be given in format minutes:seconds where seconds range 00 - 60
    // EFFECTS: creates song with given title, artist, length
    public Song(String title, String artist, String length) {
        this.title = title;
        this.artist = artist;
        this.duration = length;
    }

    //getters
    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getLength() {
        return this.duration;
    }
}
