package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.util.ArrayList;
import java.util.Random;

public class CellMerging extends Builder {
    public final int[][] numTab;
    private ArrayList<Cell> cellsToMerge;
    public CellMerging(Maze maze) {
        super(maze);

        this.numTab = new int[super.maze.getHeight()][super.maze.getWidth()];

        for (int h = 0; h < super.maze.getHeight(); h++) {
            for (int w = 0; w < super.maze.getWidth(); w++) {
                numTab[h][w] = w + (h * super.maze.getWidth());
            }
        }
    }

    @Override
    public void createPath() {
        while (checkIntTab()) {
            if (clockTime > 0) {
                try {
                    Thread.sleep(clockTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Random random = new Random();
            lastCell = cellsToMerge.get(random.nextInt(cellsToMerge.size()));

            int lastX = lastCell.getX();
            int lastY = lastCell.getY();

            ArrayList<Cell> neighbours = getNeighbours(lastCell);

            if (neighbours.size() > 0) {
                Cell cell = neighbours.get(random.nextInt(neighbours.size()));

                int x = cell.getX();
                int y = cell.getY();

                int newValue = numTab[lastY][lastX];
                int oldValue = numTab[lastY + (y - lastY)][lastX + (x - lastX)];

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

                for (int h = 0; h < super.maze.getHeight(); h++) {
                    for (int w = 0; w < super.maze.getWidth(); w++) {
                        if (numTab[h][w] == oldValue) {
                            numTab[h][w] = newValue;
                        }
                    }
                }
            }
        }
    }

    private boolean checkIntTab() {
        cellsToMerge = new ArrayList<>();

        int lastValue = numTab[0][0];

        for (int h = 0; h < super.maze.getHeight(); h++) {
            for (int w = 0; w < super.maze.getWidth(); w++) {
                if (numTab[h][w] != lastValue) {
                    cellsToMerge.add(super.maze.getCell(w, h));
                    lastValue = numTab[h][w];
                }
            }
        }

        if (cellsToMerge.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<Cell> getNeighbours(Cell cell) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        int x = cell.getX();
        int y = cell.getY();

        if (x < super.maze.getWidth() - 1 && numTab[y][x + 1] != numTab[y][x]) {
            neighbours.add(super.maze.getCell(x + 1, y));
        }
        if (x > 0 && numTab[y][x - 1] != numTab[y][x]) {
            neighbours.add(super.maze.getCell(x - 1, y));
        }
        if (y < super.maze.getHeight() - 1 && numTab[y + 1][x] != numTab[y][x]) {
            neighbours.add(super.maze.getCell(x, y + 1));
        }
        if (y > 0 && numTab[y - 1][x] != numTab[y][x]) {
            neighbours.add(super.maze.getCell(x, y - 1));
        }


        return neighbours;
    }
}