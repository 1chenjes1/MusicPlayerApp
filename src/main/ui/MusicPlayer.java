package ui;

import model.Library;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// The MusicPlayer application with console interface
public class MusicPlayer {

    private Library userLib;
    private boolean play;
    private boolean pause;
    private Song currentlyPlaying;
    private Scanner sc;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/musicplayer.json";

    // EFFECTS: runs the music player application
    public MusicPlayer() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMusicPlayer();
    }

    // MODIFIES: this
    // EFFECTS: interprets user inputs
    public void runMusicPlayer() {
        sc = new Scanner(System.in);
        boolean quit = false;

        userLib = new Library("Your Library");

        play = false;
        pause = false;
        currentlyPlaying = null;
        String command = null;

        while (!quit) {
            mainMenu();
            command = sc.next();
            command = command.toLowerCase();

            if (command.equals("11")) {
                quit = true;
            } else {
                mainCommand(command);
            }
        }

        System.out.println("Music Player shutting down...");

    }

    // EFFECTS: displays menu of beginning options to user
    private void mainMenu() {
        if (currentlyPlaying == null) {
            System.out.println("Welcome to Music Player.");
        } else {
            System.out.println("Currently playing:" + currentlyPlaying);
            System.out.println("Paused?:" + " " + pause);
        }
        System.out.println("1 -> Play");
        System.out.println("2 -> Pause");
        System.out.println("3 -> Next");
        System.out.println("4 -> Previous");
        System.out.println("5 -> Shuffle");
        System.out.println("6 -> Add song to library");
        System.out.println("7 -> View library:" + " " + userLib.getName());
        System.out.println("8 -> Rename library");
        System.out.println("9 -> Save " + userLib.getName() + " to file");
        System.out.println("10 -> load library from file");
        System.out.println("11 -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes main commands
    private void mainCommand(String command) {
        if (command.equals("1")) {
            doPlay();
        } else if (command.equals("2")) {
            doPause();
        } else if (command.equals("3")) {
            doNext();
        } else if (command.equals("4")) {
            doPrevious();
        } else if (command.equals("5")) {
            doShuffle();
        } else if (command.equals("6")) {
            add();
        } else if (command.equals("7")) {
            runLibrary();
        } else if (command.equals("8")) {
            renameLibrary();
        } else if (command.equals("9")) {
            saveLibrary();
        } else if (command.equals("10")) {
            loadLibrary();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: If there are no songs in library, display user message
    //          If no song is currently playing, play most recently added song in lib
    //          Otherwise, set play true and set false
    private void doPlay() {
        if (userLib.getSongs().size() == 0) {
            System.out.println("There are currently no songs in your library.");
        } else if (!this.play && !this.pause) {
            this.play = true;
            this.currentlyPlaying = userLib.play();
        } else {
            this.play = true;
            this.pause = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: If no song is currently playing, display user message
    //          Otherwise set pause true and play false
    private void doPause() {
        if (currentlyPlaying == null) {
            System.out.println("You are not currently playing a song.");
        } else if (!this.pause) {
            this.pause = true;
            this.play = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays next song in list as long as there is currently a song playing
    //          and there is at least 1 song in library
    private void doNext() {
        if (currentlyPlaying == null) {
            System.out.println("You are not currently playing a song.");
        } else if (userLib.getSongs().size() == 0) {
            System.out.println("There are currently no songs in your library.");
        } else {
            currentlyPlaying = userLib.next(currentlyPlaying);
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays previous song in list as long as there is currently a song playing
    //          and there is at least 1 song in library
    private void doPrevious() {
        if (currentlyPlaying == null) {
            System.out.println("You are not currently playing a song.");
        } else if (userLib.getSongs().size() == 0) {
            System.out.println("There are currently no songs in your library.");
        } else {
            currentlyPlaying = userLib.previous(currentlyPlaying);
        }
    }

    // MODIFIES: this
    //EFFECTS: picks and plays random song in library as long as there is at least 1 song in library
    private void doShuffle() {
        if (userLib.getSongs().size() == 0) {
            System.out.println("There are currently no songs in your library.");
        } else {
            currentlyPlaying = userLib.shuffle();
            System.out.println("Now shuffling...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds song to library
    private void add() {
        Scanner sc = new Scanner(System.in);
        String title;
        String artist;
        String duration;
        System.out.println("Please enter Song title");
        title = sc.next();
        System.out.println("Please enter Song artist");
        artist = sc.next();
        System.out.println("Please enter Song duration");
        duration = sc.next();

        if (userLib.addSong(title, artist, duration)) {
            System.out.println("Song added successfully");
        } else {
            System.out.println("Song already added to library");
        }
    }

    private void runLibrary() {
        Scanner sc = new Scanner(System.in);
        boolean back = false;

        while (!back) {
            int index = 0;
            System.out.println("1 -> Go back to main menu");
            System.out.println("----------------------------");
            System.out.println(userLib.getName());
            if (userLib.getSongs().size() == 0) {
                System.out.println("There are currently no songs in your library.");
            }
            while (index < userLib.getSongs().size()) {
                System.out.println(userLib.getSongs().get(index));
                index++;
            }


            String command = sc.next();
            command = command.toLowerCase();


            if (command.equals("1")) {
                back = true;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: renames library
    private void renameLibrary() {
        Scanner s = new Scanner(System.in);
        String title;
        System.out.println("Please enter new library name");
        title = s.nextLine();

        userLib.changeName(title);
        System.out.println("Library name changed successfully.");
    }

    // EFFECTS: saves library to file
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(userLib);
            jsonWriter.close();
            System.out.println("Saved " + userLib.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

    //: this
    // EFFECTS: loads library from file
    private void loadLibrary() {
        try {
            userLib = jsonReader.read();
            if (userLib.getCurrentSong() == null) {
                play = false;
                pause = false;
            } else {
                play = true;
                pause = false;
            }
            currentlyPlaying = userLib.getCurrentSong();
            System.out.println("Loaded " + userLib.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

