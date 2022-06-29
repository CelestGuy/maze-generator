package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Builder {
    protected final Maze maze;
    protected final Random rand = new Random();
    protected int clockTime;
    protected Cell lastCell;

    protected boolean debug;

    public Builder(Maze maze) {
        this.maze = maze;
        this.clockTime = 10;
        debug = false;
    }

    ///////////////////////////////////////////////// Getters and Setters //////////////////////////////////////////////////////////

    public void setClockTime(int clockTime) {
        this.clockTime = clockTime;
    }
    public int getClockTime() {
        return this.clockTime;
    }
    public void disableClock() {
        this.clockTime = 0;
    }

    public void enableDebug() {
        this.debug = true;
    }

    public void disableDebug() {
        this.debug = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void update() {
        while (!isBuilt()) {
            if (clockTime > 0) {
                try {
                    Thread.sleep(clockTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            buildMaze();
        }
    }

    abstract void buildMaze();

    abstract boolean isBuilt();

    public abstract BufferedImage render();
}
