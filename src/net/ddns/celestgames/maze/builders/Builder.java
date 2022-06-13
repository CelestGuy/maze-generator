package net.ddns.celestgames.maze.builders;

import net.ddns.celestgames.maze.game.Game;
import net.ddns.celestgames.maze.game.Maze;

import java.util.Random;

public abstract class Builder {
    protected final Random rand = new Random();

    public Game game;
    public Maze maze;
    public int x, y, oldX, oldY;

    public Builder(Game game, Maze maze) {
        this.game = game;
        this.maze = maze;

        this.x = 0;
        this.y = 0;
        this.oldX = 0;
        this.oldY = 0;
    }

    public abstract void createPath();

    public abstract void updatePath();
}
