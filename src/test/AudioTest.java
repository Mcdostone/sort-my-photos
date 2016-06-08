package test;

import app.Audio;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test class for Audio class.
 *
 * @author Mcdostone
 */
public class AudioTest  extends MediaTest {

    @Before
    public void setUp() throws Exception {
        this.type = 'A';
        this.path = "my_audio.mp3";
        this.media = new Audio(this.path);
    }

    @Test
    public void testGetPath() {
        assertEquals("The path should be 'my_audio.mp3'", this.path, this.media.getPath());
    }

}