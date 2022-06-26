package fr.celestgames.maze.utils;

import fr.celestgames.maze.io.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {
    public static BufferedImage readImage(String inputPath) {
        URL pathURL = ImageUtils.class.getResource(inputPath);

        if (pathURL != null) {
            try {
                return ImageIO.read(pathURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void writeImage(String outputPath, Object content) {
        if (content instanceof BufferedImage) {
            try {
                ImageIO.write((BufferedImage) content, "png", new File(String.valueOf(Window.class.getResourceAsStream(outputPath))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Unknown or unsupported content format.");
        }
    }
}
