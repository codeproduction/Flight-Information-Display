package model;

import org.json.JSONObject;

/**
 * Provides the ability to write data from a Java object as a JSON object
 */

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
