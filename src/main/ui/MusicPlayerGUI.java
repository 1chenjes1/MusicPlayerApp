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
import java.util.Scanner;

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
    private JPanel addSongPanel;
    private JPanel libraryPanel;
    private JLabel currentSong;
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

    public MusicPlayerGUI() {
        super("Music Player");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        setVisible(true);
        setResizable(false);

        initalizeFields();
        initializeMenu();

    }

    private void initalizeFields() {
        userLib = new Library("Your Library");
        play = false;
        pause = false;
        currentlyPlaying = null;
    }

    public void initializeMenu() {
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.PINK);
        controlPanel.setLayout(new GridLayout(1, 5));
        add(controlPanel, BorderLayout.SOUTH);
        userPanel = new JPanel();
        userPanel.setBackground(Color.PINK);
        userPanel.setLayout(new GridLayout(3, 6));
        add(userPanel, BorderLayout.NORTH);
        currentSong = new JLabel(setLabel(), SwingConstants.CENTER);
        add(currentSong);

        initializeMenuBottons();
        addButtons();
        addActionToButton();
    }

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
        b10 = new JButton("load library from file");
        b11 = new JButton("quit");
    }

    //https://stackoverflow.com/questions/36957450/fit-size-of-an-imageicon-to-a-jbutton
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void addButton(JButton b, JPanel panel) {
        b.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        b.setBackground(Color.white);
        panel.add(b);
        pack();
        setVisible(true);

    }

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

    public String setLabel() {
        if (currentlyPlaying == null) {
            return "Welcome to Music Player";
        } else {
            return "Currently playing:" + currentlyPlaying;
        }
    }



    private void initializeAddSongPanel() {
        addSongPanel = new JPanel(new GridLayout(3, 1));

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Return to main menu");
        addButton(mainMenuButton, addSongPanel);
        remove(userPanel);
        remove(controlPanel);
        remove(currentSong);
        add(addSongPanel);
        revalidate();
        repaint();
    }

    private void initalizeLibraryPanel() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("play")) {
            //
        } else if (e.getActionCommand().equals("pause")) {
            //
        } else if (e.getActionCommand().equals("next")) {
            //
        } else if (e.getActionCommand().equals("previous")) {
            //
        } else if (e.getActionCommand().equals("shuffle")) {
            //
        } else if (e.getActionCommand().equals("add song")) {
            initializeAddSongPanel();
        } else if (e.getActionCommand().equals("view library")) {
            initalizeLibraryPanel();
        } else if (e.getActionCommand().equals("rename library")) {
            //
        } else if (e.getActionCommand().equals("save library")) {
            //
        } else if (e.getActionCommand().equals("load library")) {
            //
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Return to main menu")) {
            returnToMainMenu();
        }
    }

    private void returnToMainMenu() {
        initializeMenu();
        remove(addSongPanel);

    }


}
