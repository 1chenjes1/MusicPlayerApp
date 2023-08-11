# My Personal Project

## Music Player Application

This application will be a simple version of a mp3 application,
which will play a song and have the option of pausing, skipping and replaying.
The app will start with a limited library with default songs that are available to the user.
However, there will be the option for the user to upload their own sound files.
In addition, there will be a playlist option, where playlists can be created, viewed, and deleted. 
This application will be a simplified version of already existing applications such as **Spotify** or **Apple Music**.
Anyone wanting to create playlists that play their specific songs can utilise this application.

As someone who is passionate about music and has been involved in making and producing music their whole life, I am 
taking the opportunity to create a personal project where I can use my interests and meaningfully integrate them 
in an academic setting.


## User Stories
- As a user, I want to be able to be able to add songs to the library
- As a user, I want to be able to view all the songs in the library
- As a user, I want to be able to change the name of my library 
- As a user, I want to be able to see the song title, artist and song length of the current song playing
- As a user, I want to be able to pause, play, skip, replay a song
- As a user, I want to be able to shuffle my library
- As a user, I want to be able to save my library with all my songs 
- As a user, I want to be able to reload my library with all my songs from file

## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by clicking the "Add song to library" button. 
  This will take you to a new screen where you can type in the song name, artist and duration. These must all be 
  filled out for the song to be added. A message will appear confirming if the song has been successfully added.
- You can generate the second required action related to adding Xs to a Y by clicking the "View library" button, which 
  will show you a list of all the songs currently in the library, with their name, artist and duration.
  If there are no songs in the library, a message saying no songs will appear.
- You can locate my visual component by looking at the control panel at the bottom of the main screen page.
  I have added icons to all the buttons (play,pause,next,etc.)
- You can save the state of my application by clicking the "save library" button
- You can reload the state of my application by clicking the "load library" button

## Phase 4: Task 2
Events possible:
- "Added Song" when new song is added to library
- "Playing first song in library" when the play button is hit initially
- "Playing next track" when next button is hit
- "Playing previous track" when previous button is hit
- "Library shuffled" when shuffle button is hit
- "Changed library name" when library is renamed

## Phase 4: Task 3
- Instead of having a field for JsonWriter and JsonReader in the MusicPlayerGUI class, I could've instantiated 
  JsonReader and JsonWriter objects within those methods to get rid of couplings
- Could've made multiple classes for the GUI, as all the methods corresponding to the GUI are compiled in one singular 
  class. As a result, the current GUI class is very long, and has reduced cohesion.