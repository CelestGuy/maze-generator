package fr.celestgames.maze.io;

import fr.celestgames.maze.game.Maze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MazeReader {
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
}
