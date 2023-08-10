package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

//Represents a library containing arbitrary amount of songs. Includes library name, list of songs and the song that is
//playing currently
public class Library implements Writable {
    private String name;
    private ArrayList<Song> songs;
    private Song currentSong;

    // EFFECTS: Creates new library with given name and contains no songs
    public Library(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // MODIFIES: This
    // EFFECTS: If song is not in library already, adds given song to library and produces true. Otherwise, false.
    public boolean addSong(String title, String artist, String length) {
        Song song = new Song(title, artist, length);
        if (this.containSong(song)) {
            return false;
        } else {
            this.songs.add(song);
            EventLog.getInstance().logEvent(new Event("Added Song"));
            return true;
        }
    }

    // EFFECTS: returns true if song with same name, artist, duration is in library songs
    public boolean containSong(Song song) {
        boolean result = false;
        for (Song s : this.songs) {
            if (songsEqual(s, song)) {
                result = true;
                break;
            }
        }
        return result;
    }

    //MODIFIES: this
    //EFFECTS: sets currentSong to first song in library
    public Song play() {
        EventLog.getInstance().logEvent(new Event("Playing first song in library"));
        this.currentSong = this.songs.get(0);
        return this.songs.get(0);
    }


    // REQUIRES: songs must contain at least 1 element
    // EFFECTS: returns the song after given song in list. If song is last in list, return first song in list
    public Song next(Song song) {
        EventLog.getInstance().logEvent(new Event("Playing next track"));
        int i = indexOfSong(song);
        if (i >= this.songs.size() - 1) {
            this.currentSong = this.songs.get(0);
            return this.songs.get(0);
        } else {
            this.currentSong = this.songs.get(i + 1);
            return this.songs.get(i + 1);
        }
    }

    // REQUIRES: Song is in library songs
    // EFFECTS: returns index of Song in library songs
    public int indexOfSong(Song song) {
        int result = 0;
        for (Song s : this.songs) {
            if (songsEqual(s, song)) {
                break;
            } else {
                result++;
            }
        }
        return result;
    }

    // REQUIRES: songs must contain at least 1 element
    // EFFECTS: returns the song before given song in list. If song is first in list, return last song in list
    public Song previous(Song song) {
        int i = indexOfSong(song);
        int max = this.songs.size() - 1;
        EventLog.getInstance().logEvent(new Event("Playing previous track"));
        if (i == 0) {
            this.currentSong = this.songs.get(max);
            return this.songs.get(max);
        } else {
            this.currentSong = this.songs.get(i - 1);
            return this.songs.get(i - 1);
        }
    }

    // REQUIRES: songs must contain at least 1 element
    // EFFECTS: selects random song in library
    public Song shuffle() {
        Random rndm = new Random();
        Song song = this.songs.get(rndm.nextInt(this.songs.size()));
        Song s = this.getCurrentSong();
        while (songsEqual(s, song)) {
            Random rndm2 = new Random();
            song = this.songs.get(rndm2.nextInt(this.songs.size()));
        }
        this.currentSong = song;
        EventLog.getInstance().logEvent(new Event("Library shuffled"));
        return song;

    }

    // EFFECTS: produce true if both songs have same title, artist, duration. False otherwise
    public Boolean songsEqual(Song s, Song song) {
        return s.getArtist().equals(song.getArtist())
                && s.getTitle().equals(song.getTitle())
                && s.getLength().equals(song.getLength());
    }

    // MODIFIES: this
    // EFFECTS: changes name of library to given string
    public void changeName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Changed library name"));
    }

    // MODIFIES: this
    // EFFECTS: sets currentsong to given song
    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    // getters
    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    // EFFECTS: Converts library to JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());

        if (currentSong == null) {
            json.put("currentsong", "no");
        } else {
            json.put("currentsong", currentSong.toJson());
        }

        return json;
    }

    // EFFECTS: Converts list of songs into JSONObject (JSONArray)
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songs) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
