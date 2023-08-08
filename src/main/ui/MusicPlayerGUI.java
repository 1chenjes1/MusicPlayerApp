package ui;

import model.Library;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayerGUI extends JFrame implements ActionListener {
    private Library userLib;
    private boolean play;
    private boolean pause;
    private Song currentlyPlaying;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/musicplayer.json";
    private JPanel controlPanel;
    private JPanel userPanel;
    private JPanel textPanel;
    private JPanel addSongPanel;
    private JPanel libraryPanel;
    private JPanel renamePanel;
    private JLabel saveAndLoad;
    private JLabel currentSong;
    private JLabel addedSong;
    private JLabel renameLibrary;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b10;
    private JButton b11;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;

    // Effects: Starts the GUI
    public MusicPlayerGUI() {
        super("Music Player");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        setVisible(true);
        setResizable(false);

        initalizeFields();
        initializeMenu();
    }

    // Modifies: this
    // EFFECTS: sets library fields to default settings
    private void initalizeFields() {
        userLib = new Library("Your Library");
        play = false;
        pause = false;
        currentlyPlaying = null;
    }

    // Modifies: this
    // Effects: initializes main menu
    public void initializeMenu() {
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.PINK);
        controlPanel.setLayout(new GridLayout(1, 5));
        add(controlPanel, BorderLayout.SOUTH);

        userPanel = new JPanel();
        userPanel.setBackground(Color.PINK);
        userPanel.setLayout(new GridLayout(3, 6));
        add(userPanel, BorderLayout.NORTH);

        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(3, 1));
        add(textPanel, BorderLayout.CENTER);
        saveAndLoad = new JLabel("",SwingConstants.CENTER);
        currentSong = new JLabel(setLabel(), SwingConstants.CENTER);
        textPanel.add(saveAndLoad);
        textPanel.add(currentSong);


        initializeMenuBottons();
        addButtons();
        addActionToButton();
    }

    // EFfects: adds button to main menu
    private void addButtons() {
        addButton(b1, controlPanel);
        addButton(b2, controlPanel);
        addButton(b3, controlPanel);
        addButton(b4, controlPanel);
        addButton(b5, controlPanel);
        addButton(b6, userPanel);
        addButton(b7, userPanel);
        addButton(b8, userPanel);
        addButton(b9, userPanel);
        addButton(b10, userPanel);
        addButton(b11, userPanel);
    }

    // Modifies: this
    // Effects: makes buttons for main menu
    private void initializeMenuBottons() {
        ImageIcon iconPlay = new ImageIcon("/Users/Jessica/Downloads/playbutton.png");
        ImageIcon iconPause = new ImageIcon("/Users/Jessica/Downloads/pausebutton.png");
        ImageIcon iconNext = new ImageIcon("/Users/Jessica/Downloads/nextbutton.png");
        ImageIcon iconPrevious = new ImageIcon("/Users/Jessica/Downloads/previous.png");
        ImageIcon iconShuffle = new ImageIcon("/Users/Jessica/Downloads/shufflebutton.png");
        b1 = new JButton(resizeIcon(iconPlay, 20,20));
        b2 = new JButton(resizeIcon(iconPause, 20,20));
        b3 = new JButton(resizeIcon(iconNext, 20,20));
        b4 = new JButton(resizeIcon(iconPrevious, 20,20));
        b5 = new JButton(resizeIcon(iconShuffle, 20,20));
        b6 = new JButton("Add song to library");
        b7 = new JButton("View library:" + " " + userLib.getName());
        b8 = new JButton("Rename library");
        b9 = new JButton("Save " + userLib.getName() + " to file");
        b10 = new JButton("Load library from file");
        b11 = new JButton("Quit");
    }

    // Effects: resizes icon to fit in button
    //https://stackoverflow.com/questions/36957450/fit-size-of-an-imageicon-to-a-jbutton
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // Effects: Adds button to given panel
    public void addButton(JButton b, JPanel panel) {
        b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        b.setBackground(Color.white);
        panel.add(b);
        pack();
        setVisible(true);
    }

    // Effects: adds actions to all buttons in main screen
    public void addActionToButton() {
        b1.addActionListener(this);
        b1.setActionCommand("play");
        b2.addActionListener(this);
        b2.setActionCommand("pause");
        b3.addActionListener(this);
        b3.setActionCommand("next");
        b4.addActionListener(this);
        b4.setActionCommand("previous");
        b5.addActionListener(this);
        b5.setActionCommand("shuffle");
        b6.addActionListener(this);
        b6.setActionCommand("add song");
        b7.addActionListener(this);
        b7.setActionCommand("view library");
        b8.addActionListener(this);
        b8.setActionCommand("rename library");
        b9.addActionListener(this);
        b9.setActionCommand("save library");
        b10.addActionListener(this);
        b10.setActionCommand("load library");
        b11.addActionListener(this);
        b11.setActionCommand("quit");
    }

    //Effects: returns what the currentSong label should show
    public String setLabel() {
        if (currentlyPlaying == null) {
            return "Welcome to Music Player";
        } else {
            return "Currently playing:" + currentlyPlaying + "\n paused?: " + pause;
        }
    }

    // Modifies: this
    // Effects: initializes add song panel
    public void initializeAddSongPanel() {
        addSongPanel = new JPanel(new GridLayout(5, 2));

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Return to Main Menu Song");
        addButton(mainMenuButton, addSongPanel);

        JButton addSong = new JButton("Add song to library");
        addSong.setActionCommand("Add song to library");
        addSong.addActionListener(this);
        addSongPanel.add(addSong);

        addButtonsToAddSongPanel();

        addedSong = new JLabel("");
        addSongPanel.add(addedSong);

        remove(userPanel);
        remove(controlPanel);
        remove(textPanel);
        add(addSongPanel);
        revalidate();
        repaint();
    }

    // Modifies: this
    // Effects: adds buttons and text fields to add song panel
    private void addButtonsToAddSongPanel() {
        JLabel name = new JLabel("Song name");
        t1 = new JTextField(10);
        JLabel artist = new JLabel("Song artist");
        t2 = new JTextField(10);
        JLabel duration = new JLabel("Song duration");
        t3 = new JTextField(10);

        addSongPanel.add(name);
        addSongPanel.add(t1);
        addSongPanel.add(artist);
        addSongPanel.add(t2);
        addSongPanel.add(duration);
        addSongPanel.add(t3);
    }

    // Modifies: this
    // Effects: Initializes the view library panel
    private void initalizeLibraryPanel() {
        libraryPanel = new JPanel(new GridLayout(5,1));
        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Return to Main Menu library");
        addButton(mainMenuButton, libraryPanel);

        JLabel name = new JLabel(userLib.getName() + ":");
        libraryPanel.add(name);

        JPanel librarySongPanel = new JPanel();
        librarySongPanel.setLayout(new BoxLayout(librarySongPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(librarySongPanel);

        libraryPanel.add(scrollPane);

        addLabelsToLibraryPanel(librarySongPanel);

        remove(userPanel);
        remove(controlPanel);
        remove(textPanel);
        add(libraryPanel);
        revalidate();
        repaint();
    }

    // Modifies: this all songs as labels to library panel
    private void addLabelsToLibraryPanel(JPanel panel) {

        if (userLib.getSongs().size() == 0) {
            JLabel noSong = new JLabel("There are currently no songs in your library");
            panel.add(noSong);
        } else {
            for (Song s : userLib.getSongs()) {
                JLabel label = new JLabel(s.toString());
                panel.add(label);
            }
        }
    }

    // EFFECTS: gets action command and decides what method to call
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("play")) {
            doPlay();
        } else if (e.getActionCommand().equals("pause")) {
            doPause();
        } else if (e.getActionCommand().equals("next")) {
            doNext();
        } else if (e.getActionCommand().equals("previous")) {
            doPrevious();
        } else if (e.getActionCommand().equals("shuffle")) {
            doShuffle();
        } else if (e.getActionCommand().equals("add song")) {
            initializeAddSongPanel();
        } else if (e.getActionCommand().equals("view library")) {
            initalizeLibraryPanel();
        } else if (e.getActionCommand().equals("rename library")) {
            initializeRenamePanel();
        } else if (e.getActionCommand().equals("save library")) {
            saveLibrary();
        } else if (e.getActionCommand().equals("load library")) {
            loadLibrary();
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Return to Main Menu Song")) {
            returnToMainMenuSong();
        } else if (e.getActionCommand().equals("Return to Main Menu library")) {
            returnToMainMenuLibrary();
        } else if (e.getActionCommand().equals("Add song to library")) {
            add();
        } else if (e.getActionCommand().equals("Return to Main Menu Rename")) {
            returnToMainMenuRename();
        } else if (e.getActionCommand().equals("Rename Library")) {
            renameLibrary();
        }
    }

    // Modifies: this
    // EFFECTS: adds given song to user library if not already added.
    private void add() {
        if (isNotASong()) {
            addedSong.setText("Error: Please fill out all fields");
        } else {
            String title = t1.getText();
            String artist = t2.getText();
            String duration = t3.getText();

            if (userLib.addSong(title, artist, duration)) {
                addedSong.setText("Song added successfully");
            } else {
                addedSong.setText("Song already added");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: If there are no songs in library, display user message
    //          If no song is currently playing, play most recently added song in lib
    //          Otherwise, set play true and set false
    private void doPlay() {
        if (userLib.getSongs().size() == 0) {
            currentSong.setText("There are currently no songs in your library.");
        } else if (!this.play && !this.pause) {
            this.play = true;
            this.currentlyPlaying = userLib.play();
            currentSong.setText(setLabel());
        } else {
            this.play = true;
            this.pause = false;
            currentSong.setText(setLabel());
        }
    }

    // MODIFIES: this
    // EFFECTS: If no song is currently playing, display user message
    //          Otherwise set pause true and play false
    private void doPause() {
        if (currentlyPlaying == null) {
            currentSong.setText("You are not currently playing a song.");
        } else if (!this.pause) {
            this.pause = true;
            this.play = false;
            currentSong.setText(setLabel());
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays next song in list as long as there is currently a song playing
    //          and there is at least 1 song in library
    private void doNext() {
        if (currentlyPlaying == null) {
            currentSong.setText("You are not currently playing a song.");
        } else if (userLib.getSongs().size() == 0) {
            currentSong.setText("There are currently no songs in your library.");
        } else {
            currentlyPlaying = userLib.next(currentlyPlaying);
            currentSong.setText(setLabel());
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays previous song in list as long as there is currently a song playing
    //          and there is at least 1 song in library
    private void doPrevious() {
        if (currentlyPlaying == null) {
            currentSong.setText("You are not currently playing a song.");
        } else if (userLib.getSongs().size() == 0) {
            currentSong.setText("There are currently no songs in your library.");
        } else {
            currentlyPlaying = userLib.previous(currentlyPlaying);
            currentSong.setText(setLabel());
        }
    }

    // MODIFIES: this
    //EFFECTS: picks and plays random song in library as long as there is at least 1 song in library
    private void doShuffle() {
        if (userLib.getSongs().size() == 0) {
            currentSong.setText("There are currently no songs in your library.");
        } else {
            currentlyPlaying = userLib.shuffle();
            currentSong.setText(setLabel());
        }
    }

    // EFFECTS: returns true if inputs in textfields do not meet requirement of song (i.e. some fields are empty)
    private boolean isNotASong() {
        return t1.getText().trim().length() == 0
                || t2.getText().trim().length() == 0
                || t3.getText().trim().length() == 0;
    }

    // EFFECTS: reinitializes main menu and removes add song panel
    private void returnToMainMenuSong() {
        initializeMenu();
        remove(addSongPanel);
    }

    // EFFECTS: reinitializes main menu and removes library panel
    private void returnToMainMenuLibrary() {
        initializeMenu();
        remove(libraryPanel);
    }

    //Effects: reinitializes main menu and removes rename library panel
    private void returnToMainMenuRename() {
        initializeMenu();
        remove(renamePanel);
    }

    // Modifies: this
    // EFFECTS: Initializes the rename library panel
    public void initializeRenamePanel() {
        renamePanel = new JPanel(new GridLayout(5, 2));

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Return to Main Menu Rename");
        addButton(mainMenuButton, renamePanel);

        JButton renameLib = new JButton("Rename Library");
        renameLib.setActionCommand("Rename Library");
        renameLib.addActionListener(this);
        renamePanel.add(renameLib);

        addButtonsToRenamePanel();

        renameLibrary = new JLabel("");
        renamePanel.add(renameLibrary);

        remove(userPanel);
        remove(controlPanel);
        remove(textPanel);
        add(renamePanel);
        revalidate();
        repaint();
    }

    // Modifies: this
    // Effects: adds required buttons and text field to the rename library panel
    private void addButtonsToRenamePanel() {
        JLabel name = new JLabel("New library name");
        t4 = new JTextField(10);

        renamePanel.add(name);
        renamePanel.add(t4);
    }

    // Modifies: this
    // Effects: renames library to given name
    public void renameLibrary() {
        if (t4.getText().trim().length() == 0) {
            renameLibrary.setText("Error: Please fill out all fields");
        } else {
            String title = t4.getText();
            userLib.changeName(title);
            renameLibrary.setText("Library name changed successfully.");
        }
    }

    // EFFECTS: saves library to file
    private void saveLibrary() {
        try {
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(userLib);
            jsonWriter.close();
            saveAndLoad.setText("Saved " + userLib.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            saveAndLoad.setText("Unable to write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads library from file
    private void loadLibrary() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            userLib = jsonReader.read();
            if (userLib.getCurrentSong() == null) {
                play = false;
                pause = false;
            } else {
                play = true;
                pause = false;
            }
            currentlyPlaying = userLib.getCurrentSong();
            b7.setText("View library:" + " " + userLib.getName());
            b9.setText("Save " + userLib.getName() + " to file");
            currentSong.setText(setLabel());
            saveAndLoad.setText("Loaded " + userLib.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            saveAndLoad.setText("Unable to read from file: " + JSON_STORE);
        }
    }

}
