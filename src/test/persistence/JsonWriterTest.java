package persistence;

import model.Library;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    JsonWriter tw;
    Library testlib;
    JsonReader tr;

    @Test
    void testWriterInvalidFile() {
        try {
            testlib = new Library("Your Library");
            tw = new JsonWriter("./data/my\0illegal:fileName.json");
            tw.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            testlib = new Library("Your Library");
            tw = new JsonWriter("./data/testEmptyLib.json");
            tw.open();
            tw.write(testlib);
            tw.close();

            tr = new JsonReader("./data/testEmptyLib.json");
            testlib = tr.read();
            assertEquals("Your Library", testlib.getName());
            assertEquals(0, testlib.getSongs().size());
            assertTrue(testlib.getCurrentSong() == null);
        } catch (FileNotFoundException e) {
            fail("Threw FileNotFoundException");
        } catch (IOException e) {
            fail("Threw IOException");
        }
    }

    @Test
    void testWriterGeneralLibrary() {
        try {
            testlib = new Library("Your Library");
            testlib.addSong("a", "a", "1:00");
            testlib.addSong("b", "b", "2:00");
            testlib.changeName("Test Library");
            tw = new JsonWriter("./data/testGeneralLib.json");
            tw.open();
            tw.write(testlib);
            tw.close();

            tr = new JsonReader("./data/testGeneralLib.json");
            testlib = tr.read();
            assertEquals("Test Library", testlib.getName());
            assertEquals(2, testlib.getSongs().size());
            checkSong("a","a", "1:00",testlib.getSongs().get(0));
            checkSong("b","b", "2:00",testlib.getSongs().get(1));
            assertTrue(testlib.getCurrentSong() == null);
        } catch (FileNotFoundException e) {
            fail("Threw FileNotFoundException");
        } catch (IOException e) {
            fail("Threw IOException");
        }
    }

    @Test
    void testWriterGeneralLibrary1() {
        try {
            testlib = new Library("Your Library");
            testlib.addSong("a", "a", "1:00");
            testlib.addSong("b", "b", "2:00");
            testlib.changeName("Test Library");
            testlib.play();
            tw = new JsonWriter("./data/testGeneralLib1.json");
            tw.open();
            tw.write(testlib);
            tw.close();

            tr = new JsonReader("./data/testGeneralLib1.json");
            testlib = tr.read();
            assertEquals("Test Library", testlib.getName());
            assertEquals(2, testlib.getSongs().size());
            checkSong("a","a", "1:00",testlib.getSongs().get(0));
            checkSong("b","b", "2:00",testlib.getSongs().get(1));
            checkSong("a","a", "1:00",testlib.getCurrentSong());
        } catch (FileNotFoundException e) {
            fail("Threw FileNotFoundException");
        } catch (IOException e) {
            fail("Threw IOException");
        }
    }
}
