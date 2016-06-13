package test;

import app.model.Media;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Abstract class for testing all Media instances.
 *
 * @author Mcdostone
 */
public abstract class MediaTest {

    protected char type;
    protected String path;
    protected Media media;

    @Test
    public void testToString() {
        assertEquals("toString is not good", "[" + this.type + "] " + this.path, media.toString());
    }

}