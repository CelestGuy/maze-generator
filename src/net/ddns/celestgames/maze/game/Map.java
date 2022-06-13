package net.ddns.celestgames.maze.game;

public class Map {
    public int x, y, width, height;
    private final Maze maze;
    //public Block[][] blocks;

    public Map(Maze maze) {
        this.maze = maze;
    }
}
