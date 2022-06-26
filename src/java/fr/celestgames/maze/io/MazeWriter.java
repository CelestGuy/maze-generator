package fr.celestgames.maze.io;

import fr.celestgames.maze.structure.Maze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MazeWriter {
    public static void saveMaze(File outputFile, Maze maze) {
        try {
            FileOutputStream saveFileOS = new FileOutputStream(outputFile);
            ObjectOutputStream mazeOS = new ObjectOutputStream(saveFileOS);

            mazeOS.writeObject(maze);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMaze(BufferedImage e) {

    }

    public static void saveMaze(BufferedImage[] e) {

    }
}
