package fr.celestgames.maze_generator;

import fr.celestgames.maze_generator.io.Window;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.builders.CellMerging;
import fr.celestgames.maze_generator.maze.builders.DepthFirst;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        Maze maze = new Maze(50, 100);
        window.setMaze(maze);
        Builder builder = new DepthFirst(maze);

        builder.disableClock();

        window.getThread().start();

        long start = System.nanoTime();
        builder.update();
        long end = System.nanoTime();

        System.out.println("Time: " + (end - start) / 1000000 + " ms");
    }
}
