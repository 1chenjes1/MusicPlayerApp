package ui;

import model.Library;
import model.Song;

import java.util.Random;
import java.util.Scanner;

public class MusicPlayer {

    private Library userlib;
    private boolean play;
    private boolean pause;
    private Song currentlyplaying;

    // EFFECTS: runs the music player application
    public MusicPlayer() {

        runMusicPlayer();
    }

    public void runMusicPlayer() {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        userlib = new Library("Library");

        userlib.addSong("H", "Poop", "Poop");
        userlib.addSong("D", "Poop", "Poop");
        userlib.addSong("F", "Poop", "Poop");
        userlib.addSong("L", "Poop", "Poop");

        play = false;
        pause = false;
        currentlyplaying = null;

        while (!quit) {
            mainMenu();
            int command = sc.nextInt();
            sc.nextLine();

            if (command == 8) {
                quit = true;
            } else {
                openingCommand(command);
            }
        }
        System.out.println("Music Player shutting down...");

    }

    // EFFECTS: displays menu of beginning options to user
    private void mainMenu() {
        if (currentlyplaying == null) {
            System.out.println("Click play to begin playing a song");
        } else {
            System.out.println("Currently playing:" + currentlyplaying.toString());
        }
        System.out.println("Paused?:" + " " + pause);
        System.out.println("1 -> Play");
        System.out.println("2 -> Pause");
        System.out.println("3 -> Next");
        System.out.println("4 -> Previous");
        System.out.println("5 -> Shuffle");
        System.out.println("6 -> Add song to library");
        System.out.println("7 -> View library");
        System.out.println("8 -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes opening commands
    private void openingCommand(Integer command) {
        if (command.equals(1)) {
            doPlay();
        } else if (command.equals(2)) {
            doPause();
        } else if (command.equals(3)) {
            doNext();
        } else if (command.equals(4)) {
            doPrevious();
        } else if (command.equals(5)) {
            doShuffle();
        } else if (command.equals(6)) {
            add();
        } else if (command.equals(7)) {
            runlibrary();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void add() {
        Scanner sc = new Scanner(System.in);
        String title = null;
        String artist = null;
        String duration = null;
        System.out.println("Please enter Song title");
        title = sc.next();
        System.out.println("Please enter Song artist");
        artist = sc.next();
        System.out.println("Please enter Song duration");
        duration = sc.next();

        if (userlib.addSong(title, artist, duration)) {
            System.out.println("Song added succesfully");
        } else {
            System.out.println("Song already added to library");
        }
    }

    private void doPlay() {

        if (!this.play && !this.pause) {
            this.play = true;
            this.currentlyplaying = userlib.getSongs().get(0);
        } else {
            this.play = true;
            this.pause = false;
        }
    }

    private void doPause() {
        if (!this.pause) {
            this.pause = true;
        }
    }

    private void doNext() {
        if (currentlyplaying == null) {
            System.out.println("Not currently playing a song.");
        } else {
            currentlyplaying = userlib.next(currentlyplaying);
        }
    }

    private void doPrevious() {
        if (currentlyplaying == null) {
            System.out.println("Not currently playing a song.");
        } else {
            currentlyplaying = userlib.previous(currentlyplaying);
        }
    }


    //EFFECTS: picks and plays random song in given Playlist
    private void doShuffle() {
        currentlyplaying = userlib.shuffle();
        System.out.println("Now shuffling...");
    }

    private void runlibrary() {
        Scanner sc = new Scanner(System.in);
        boolean back = false;

        while (!back) {
            int command = sc.nextInt();
            sc.nextLine();

            int index = 0;
            while (index < userlib.getSongs().size()) {
                System.out.println(userlib.getSongs().get(index));
                index++;
            }
            System.out.println("6 -> Go back to main menu");

            if (command == 6) {
                back = true;
            }
        }
    }
}

