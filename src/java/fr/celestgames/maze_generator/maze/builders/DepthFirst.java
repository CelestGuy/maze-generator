package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class DepthFirst extends Builder {
    private final boolean [][] builtCells;
    private final ArrayDeque<Cell> pile;

    public DepthFirst(Maze maze) {
        super(maze);
        this.builtCells = new boolean[this.maze.getHeight()][this.maze.getWidth()];
        this.pile = new ArrayDeque<>();

        for (int h = 0; h < this.maze.getHeight(); h++) {
            for (int w = 0; w < this.maze.getWidth(); w++) {
                this.builtCells[h][w] = false;
            }
        }

        builtCells[0][0] = true;
        lastCell = maze.getCell(0, 0);
        pile.add(lastCell);
    }

    @Override
    void buildMaze() {
        if (lastCell != null) {
            ArrayList<Cell> neighbors = new ArrayList<>();

            int lastX = lastCell.getX();
            int lastY = lastCell.getY();

            if (lastX > 0 && !builtCells[lastY][lastX - 1]) {
                neighbors.add(maze.getCell(lastX - 1, lastY));
            }
            if (lastX < (maze.getWidth() - 1) && !builtCells[lastY][lastX + 1]) {
                neighbors.add(maze.getCell(lastX + 1, lastY));
            }
            if (lastY > 0 && !builtCells[lastY - 1][lastX]) {
                neighbors.add(maze.getCell(lastX, lastY - 1));
            }
            if (lastY < (maze.getHeight() - 1) && !builtCells[lastY + 1][lastX]) {
                neighbors.add(maze.getCell(lastX, lastY + 1));
            }

            if (neighbors.size() > 0) {
                Cell cell = neighbors.get(rand.nextInt(neighbors.size()));

                int x = cell.getX();
                int y = cell.getY();

                builtCells[y][x] = true;

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

                lastCell = maze.getCell(x, y);
                pile.add(lastCell);
            } else {
                lastCell = pile.removeLast();
            }
        }
    }

    @Override
    public BufferedImage render() {
        return maze.render();
    }

    @Override
    boolean isBuilt() {
        for (boolean [] row : builtCells) {
            for (boolean cell : row) {
                if (!cell) {
                    return false;
                }
            }
        }
        return true;
    }
}