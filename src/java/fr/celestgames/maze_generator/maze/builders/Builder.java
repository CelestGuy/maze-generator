package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Builder {
    protected final Random rand = new Random();
    protected final Maze maze;
    protected Cell lastCell;

    public Builder(Maze maze) {
        this.maze = maze;
    }

    public abstract void update();

    public abstract boolean isBuilt();

    public abstract void render(Graphics2D g2, int cellSize);
}
