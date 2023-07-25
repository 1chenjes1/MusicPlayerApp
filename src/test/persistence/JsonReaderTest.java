package persistence;

import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    JsonReader tr;
    Library testlib;

    @Test
    void testReaderNonExistentFile() {
        tr = new JsonReader("./data/nonexistent.json");
        try {
            testlib = tr.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        tr = new JsonReader("./data/testEmptyLib.json");
        try {
            testlib = tr.read();
            assertEquals("Your Library", testlib.getName());
            assertEquals(0, testlib.getSongs().size());
        } catch (IOException e) {
            fail("threw IOException");
        }
    }

    @Test
    void testReaderGeneralLibrary() {
        tr = new JsonReader("./data/testGeneralLib.json");
        try {
            testlib = tr.read();
            testlib = tr.read();
            assertEquals("Test Library", testlib.getName());
            assertEquals(2, testlib.getSongs().size());
            checkSong("a","a", "1:00",testlib.getSongs().get(0));
            checkSong("b","b", "2:00",testlib.getSongs().get(1));
        } catch (IOException e) {
            fail("Threw IOException");
        }
    }
}
