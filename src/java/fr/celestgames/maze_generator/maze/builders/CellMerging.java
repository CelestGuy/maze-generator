package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CellMerging extends Builder {
    private final int[][] cellValues;
    private ArrayList<Cell> cellsToMerge;

    public CellMerging(Maze maze) {
        super(maze);

        this.cellValues = new int[super.maze.getHeight()][super.maze.getWidth()];
        this.cellsToMerge = new ArrayList<>();

        for (int h = 0; h < super.maze.getHeight(); h++) {
            for (int w = 0; w < super.maze.getWidth(); w++) {
                cellValues[h][w] = w + (h * super.maze.getWidth());
            }
        }
    }

    @Override
    void buildMaze() {
        lastCell = cellsToMerge.get(rand.nextInt(cellsToMerge.size()));
        if (lastCell != null) {
            int lastX = lastCell.getX();
            int lastY = lastCell.getY();

            ArrayList<Cell> neighbours = new ArrayList<>();

            if (lastX < super.maze.getWidth() - 1 && cellValues[lastY][lastX + 1] != cellValues[lastY][lastX]) {
                neighbours.add(super.maze.getCell(lastX + 1, lastY));
            }
            if (lastX > 0 && cellValues[lastY][lastX - 1] != cellValues[lastY][lastX]) {
                neighbours.add(super.maze.getCell(lastX - 1, lastY));
            }
            if (lastY < super.maze.getHeight() - 1 && cellValues[lastY + 1][lastX] != cellValues[lastY][lastX]) {
                neighbours.add(super.maze.getCell(lastX, lastY + 1));
            }
            if (lastY > 0 && cellValues[lastY - 1][lastX] != cellValues[lastY][lastX]) {
                neighbours.add(super.maze.getCell(lastX, lastY - 1));
            }

            if (neighbours.size() > 0) {
                Cell cell = neighbours.get(rand.nextInt(neighbours.size()));

                int x = cell.getX();
                int y = cell.getY();

                int newValue = cellValues[lastY][lastX];
                int oldValue = cellValues[lastY + (y - lastY)][lastX + (x - lastX)];

                if (y - lastY == 1) {
                    maze.getCell(lastX, lastY).setSouth(cell);
                    maze.getCell(x, y).setNorth(lastCell);
                } else if (y - lastY == -1) {
                    maze.getCell(lastX, lastY).setNorth(cell);
                    maze.getCell(x, y).setSouth(lastCell);
                } else if (x - lastX == 1) {
                    maze.getCell(lastX, lastY).setEast(cell);
                    maze.getCell(x, y).setWest(lastCell);
                } else if (x - lastX == -1) {
                    maze.getCell(lastX, lastY).setWest(cell);
                    maze.getCell(x, y).setEast(lastCell);
                }

                for (int h = 0; h < super.maze.getHeight(); h++) {
                    for (int w = 0; w < super.maze.getWidth(); w++) {
                        if (cellValues[h][w] == oldValue) {
                            cellValues[h][w] = newValue;
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Cell> getAvailableCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        int lastValue = cellValues[0][0];

        for (int h = 0; h < super.maze.getHeight(); h++) {
            for (int w = 0; w < super.maze.getWidth(); w++) {
                if (cellValues[h][w] != lastValue) {
                    cells.add(super.maze.getCell(w, h));
                    lastValue = cellValues[h][w];
                }
            }
        }

        return cells;
    }

    @Override
    boolean isBuilt() {
        cellsToMerge = getAvailableCells();
        return cellsToMerge.isEmpty();
    }

    @Override
    public BufferedImage render() {
        return maze.render();
    }
}
