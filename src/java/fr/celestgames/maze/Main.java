package fr.celestgames.maze;

import fr.celestgames.maze.io.Window;
import fr.celestgames.maze.structure.GenMode;
import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.builders.Builder;
import fr.celestgames.maze.structure.builders.DepthFirstSearching;

public class Main {
    public static final int ONE_SEC_IN_NANO = 1000000000;
    public static final int ONE_SEC_IN_MICRO = 1000000;

    public static void main(String[] args) {
        Window window = new Window();
        Maze maze = new Maze(25, 25, GenMode.DEPTH_FIRST_SEARCHING);
        Builder builder = new DepthFirstSearching(maze);
        window.setMaze(maze);
        window.setBuilder(builder);

        builder.initPath();

        while (!window.window.isVisible()) {

        }

        while (true) {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            builder.createPath();

            window.update();
        }
    }
}
