package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.util.ArrayList;
import java.util.Random;

public class DepthFirstSearch extends Builder {

    public ArrayList<Cell> path;
    public boolean[][] boolTab;

    public DepthFirstSearch(Maze maze) {
        super(maze);
        this.path = new ArrayList<>();
        this.boolTab = new boolean[this.maze.getHeight()][this.maze.getWidth()];
    }

    @Override
    public void createPath() {
        Random rand = new Random();
        boolTab[0][0] = true;
        lastCell = maze.cells[0][0];

        while (checkBoolTab()) {
            if (clockTime > 0) {
                try {
                    Thread.sleep(clockTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ArrayList<Cell> availableCell = getNeighbours(lastCell);

            if (availableCell.size() > 0) {
                Cell cell = availableCell.get(rand.nextInt(availableCell.size()));

                int lastX = lastCell.getX();
                int lastY = lastCell.getY();
                int x = cell.getX();
                int y = cell.getY();

                boolTab[y][x] = true;
                if (y - lastY == 1) {
                    maze.cells[lastY][lastX].setSouth(cell);
                    maze.cells[y][x].setNorth(lastCell);
                } else if (y - lastY == -1) {
                    maze.cells[lastY][lastX].setNorth(cell);
                    maze.cells[y][x].setSouth(lastCell);
                } else if (x - lastX == 1) {
                    maze.cells[lastY][lastX].setEast(cell);
                    maze.cells[y][x].setWest(lastCell);
                } else if (x - lastX == -1) {
                    maze.cells[lastY][lastX].setWest(cell);
                    maze.cells[y][x].setEast(lastCell);
                }

                lastCell = maze.cells[y][x];
                path.add(maze.cells[y][x]);
            } else {
                lastCell = path.remove(path.size() - 1);
            }
        }
    }

    private boolean checkBoolTab() {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (!boolTab[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Cell> getNeighbours(Cell cell) {
        ArrayList<Cell> points = new ArrayList<>();

        int x = cell.getX();
        int y = cell.getY();

        if (x > 0 && !boolTab[y][x - 1]) {
            points.add(maze.cells[y][x - 1]);
        }
        if (x < (maze.getWidth() - 1) && !boolTab[y][x + 1]) {
            points.add(maze.cells[y][x + 1]);
        }

        if (y > 0 && !boolTab[y - 1][x]) {
            points.add(maze.cells[y - 1][x]);
        }
        if (y < (maze.getHeight() - 1) && !boolTab[y + 1][x]) {
            points.add(maze.cells[y + 1][x]);
        }

        return points;
    }
}
