package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class Solver {
    protected HashSet<Cell> path;
    protected Maze maze;
    protected Cell departure;
    protected Cell arrival;

    protected boolean solved;
    protected boolean solving;

    protected boolean solvable;
    protected int clockTime;

    public Solver(Maze maze) {
        this.maze = maze;
        this.solved = false;
        this.solving = false;
        this.solvable = true;
        this.clockTime = 10;
        this.path = new HashSet<>();
    }
    public abstract void update();
    public abstract void path();
    public abstract void setDeparture(Cell departure);
    public abstract void setArrival(Cell arrival);
    public abstract void render(Graphics2D g2, int cellSize);

    public HashSet<Cell> getPath() {
        return path;
    }

    public Cell getDeparture() {
        return departure;
    }

    public Cell getArrival() {
        return arrival;
    }

    public boolean isSolved() {
        return solved;
    }

    public boolean isSolving() {
        return solving;
    }

    public boolean isSolvable() {
        return solvable;
    }
}
