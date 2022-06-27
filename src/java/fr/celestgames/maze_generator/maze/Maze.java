package fr.celestgames.maze_generator.maze;

import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {
    private final int width;
    private final int height;
    public final Cell[][] cells;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;

        this.cells = new Cell[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                cells[h][w] = new Cell(w, h);
            }
        }
    }

    public boolean isEqual(Maze mazeToCompare) {
        if (mazeToCompare.getHeight() != height || mazeToCompare.getWidth() != width) {
            return false;
        } else {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    if (this.cells[h][w] != mazeToCompare.cells[h][w]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

    public ArrayList<Cell> getCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                cells.add(this.cells[h][w]);
            }
        }
        return cells;
    }
}