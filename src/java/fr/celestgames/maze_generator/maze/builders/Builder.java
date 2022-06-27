package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.util.Random;

public abstract class Builder {
    public final Maze maze;
    protected Cell lastCell;
    protected int clockTime;

    public Builder(Maze maze) {
        this.maze = maze;
        this.clockTime = 10;
    }

    public abstract void createPath();

    public void setClockTime(int clockTime) {
        this.clockTime = clockTime;
    }

    public void disableClock() {
        this.clockTime = 0;
    }

    public int getClockTime() {
        return this.clockTime;
    }

    public Cell getLastCell() {
        return this.lastCell;
    }
}
