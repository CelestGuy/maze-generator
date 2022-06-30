package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

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
    public void update() {
        if (lastCell != null) {
            int lastX = lastCell.getX();
            int lastY = lastCell.getY();

            HashSet<Cell> neighbours = new HashSet<>();

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
                Cell cell = (Cell) (neighbours.toArray()[rand.nextInt(neighbours.size())]);

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

    private Cell getNextCell() {
        HashSet<Cell> cells = new HashSet<>();
        HashSet<Integer> knownValues = new HashSet<>();

        for (int h = 0; h < super.maze.getHeight(); h++) {
            for (int w = 0; w < super.maze.getWidth(); w++) {
                if (!knownValues.contains(cellValues[h][w])) {
                    knownValues.add(cellValues[h][w]);
                    cells.add(super.maze.getCell(w, h));
                }
            }
        }

        if (cells.size() > 1) {
            return (Cell) (cells.toArray()[rand.nextInt(cells.size())]);
        } else {
            return null;
        }
    }

    @Override
    public boolean isBuilt() {
        lastCell = getNextCell();
        return lastCell == null;
    }

    @Override
    public void render(Graphics2D g2, int cellSize) {
        int c2 = cellSize / 2;
        int c4 = cellSize / 4;
        int c3 = c4 + c2;

        for (int h = 0; h < cellValues.length; h++) {
            for (int w = 0; w < cellValues[h].length; w++) {
                int value = cellValues[h][w] % 255;
                int r = value / 16;
                int g = value % 16 / 4;
                int b = value % 16 % 4;

                if (value % 2 == 0) {
                    r = 100;
                    g = (100 + value) % 255;
                    b = 255;
                } else if (value % 3 == 0) {
                    r = (100 + value) % 255;
                    g = 255;
                    b = 100;
                } else if (value % 5 == 0) {
                    r = 255;
                    g = 100;
                    b = (100 + value) % 255;
                }

                Color color = new Color(r, g, b, 150);
                g2.setColor(color);
                if (maze.getCell(w, h).getNorth() != null || maze.getCell(w, h).getSouth() != null || maze.getCell(w, h).getEast() != null || maze.getCell(w, h).getWest() != null) {
                    if (maze.getCell(w, h).getNorth() != null) {
                        g2.fillRect(w * cellSize + c4, h * cellSize, c2, c4);
                    }
                    if (maze.getCell(w, h).getSouth() != null) {
                        g2.fillRect(w * cellSize + c4, h * cellSize + c3, c2, c4);
                    }
                    if (maze.getCell(w, h).getEast() != null) {
                        g2.fillRect(w * cellSize + c3, h * cellSize + c4, c4, c2);
                    }
                    if (maze.getCell(w, h).getWest() != null) {
                        g2.fillRect(w * cellSize, h * cellSize + c4, c4, c2);
                    }
                    g2.fillRect(w * cellSize + c4, h * cellSize + c4, c2, c2);
                }
            }
        }

        if (lastCell != null) {
            g2.drawImage(CellTiles.MARKER, lastCell.getX() * cellSize, lastCell.getY() * cellSize, cellSize, cellSize, null);
        }
    }
}
