package fr.celestgames.maze_generator.maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {
    private final int width;
    private final int height;
    private final Cell[][] cells;

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

    public Maze(int height, int width, boolean doubleCell) {
        this.height = height;
        this.width = width;

        this.cells = new Cell[height][width];

        if (doubleCell) {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    cells[h][w] = new Cell(w, h);
                }
            }

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    Cell cell = cells[h][w];
                    if (w < width - 1) {
                        cell.setEast(cells[h][w + 1]);
                    }
                    if (h < height - 1) {
                        cell.setSouth(cells[h + 1][w]);
                    }
                    if (w > 0) {
                        cell.setWest(cells[h][w - 1]);
                    }
                    if (h > 0) {
                        cell.setNorth(cells[h - 1][w]);
                    }
                    if (h > 0 && w > 0) {
                        cell.setNorthWest(cells[h - 1][w - 1]);
                    }
                    if (h > 0 && w < width - 1) {
                        cell.setNorthEast(cells[h - 1][w + 1]);
                    }
                    if (h < height - 1 && w > 0) {
                        cell.setSouthWest(cells[h + 1][w - 1]);
                    }
                    if (h < height - 1 && w < width - 1) {
                        cell.setSouthEast(cells[h + 1][w + 1]);
                    }
                }
            }
        } else {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    cells[h][w] = new Cell(w, h);
                }
            }

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    if (w < width - 1) {
                        cells[h][w].setEast(cells[h][w + 1]);
                    }
                    if (h < height - 1) {
                        cells[h][w].setSouth(cells[h + 1][w]);
                    }
                    if (w > 0) {
                        cells[h][w].setWest(cells[h][w - 1]);
                    }
                    if (h > 0) {
                        cells[h][w].setNorth(cells[h - 1][w]);
                    }
                }
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

    public void setVoidCell(int x, int y) {
        cells[y][x] = new Cell(x, y);
        if (x < width - 1) {
            cells[y][x + 1].setWest(null);
        }
        if (y < height - 1) {
            cells[y + 1][x].setNorth(null);
        }
        if (x > 0) {
            cells[y][x - 1].setEast(null);
        }
        if (y > 0) {
            cells[y - 1][x].setSouth(null);
        }
        if (x > 0 && y > 0) {
            cells[y - 1][x - 1].setSouthEast(null);
        }
        if (x > 0 && y < height - 1) {
            cells[y + 1][x - 1].setNorthEast(null);
        }
        if (x < width - 1 && y > 0) {
            cells[y - 1][x + 1].setSouthWest(null);
        }
        if (x < width - 1 && y < height - 1) {
            cells[y + 1][x + 1].setNorthWest(null);
        }
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