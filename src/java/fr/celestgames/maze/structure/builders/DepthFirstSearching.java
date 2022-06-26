package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.structure.MazeParts;
import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.Point;

import java.util.ArrayList;

public class DepthFirstSearching extends Builder {
    public ArrayList<Point> path;
    private Point oldPoint;
    public boolean[][] boolTab;
    public int boolTabHeight;
    public int boolTabWidth;

    public DepthFirstSearching(Maze maze) {
        super(maze);

        this.path = new ArrayList<>();
        this.boolTabHeight = (this.maze.getHeight() - 1) / 2;
        this.boolTabWidth = (this.maze.getWidth() - 1) / 2;

        this.boolTab = new boolean[this.boolTabHeight][this.boolTabWidth];

        for (int h = 0; h < this.boolTabHeight; h++) {
            for (int w = 0; w < this.boolTabWidth; w++) {
                boolTab[h][w] = false;
            }
        }
    }

    @Override
    public void initPath() {
        oldPoint = new Point(0, 0);
        boolTab[0][0] = true;
        maze.cell[1][1] = MazeParts.PATH;
    }

    @Override
    public void createPath() {
        if (checkBoolTab()) {
            ArrayList<Point> availableCell = maze.getNeighbours(oldPoint);

            if (availableCell.size() > 0) {
                int randCellIndex = rand.nextInt(availableCell.size());

                int oldX = oldPoint.x;
                int oldY = oldPoint.y;
                int x = availableCell.get(randCellIndex).x;
                int y = availableCell.get(randCellIndex).y;

                boolTab[y][x] = true;
                maze.cell[(oldY * 2 + 1) + (y - oldY)][(oldX * 2 + 1) + (x - oldX)] = MazeParts.PATH;
                maze.cell[y * 2 + 1][x * 2 + 1] = MazeParts.PATH;

                path.add(new Point(x, y));
            } else {
                int x = oldPoint.x;
                int y = oldPoint.y;

                maze.cell[y * 2 + 1][x * 2 + 1] = MazeParts.PATH;

                path.remove(path.size() - 1);
            }
        }
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