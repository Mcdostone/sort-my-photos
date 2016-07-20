package app.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MediaProperties is a data structure which enable to have
 * more details about a media (dimension, FPS, duration ...).
 *
 * @author Mcdostone
 */
public class MediaProperties {

    private HashMap<String, Object> properties;
    private ArrayList<String> orderAdding;

    public MediaProperties() {
        this.properties = new HashMap<>();
        this.orderAdding = new ArrayList<>();
    }

    public ArrayList<String> keyList() {
        return this.orderAdding;
    }

    public void put(String key, Object value) {
        if(value != null) {
            if(!this.properties.containsKey(key))
                this.orderAdding.add(key);
            this.properties.put(key, value);
        }
    }

    public Object get(Object key) {
        return this.properties.get(key);
    }

}
