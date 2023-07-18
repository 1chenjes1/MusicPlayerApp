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

        userlib.addSong("Poop", "Poop", "Poop");
        userlib.addSong("Poop", "Poop", "Poop");
        userlib.addSong("Poop", "Poop", "Poop");
        userlib.addSong("Poop", "Poop", "Poop");

        play = false;
        pause = false;


        while (!quit) {
            int command = sc.nextInt();
            sc.nextLine();

            mainMenu();

            if (command == 7) {
                quit = true;
            } else {
                openingCommand(command);
            }
        }


    }

    // EFFECTS: displays menu of beginning options to user
    private void mainMenu() {
        System.out.println("Currently playing:" + currentlyplaying.toString());
        System.out.println("Paused?:" + " " + pause);
        System.out.println("1 -> Play");
        System.out.println("2 -> Pause");
        System.out.println("3 -> Replay");
        System.out.println("4 - Shuffle");
        System.out.println("5 -> add song to library");
        System.out.println("6 -> view library");
        System.out.println("7 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes opening commands
    private void openingCommand(Integer command) {
        if (command.equals(1)) {
            play();
        } else if (command.equals(2)) {
            pause();
        } else if (command.equals(3)) {
            replay();
        } else if (command.equals(4)) {
            shuffle();
        } else if (command.equals(5)) {
            add();
        } else if (command.equals(6)) {
            runlibrary();
        } else {
            System.out.println("Selection not valid...");
        }
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

    private void play() {
        int i = (userlib.getSongs().size()) - 1;

        if (!play && !pause) {
            this.play = true;
            this.currentlyplaying = userlib.getSongs().get(i);
        } else {
            this.play = true;
            this.pause = false;
        }
    }

    private void pause() {

    }

    private void replay() {

    }


    //EFFECTS: picks and plays random song in given Playlist
    private void shuffle() {
        Random rndm = new Random();
        Song song = userlib.getSongs().get(rndm.nextInt());
        System.out.println("now playing:" + song.toString());
    }
}
