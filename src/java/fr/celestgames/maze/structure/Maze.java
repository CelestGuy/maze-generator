package fr.celestgames.maze.structure;

import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {
    private final int width;
    private final int height;
    public final MazeParts[][] cell;
    private final GenMode genMode;

    public Maze(int height, int width, GenMode genMode) {
        this.height = height;
        this.width = width;

        this.genMode = genMode;

        this.cell = new MazeParts[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                cell[h][w] = MazeParts.WALL;
            }
        }
    }

    public boolean isEqual(Maze mazeToCompare) {
        if (mazeToCompare.getHeight() != height || mazeToCompare.getWidth() != width) {
            return false;
        } else {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    if (this.cell[h][w] != mazeToCompare.cell[h][w]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public GenMode getGenMode() {
        return genMode;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Point> getNeighbours(Point p) {
        ArrayList<Point> points = new ArrayList<>();

        if (p.y >= 0 && p.y < cell.length) {
            if (p.x > 0 && cell[p.y][p.x - 1] == MazeParts.WALL) {
                points.add(new Point(p.x - 1, p.y));
            }
            if (p.x < (width - 1) && cell[p.y][p.x + 1] == MazeParts.WALL) {
                points.add(new Point(p.x + 1, p.y));
            }

            if (p.y > 0 && cell[p.y - 1][p.x] == MazeParts.WALL) {
                points.add(new Point(p.x, p.y -1));
            }
            if (p.y < (height - 1) && cell[p.y + 1][p.x] == MazeParts.WALL) {
                points.add(new Point(p.x, p.y + 1));
            }
        }

        return points;
    }
}