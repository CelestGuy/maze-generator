package fr.celestgames.maze_generator.ui;

import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.solvers.Solver;

public abstract class Scene {
    Maze mainMaze;
    Builder builder;
    Solver solver;

    abstract void update();

    abstract void render();

    void setMaze(Maze maze) {
        this.mainMaze = maze;
    }
}
