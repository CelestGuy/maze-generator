package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.Point;

import java.util.Random;

public abstract class Builder {
    protected final Random rand = new Random();
    public Maze maze;

    public Point oldPoint;

    public Builder(Maze maze) {
        this.maze = maze;
    }

    public abstract void initPath();

    public abstract void createPath();
}
