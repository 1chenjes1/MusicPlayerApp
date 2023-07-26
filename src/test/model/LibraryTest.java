package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library testlib;

    @BeforeEach
    void runBefore() {
        testlib = new Library("test");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testlib.getSongs().size());
        assertEquals("test", testlib.getName());
    }

    @Test
    void testAddSong() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        assertFalse(testlib.addSong("test1","boy", "1:50"));
        assertTrue(testlib.addSong("test1","bo", "1:50"));
        assertTrue(testlib.addSong("test","boy", "1:50"));
        assertTrue(testlib.addSong("test1","boy", "1:51"));
        assertFalse(testlib.addSong("test1","bo", "1:50"));

        assertEquals(4, testlib.getSongs().size());
    }

    @Test
    void testPlay() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        assertTrue(testlib.addSong("test2","girl", "3:00"));
        assertEquals(testlib.getSongs().get(0), testlib.play());
    }

    @Test
    void testNext() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        Song s1 = testlib.getSongs().get(0);

        assertEquals(s1,testlib.next(s1));
        assertEquals(s1,testlib.next(s1));

        assertTrue(testlib.addSong("test2","girl", "3:00"));
        Song s2 = testlib.getSongs().get(1);
        assertTrue(testlib.addSong("test3","girl", "2:00"));
        Song s3 = testlib.getSongs().get(2);


        assertEquals(s2,testlib.next(s1));
        assertEquals(s3,testlib.next(s2));
        assertEquals(s1,testlib.next(s3));
    }

    @Test
    void testPrevious() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        Song s1 = testlib.getSongs().get(0);

        assertEquals(s1,testlib.previous(s1));
        assertEquals(s1,testlib.previous(s1));

        assertTrue(testlib.addSong("test2","girl", "3:00"));
        Song s2 = testlib.getSongs().get(1);
        assertTrue(testlib.addSong("test3","girl", "2:00"));
        Song s3 = testlib.getSongs().get(2);

        assertEquals(s3,testlib.previous(s1));
        assertEquals(s1,testlib.previous(s2));
        assertEquals(s2,testlib.previous(s3));
    }

    @Test
    void testShuffle() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        assertTrue(testlib.addSong("test2","girl", "3:00"));
        assertTrue(testlib.addSong("test3","girl", "2:00"));
        Song s = testlib.shuffle();
        assertTrue(testlib.getSongs().contains(s));
    }

    @Test
    void testShuffle1() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        assertTrue(testlib.addSong("test2","girl", "3:00"));
        assertTrue(testlib.addSong("test3","girl", "2:00"));

        Song s1 = testlib.play();
        Song s2 = testlib.shuffle();
        assertFalse(s1 == s2);
        assertTrue(testlib.getSongs().contains(s2));
    }

    @Test
    void testChangeName() {
        testlib.changeName("test2");
        assertEquals("test2", testlib.getName());
    }

    @Test
    void testSetCurrentSong() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        assertTrue(testlib.addSong("test2","girl", "3:00"));
        assertTrue(testlib.addSong("test3","girl", "2:00"));
        Song s1 = testlib.getSongs().get(1);
        testlib.setCurrentSong(s1);
        assertEquals(s1, testlib.getCurrentSong());
    }

    @Test
    void testIndexOfSong() {
        assertTrue(testlib.addSong("test1","boy", "1:50"));
        assertTrue(testlib.addSong("test2","girl", "3:00"));
        assertTrue(testlib.addSong("test3","girl", "2:00"));

        Song s1 = new Song("test2","girl", "3:00");
        Song s2 = new Song("test1","boy", "1:50");

        assertEquals(1, testlib.indexOfSong(s1));
        assertEquals(0, testlib.indexOfSong(s2));
    }
}
