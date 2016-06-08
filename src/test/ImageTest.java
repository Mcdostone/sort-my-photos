package test;

import app.Image;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for Image class.
 *
 * @author Mcdostone
 */
public class ImageTest extends MediaTest {

    @Before
    public void setUp() throws Exception {
        this.path = "my_image.png";
        this.media = new Image(this.path);
    }

    @Test
    public void testGetPath() {
        assertEquals("The path should be 'my_image.png'", this.path, this.media.getPath());
    }

}