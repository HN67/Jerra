package jerra.core;

import java.io.InputStream;

import javafx.scene.image.Image;

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

}
