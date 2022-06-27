package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.util.ArrayList;

public abstract class Solver {
    protected final ArrayList<Cell> path;
    protected Maze maze;
    protected Cell departure;
    protected Cell arrival;

    protected boolean solved;
    protected boolean solving;
    protected int clockTime;

    public Solver(Maze maze) {
        this.maze = maze;
        this.solved = false;
        this.solving = false;
        this.clockTime = 10;
        this.path = new ArrayList<>();
    }

    public abstract void solve();

    public abstract void path();

    public boolean isSolved() {
        return solved;
    }

    public boolean isSolving() {
        return solving;
    }

    public void setClockTime(int clockTime) {
        this.clockTime = clockTime;
    }

    public void disableClock() {
        this.clockTime = 0;
    }

    public int getClockTime() {
        return this.clockTime;
    }

    public void setDeparture(Cell departure) {
        this.departure = departure;
    }

    public void setArrival(Cell arrival) {
        this.arrival = arrival;
    }

    public ArrayList<Cell> getPath() {
        return path;
    }

    public Cell getDeparture() {
        return departure;
    }

    public Cell getArrival() {
        return arrival;
    }
}
