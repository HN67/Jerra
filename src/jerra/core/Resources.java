package jerra.core;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javafx.scene.image.Image;
import jerra.room.Room;

/**
 * Resources class, provides methods for loading resources.
 */
public class Resources {

    /**
     * Trys to load the given path as a Steam
     * @param path a String, e.g. `/resources/logo.png`
     * @return a InputSteam, may be null if file was not found
     */
    public static InputStream loadStream(String path) {
        return Resources.class.getResourceAsStream(path);
    }

    /**
     * Trys to load the given path into a JavaFX Image
     * @param path a String, e.g. `/resources/logo.png`
     * @return a Image
     */
    public static Image loadImage(String path) {
        return new Image(loadStream(path));
    }

    /**
     * Resolves a relative path to the resources folder
     * @param path a String, a relative path, e.g. `logo.png`
     * @return a String, a classpath absolute path to the resources folder, e.g. `/resources/logo.png`
     */
    public static String resourcePath(String path) {
        return "/resources/" + path;
    }

    /**
     * Saves a serializable to the given path.
     * If the path is relative, it will be interpreted relative to the current working directory.
     * Example usage would be 'obj.ser' which serializes the object to 'obj.ser' in the cwd.
     * @param path a String, absolute or relative (to cwd)
     * @param object a Serializable, the object to be serialized
     */
    public static void saveObject(String path, Serializable object) {
        try {
            FileOutputStream stream = new FileOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(stream);
            output.writeObject(object);
            output.close();
        } catch (Exception e) {
            System.out.println("Failed to save object");
        }
    }

    /**
     * Loads a serialized object from the specified location.
     * @param path a String, absolute or relative (to cwd)
     * @return a Object, the result of deserializing the specified file. Will be null if the loading failed.
     */
    public static Object loadObject(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            ObjectInputStream input = new ObjectInputStream(stream);
            Object object = input.readObject();
            input.close();
            return object;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Attempts to load a Room from the specified serialized file.
     * @param path a String, the absolute or relative (cwd) path to the file.
     * @return a Room, the Room deserialized from the file. Will be null if loading failed.
     */
    public static Room loadRoom(String path) {

        try {
            return (Room) loadObject(path);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
        
    }

    public static void main(String[] args) {
        saveObject("test.ser", "abc");
        String test = (String) loadObject("test.ser");
        System.out.println(test);
    }

}
