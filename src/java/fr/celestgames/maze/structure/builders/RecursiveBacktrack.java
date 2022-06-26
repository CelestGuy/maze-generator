package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.enums.MazeParts;
import fr.celestgames.maze.game.Game;
import fr.celestgames.maze.game.Maze;

import java.util.ArrayList;

public class RecursiveBacktrack extends Builder {
    public ArrayList<Point> path;

    public boolean[][] boolTab;
    public int boolTabHeight;
    public int boolTabWidth;

    public RecursiveBacktrack(Game game, Maze maze) {
        super(game, maze);

        this.path = new ArrayList<>();
        this.boolTabHeight = (this.maze.height - 1) / 2;
        this.boolTabWidth = (this.maze.width - 1) / 2;

        this.boolTab = new boolean[this.boolTabHeight][this.boolTabWidth];

        for (int h = 0; h < this.boolTabHeight; h++) {
            for (int w = 0; w < this.boolTabWidth; w++) {
                boolTab[h][w] = false;
            }
        }
    }

    @Override
    public void createPath() {
        boolTab[y][x] = true;
        maze.cell[1][1] = MazeParts.PATH;
    }

    @Override
    public void updatePath() {
        if (checkBoolTab()) {
            ArrayList<Point> availableCell = new ArrayList<>();

            if (x < boolTabWidth - 1 && !boolTab[y][x + 1]) {
                availableCell.add(new Point(x + 1, y));
            }
            if (x > 0 && !boolTab[y][x - 1]) {
                availableCell.add(new Point(x - 1, y));
            }
            if (y < boolTabHeight - 1 && !boolTab[y + 1][x]) {
                availableCell.add(new Point(x, y + 1));
            }
            if (y > 0 && !boolTab[y - 1][x]) {
                availableCell.add(new Point(x, y - 1));
            }

            if (availableCell.size() > 0) {
                int randCellIndex = rand.nextInt(availableCell.size());
                oldX = x;
                oldY = y;
                x = availableCell.get(randCellIndex).x;
                y = availableCell.get(randCellIndex).y;

                boolTab[y][x] = true;
                maze.cell[(oldY * 2 + 1) + (y - oldY)][(oldX * 2 + 1) + (x - oldX)] = MazeParts.PATH;
                maze.cell[y * 2 + 1][x * 2 + 1] = MazeParts.PATH;

                path.add(new Point(x, y));
            } else {
                maze.cell[y * 2 + 1][x * 2 + 1] = MazeParts.PATH;
                x = path.get(path.size() - 1).x;
                y = path.get(path.size() - 1).y;

                path.remove(path.size() - 1);
            }
        }
        /*
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
        }*/
    }

    public boolean checkBoolTab() {
        for (int i = 0; i < boolTabHeight; i++) {
            for (int j = 0; j < boolTabWidth; j++) {
                if (!boolTab[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}