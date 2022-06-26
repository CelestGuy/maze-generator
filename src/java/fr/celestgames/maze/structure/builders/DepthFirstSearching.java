package fr.celestgames.maze.structure.builders;

import fr.celestgames.maze.structure.MazeParts;
import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.Point;

import java.util.ArrayList;

public class DepthFirstSearching extends Builder {
    public ArrayList<Point> path;
    public boolean[][] boolTab;

    public DepthFirstSearching(Maze maze) {
        super(maze);

        this.path = new ArrayList<>();
        int boolTabHeight = (this.maze.getHeight() - 1) / 2;
        int boolTabWidth = (this.maze.getWidth() - 1) / 2;

        this.boolTab = new boolean[boolTabHeight][boolTabWidth];

        for (int h = 0; h < boolTabHeight; h++) {
            for (int w = 0; w < boolTabWidth; w++) {
                boolTab[h][w] = false;
            }
        }
    }

    @Override
    public void initPath() {
        boolTab[0][0] = true;
        oldPoint = new Point(1, 1);
        maze.cell[1][1] = MazeParts.PATH;
    }

    @Override
    public void createPath() {
        if (checkBoolTab()) {
            ArrayList<Point> availableCell = getNeighbours(oldPoint);

            if (availableCell.size() > 0) {
                int randCellIndex = rand.nextInt(availableCell.size());

                int oldX = oldPoint.x;
                int oldY = oldPoint.y;
                int x = availableCell.get(randCellIndex).x;
                int y = availableCell.get(randCellIndex).y;

                boolTab[y][x] = true;
                maze.cell[(oldY * 2 + 1) + (y - oldY)][(oldX * 2 + 1) + (x - oldX)] = MazeParts.PATH;
                maze.cell[y * 2 + 1][x * 2 + 1] = MazeParts.PATH;

                oldPoint.x = x;
                oldPoint.y = y;
                path.add(new Point(x, y));
            } else {
                int x = oldPoint.x;
                int y = oldPoint.y;

                maze.cell[y * 2 + 1][x * 2 + 1] = MazeParts.PATH;

                path.remove(path.size() - 1);

                oldPoint.x = path.get(path.size() - 1).x;
                oldPoint.y = path.get(path.size() - 1).y;
            }
        }
    }

    public boolean checkBoolTab() {
        for (int i = 0; i < (maze.getHeight() - 1) / 2; i++) {
            for (int j = 0; j < (maze.getWidth() - 1) / 2; j++) {
                if (!boolTab[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Point> getNeighbours(Point p) {
        ArrayList<Point> points = new ArrayList<>();

        int boolTabHeight = (maze.getHeight() - 1) / 2;
        int boolTabWidth = (maze.getWidth() - 1) / 2;

        if (p.x > 0 && !boolTab[p.y][p.x - 1]) {
            points.add(new Point(p.x - 1, p.y));
        }
        if (p.x < (boolTabWidth - 1) && !boolTab[p.y][p.x + 1]) {
            points.add(new Point(p.x + 1, p.y));
        }

        if (p.y > 0 && !boolTab[p.y - 1][p.x]) {
            points.add(new Point(p.x, p.y - 1));
        }
        if (p.y < (boolTabHeight - 1) && !boolTab[p.y + 1][p.x]) {
            points.add(new Point(p.x, p.y + 1));
        }

        return points;
    }
}