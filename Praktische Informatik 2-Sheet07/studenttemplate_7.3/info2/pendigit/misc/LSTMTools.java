package info2.pendigit.misc;

import java.net.URISyntaxException;
import java.net.URL;

import de.jannlab.io.Serializer;

/**
 * Some tools for this Exercise.
 * 
 * @author Sebastian Otte
 */
public class LSTMTools {
    
    /**
     * Received a string and substitutes the place holders
     * #1, #2, ... with given input values. This is just 
     * a bit more elegant than just concatenating a string.
     * 
     * @param str Original String containing place holders.
     * @param args Substitute values for the place holders.
     * @return Result string.
     */
    public static String var(
        final String str, 
        final int ...args
    ) {
        final String key = "#";
        String result = str;
        for (int i = 0; i < args.length; i++) {
            result = result.replace(
                key + (i + 1), 
                String.valueOf(args[i])
            );
        }
        return result;
    }
    
    
    private static final URL resourceURL(final String resource) throws URISyntaxException {
        final URL url = (
            ClassLoader.getSystemClassLoader().getResource(resource)
        );
        return url;
    }

    /**
     * Loads neural networks weights from a given resource (filename).
     * @param resource Resource destination.
     * @return Weight array loaded from resource.
     */
    public static double[] loadWeights(final String resource) {
        try {
            return (double[])(Serializer.read(
                resourceURL(resource).openStream()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}