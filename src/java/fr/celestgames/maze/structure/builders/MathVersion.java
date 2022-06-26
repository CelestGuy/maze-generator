package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.enums.MazeParts;
import fr.celestgames.maze.game.Game;
import fr.celestgames.maze.game.Maze;

import java.util.ArrayList;

public class MathVersion extends Builder {
    public int[][] numTab;
    public int numTabHeight;
    public int numTabWidth;

    public MathVersion(Game game, Maze maze) {
        super(game, maze);

        this.numTabHeight = (this.maze.height - 1) / 2;
        this.numTabWidth = (this.maze.width - 1) / 2;

        this.numTab = new int[this.numTabHeight][this.numTabWidth];

        for (int h = 0; h < this.numTabHeight; h++) {
            for (int w = 0; w < this.numTabWidth; w++) {
                numTab[h][w] = w + (h * this.numTabHeight);
            }
        }
    }

    @Override
    public void createPath() {
        for (int i = 0; i < this.maze.height; i++) {
            for (int j = 0; j < this.maze.width; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    this.maze.cell[i][j] = MazeParts.PATH;
                } else {
                    this.maze.cell[i][j] = MazeParts.WALL;
                }
            }
        }
    }

    @Override
    public void updatePath() {
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

            x = rdmPoint.x;
            y = rdmPoint.y;

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

        for (int i = 0; i < maze.height; i++) {
            for (int j = 0; j < maze.width; j++) {
                if (maze.cell[i][j] == MazeParts.PATH || maze.cell[i][j] == MazeParts.CROSS) {
                    int count = 0;

                    if (maze.cell[i + 1][j] == MazeParts.PATH || maze.cell[i + 1][j] == MazeParts.CROSS)
                        count++;
                    if (maze.cell[i - 1][j] == MazeParts.PATH || maze.cell[i - 1][j] == MazeParts.CROSS)
                        count++;
                    if (maze.cell[i][j + 1] == MazeParts.PATH || maze.cell[i][j + 1] == MazeParts.CROSS)
                        count++;
                    if (maze.cell[i][j - 1] == MazeParts.PATH || maze.cell[i][j - 1] == MazeParts.CROSS)
                        count++;

                    if (count > 2) {
                        maze.cell[i][j] = MazeParts.CROSS;
                    }
                }
            }
        }
    }
}