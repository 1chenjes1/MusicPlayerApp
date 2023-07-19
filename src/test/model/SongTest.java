package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SongTest {
    private Song testsong;

    @BeforeEach
    void runBefore() {
        testsong = new Song("Title","Person","1:00");
    }

    @Test
    void testConstructor() {
        assertEquals("Title", testsong.getTitle());
        assertEquals("Person", testsong.getArtist());
        assertEquals("1:00", testsong.getLength());
    }

    @Test
    void testToString() {
        assertEquals("Song{title='Title', artist='Person', duration='1:00'}",
                testsong.toString());
    }
}