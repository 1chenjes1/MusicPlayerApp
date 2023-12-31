package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a song with title, artist name, and duration in form minutes:seconds
public class Song implements Writable {
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

    // EFFECTS: returns a string representation of song
    @Override
    public String toString() {
        return "Song{"
                + "title='" + title + '\''
                + ", artist='" + artist + '\''
                + ", duration='" + duration + '\''
                + '}';
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


    // Converts song into JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title",title);
        json.put("artist", artist);
        json.put("duration", duration);
        return json;
    }
}
