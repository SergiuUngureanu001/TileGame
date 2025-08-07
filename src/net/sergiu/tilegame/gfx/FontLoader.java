package net.sergiu.tilegame.gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(String path, float size) {
        try (InputStream is = FontLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            Font base = Font.createFont(Font.TRUETYPE_FONT, is);
            return base.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
