package persistence;

import model.Library;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads library from the JSON data stored
// Code was taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from given source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads and returns library from saved file
    // throws IOException if there is error reading data from saved file
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads and returns source file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Copy library from JSONObject and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Library lib = new Library(name);
        if (jsonObject.get("currentsong") instanceof String) {
            lib.setCurrentSong(null);
        } else {
            JSONObject currentsong = (JSONObject) jsonObject.get("currentsong");
            addCurrentSong(lib, currentsong);
        }
        addSongs(lib, jsonObject);
        return lib;
    }

    // EFFECTS: Adds songs from JSONObject to library
    private void addSongs(Library lib, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(lib, nextSong);
        }
    }

    // EFFECTS: Adds song from JSONObject to library
    private void addSong(Library lib, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        String duration = jsonObject.getString("duration");
        lib.addSong(title, artist, duration);
    }

    //EFFECTS: sets song to currentlyplayed in library
    private void addCurrentSong(Library lib, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        String duration = jsonObject.getString("duration");
        Song song = new Song(title, artist, duration);
        lib.setCurrentSong(song);
    }
}
