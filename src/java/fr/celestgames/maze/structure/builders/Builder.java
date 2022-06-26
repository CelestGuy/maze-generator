package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.structure.Maze;

import java.util.Random;

public abstract class Builder {
    protected final Random rand = new Random();
    public Maze maze;

    public Builder(Maze maze) {
        this.maze = maze;
    }

    public abstract void initPath();

    public abstract void createPath();
}
