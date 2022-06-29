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

    public BufferedImage render() {
        BufferedImage image = new BufferedImage(width * 16, height * 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                g2.drawImage(CellTiles.BASE, w * 16, h * 16, null);
                if (cells[h][w].getNorth() != null || cells[h][w].getSouth() != null || cells[h][w].getEast() != null || cells[h][w].getWest() != null) {
                    if (cells[h][w].getNorth() != null) {
                        g2.drawImage(CellTiles.NORTH, w * 16, h * 16, null);
                    }
                    if (cells[h][w].getSouth() != null) {
                        g2.drawImage(CellTiles.SOUTH, w * 16, h * 16, null);
                    }
                    if (cells[h][w].getEast() != null) {
                        g2.drawImage(CellTiles.EAST, w * 16, h * 16, null);
                    }
                    if (cells[h][w].getWest() != null) {
                        g2.drawImage(CellTiles.WEST, w * 16, h * 16, null);
                    }
                    g2.drawImage(CellTiles.CENTER, w * 16, h * 16, null);
                }
            }
        }

        g2.dispose();
        return image;
    }
}