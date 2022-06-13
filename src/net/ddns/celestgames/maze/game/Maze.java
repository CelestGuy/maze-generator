package net.ddns.celestgames.maze.game;

import net.ddns.celestgames.maze.enums.GenMode;
import net.ddns.celestgames.maze.enums.MazeParts;

import java.io.Serializable;

public class Maze implements Serializable {
    public int width;
    public int height;
    public MazeParts[][] cell;
    private GenMode genMode;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;

        this.cell = new MazeParts[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                cell[h][w] = MazeParts.WALL;
            }
        }
    }

    public boolean isEqual(Maze mazeToCompare) {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < height; w++) {
                if (this.cell[h][w] != mazeToCompare.cell[h][w]) {
                    return false;
                }
            }
        }
        return true;
    }

    public GenMode getGenMode() {
        return genMode;
    }

    public void setGenMode(GenMode genMode) {
        this.genMode = genMode;
    }
}