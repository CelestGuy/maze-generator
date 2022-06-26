package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.structure.MazeParts;
import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.Point;

import java.util.ArrayList;

public class MathVersion extends Builder {
    public int[][] numTab;
    public int numTabHeight;
    public int numTabWidth;

    public MathVersion(Maze maze) {
        super(maze);

        this.numTabHeight = (this.maze.getHeight() - 1) / 2;
        this.numTabWidth = (this.maze.getWidth() - 1) / 2;

        this.numTab = new int[this.numTabHeight][this.numTabWidth];

        for (int h = 0; h < this.numTabHeight; h++) {
            for (int w = 0; w < this.numTabWidth; w++) {
                numTab[h][w] = w + (h * this.numTabHeight);
            }
        }
    }

    @Override
    public void initPath() {
        for (int i = 0; i < this.maze.getHeight(); i++) {
            for (int j = 0; j < this.maze.getWidth(); j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    this.maze.cell[i][j] = MazeParts.PATH;
                } else {
                    this.maze.cell[i][j] = MazeParts.WALL;
                }
            }
        }
    }

    @Override
    public void createPath() {
        ArrayList<Point> Cell = new ArrayList<>();

        int lastNum = numTab[0][0];

        for (int i = 0; i < numTabHeight; i++) {
            for (int j = 0; j < numTabWidth; j++) {
                if (lastNum != numTab[i][j]) {
                    Cell.add(new Point(j, i));
                }
                lastNum = numTab[i][j];
            }
        }

        if (Cell.size() > 0) {
            Point rdmPoint = Cell.get(rand.nextInt(Cell.size()));

            int x = rdmPoint.x;
            int y = rdmPoint.y;

            ArrayList<Point> availableCell = new ArrayList<>();

            if (x < numTabWidth - 1 && numTab[y][x + 1] != numTab[y][x]) {
                availableCell.add(new Point(x + 1, y));
            }
            if (x > 0 && numTab[y][x - 1] != numTab[y][x]) {
                availableCell.add(new Point(x - 1, y));
            }
            if (y < numTabHeight - 1 && numTab[y + 1][x] != numTab[y][x]) {
                availableCell.add(new Point(x, y + 1));
            }
            if (y > 0 && numTab[y - 1][x] != numTab[y][x]) {
                availableCell.add(new Point(x, y - 1));
            }

            if (availableCell.size() > 0) {
                Point p = availableCell.get(rand.nextInt(availableCell.size()));

                int newValue = numTab[y][x];
                int oldValue = numTab[y + (p.y - y)][x + (p.x - x)];

                for (int h = 0; h < numTabHeight; h++) {
                    for (int w = 0; w < numTabWidth; w++) {
                        if (numTab[h][w] == oldValue) {
                            numTab[h][w] = newValue;
                        }
                    }
                }

                maze.cell[y * 2 + 1 + (p.y - y)][x * 2 + 1 + (p.x - x)] = MazeParts.PATH;
            }
        }
    }
}