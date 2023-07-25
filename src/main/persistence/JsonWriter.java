package persistence;

import model.Library;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// A writer that writes JSON representation of library to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: Constructs writer to write to file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer
    // throws FileNotFoundException if file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes Library interpretation into JSON
    public void write(Library lib) {
        JSONObject json = lib.toJson();
        saveToFile(json.toString());
    }

    //MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
