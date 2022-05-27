package core.parsers;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The ResourceParser class provides access to methods for reading files found in classpath (resource folder)
 */
public class ResourceParser {

    /**
     * Reads file from the resource folder
     *
     * @param fileName Name of the file
     * @return InputStream
     * @throws FileNotFoundException if there is no such file in the classpath
     */
    public static @NotNull InputStream readFile(String fileName) throws FileNotFoundException {
        InputStream stream = ResourceParser.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new FileNotFoundException(fileName + " file not found in the classpath");
        }
        return stream;
    }
}
