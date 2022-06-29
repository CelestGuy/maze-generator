package fr.celestgames.maze_generator;

import fr.celestgames.maze_generator.io.Window;
import fr.celestgames.maze_generator.maze.GenMode;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.builders.CellMerging;
import fr.celestgames.maze_generator.maze.builders.DepthFirstSearch;
import fr.celestgames.maze_generator.maze.solvers.BellmanFord;
import fr.celestgames.maze_generator.maze.solvers.Dijkstra;
import fr.celestgames.maze_generator.maze.solvers.Solver;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        Maze maze = new Maze(15, 15);
        Builder builder = new DepthFirstSearch(maze);

        Solver solver = new BellmanFord(maze);

        //window.setMaze(maze);
        //window.setBuilder(builder);
        //window.setSolver(solver);

        builder.setClockTime(10);
        solver.setClockTime(10);

        while (!window.window.isVisible()) {

        }

        window.getThread().start();

        builder.createPath();

        solver.setDeparture(maze.getCell(0, 0));
        solver.setArrival(maze.getCell(maze.getWidth() - 1, maze.getHeight() - 1));

        solver.solve();
        solver.path();
    }
}
