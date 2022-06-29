package fr.celestgames.maze_generator.utils;

import fr.celestgames.maze_generator.maze.Maze;

import java.awt.image.BufferedImage;
import java.io.*;

public class MazeFile {
    public static Maze loadMaze(File savedMaze) {
        if (savedMaze != null) {
            try {
                FileInputStream saveFileIS = new FileInputStream(savedMaze);
                ObjectInputStream savedMazeIS = new ObjectInputStream(saveFileIS);

                return (Maze) savedMazeIS.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static Maze loadMaze(BufferedImage imageContent) {
        System.err.println("Not implemented yet.");

        /*Maze tempMaze;
        if (imageContent != null) {

        }*/
        return null;
    }
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
