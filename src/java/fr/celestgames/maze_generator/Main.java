package fr.celestgames.maze_generator;

import fr.celestgames.maze_generator.io.Window;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.builders.CellMerging;
import fr.celestgames.maze_generator.maze.builders.DepthFirst;
import fr.celestgames.maze_generator.maze.solvers.AStar;
import fr.celestgames.maze_generator.maze.solvers.BellmanFord;
import fr.celestgames.maze_generator.maze.solvers.Dijkstra;
import fr.celestgames.maze_generator.maze.solvers.Solver;
import fr.celestgames.maze_generator.ui.MazeScene;
import fr.celestgames.maze_generator.ui.PathFindingScene;

public class Main implements Runnable {
    Window window;

    Thread thread = new Thread(this, "Main Thread");

    public static void main(String[] args) {
        Main main = new Main();
        main.window = new Window();
        main.thread.start();
    }

    @Override
    public void run() {
        /*MazeScene scene = new MazeScene(window);
        Maze maze = new Maze(50, 50);
        Builder builder = new CellMerging(maze);
        Solver solver = new AStar(maze);

        window.setScene(scene);
        scene.setMaze(maze);
        scene.setBuilder(builder);
        scene.setSolver(solver);
        scene.setBuilderClockTime(0);
        scene.setSolverClockTime(1);

        solver.setDeparture(maze.getCell(0, 0));
        solver.setArrival(maze.getCell(maze.getWidth() - 1, maze.getHeight() - 1));

        window.getThread().start();

        while (true) {
            scene.update();
        }*/

        PathFindingScene scene = new PathFindingScene(window);
        Maze maze = new Maze(10, 10, true);
        Solver solver = new AStar(maze);

        window.setScene(scene);
        scene.setMaze(maze);
        scene.setSolver(solver);

        window.getThread().start();

        while (true) {
            scene.update();
        }
    }
}
