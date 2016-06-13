package test;

import app.model.Video;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test class for Video class.
 *
 * @author Mcdostone
 */
public class VideoTest extends MediaTest {

    @Before
    public void setUp() throws Exception {
        this.type = 'V';
        this.path = "my_video.mp4";
        this.media = new Video(this.path);
    }

    @Test
    public void testGetPath() {
        assertEquals("The path should be 'my_image.png'", this.path, this.media.getPath());
    }

}