package net.sergiu.tilegame.utils;

import java.io.*;

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        // 1) Ask the ClassLoader for an InputStream instead of new FileReader(...)
        InputStream is = Utils.class
                .getClassLoader()
                .getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("Resource not found on classpath: " + path);
        }

        // 2) Wrap it in a BufferedReader
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading resource: " + path, e);
        }

        return builder.toString();
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
