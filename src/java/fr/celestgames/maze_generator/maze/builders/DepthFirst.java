package fr.celestgames.maze_generator.maze.builders;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.HashSet;

public class DepthFirst extends Builder {
    private final ArrayDeque<Cell> pile;
    private final HashSet<Cell> visitedCells;
    private final HashSet<Cell> markedCells;

    public DepthFirst(Maze maze) {
        super(maze);
        this.pile = new ArrayDeque<>();
        this.visitedCells = new HashSet<>();
        this.markedCells = new HashSet<>();

        lastCell = maze.getCell(0, 0);
        visitedCells.add(lastCell);
        pile.push(lastCell);
    }

    @Override
    public void update() {
        if (lastCell != null) {
            ArrayList<Cell> neighbors = new ArrayList<>();

            int lastX = lastCell.getX();
            int lastY = lastCell.getY();

            if (lastX > 0 && !visitedCells.contains(maze.getCell(lastX - 1, lastY))) {
                neighbors.add(maze.getCell(lastX - 1, lastY));
            }
            if (lastX < (maze.getWidth() - 1) && !visitedCells.contains(maze.getCell(lastX + 1, lastY))) {
                neighbors.add(maze.getCell(lastX + 1, lastY));
            }
            if (lastY > 0 && !visitedCells.contains(maze.getCell(lastX, lastY - 1))) {
                neighbors.add(maze.getCell(lastX, lastY - 1));
            }
            if (lastY < (maze.getHeight() - 1) && !visitedCells.contains(maze.getCell(lastX, lastY + 1))) {
                neighbors.add(maze.getCell(lastX, lastY + 1));
            }

            if (neighbors.size() > 0) {
                Cell cell = neighbors.get(rand.nextInt(neighbors.size()));

                int x = cell.getX();
                int y = cell.getY();

                if (y - lastY == 1) {
                    lastCell.setSouth(cell);
                    cell.setNorth(lastCell);
                } else if (y - lastY == -1) {
                    lastCell.setNorth(cell);
                    cell.setSouth(lastCell);
                } else if (x - lastX == 1) {
                    lastCell.setEast(cell);
                    cell.setWest(lastCell);
                } else if (x - lastX == -1) {
                    lastCell.setWest(cell);
                    cell.setEast(lastCell);
                }

                lastCell = cell;
                visitedCells.add(cell);
                pile.push(lastCell);
            } else {
                lastCell = pile.pop();
                visitedCells.add(lastCell);
                markedCells.add(lastCell);
            }
        }
    }

    @Override
    public void render(Graphics2D g2, int cellSize) {
        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                if (markedCells.contains(maze.getCell(w, h))) {
                    if (maze.getCell(w, h).getNorth() != null || maze.getCell(w, h).getSouth() != null || maze.getCell(w, h).getEast() != null || maze.getCell(w, h).getWest() != null) {
                        if (maze.getCell(w, h).getNorth() != null) {
                            g2.drawImage(CellTiles.VISITED_NORTH, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        if (maze.getCell(w, h).getSouth() != null) {
                            g2.drawImage(CellTiles.VISITED_SOUTH, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        if (maze.getCell(w, h).getEast() != null) {
                            g2.drawImage(CellTiles.VISITED_EAST, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        if (maze.getCell(w, h).getWest() != null) {
                            g2.drawImage(CellTiles.VISITED_WEST, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        g2.drawImage(CellTiles.VISITED_CENTER, w * cellSize, h * cellSize, cellSize, cellSize, null);
                    }
                }
            }
        }
    }

    @Override
    public boolean isBuilt() {
        return markedCells.size() == maze.getHeight() * maze.getWidth();
    }
}