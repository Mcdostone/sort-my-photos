package app.model;

import java.util.HashMap;
import java.util.Set;

/**
 * MediaProperties is a data structure which enable to have
 * more details about a media (dimension, FPS, duration ...).
 *
 * @author Mcdostone
 */
public class MediaProperties {

    private HashMap<String, Object> properties;

    public MediaProperties() {
        this.properties = new HashMap<>();
    }

    public Set<String> keySet() {
        return this.properties.keySet();
    }

    public void put(String key, Object value) {
        this.properties.put(key, value);
    }

    public Object get(Object key) {
        return this.properties.get(key);
    }

}
