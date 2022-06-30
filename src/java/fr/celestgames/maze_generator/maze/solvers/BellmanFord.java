package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BellmanFord extends Solver {
    public HashSet<Cell> marked;
    private HashSet<Cell> cells;
    private HashMap<Cell, Double> dist;

    private HashMap<Cell, Cell> pred;
    private int index = 0;

    public BellmanFord(Maze maze) {
        super(maze);
        marked = new HashSet<>();
        cells = new HashSet<>();
        dist = new HashMap<>();
        pred = new HashMap<>();

        for (Cell cell : maze.getCells()) {
            cells.add(cell);
            dist.put(cell, Double.POSITIVE_INFINITY);
            pred.put(cell, null);
        }
    }


    @Override
    public void update() {
        if ((super.departure != null && super.arrival != null) && !solved && solvable) {
            if (index < cells.size() - 1) {
                for (Cell cell : cells) {
                    for (Cell neighbor : cell.getNeighbors()) {
                        if (neighbor != null) {
                            if (marked.contains(arrival)) {
                                solved = true;
                                return;
                            } else {
                                double newDist = dist.get(cell) + 1;
                                double oldDist = dist.get(neighbor);
                                if (newDist < oldDist) {
                                    dist.replace(neighbor, newDist);
                                    pred.replace(neighbor, cell);
                                    marked.add(neighbor);
                                }
                            }
                        }
                    }
                }
            }

            index++;
        } else {
            if (departure == null) {
                System.out.println("La cellule d'arrivée n'a pas été définie...");
            } else if (arrival == null) {
                System.out.println("La cellule de départ n'a pas été définie...");
            }

            solving = false;
            solved = false;
        }
    }

    @Override
    public void path() {
        Cell dernierSommet = arrival;

        while (dernierSommet != null) {
            path.add(dernierSommet);
            dernierSommet = pred.get(dernierSommet);
        }
    }

    @Override
    public void setDeparture(Cell departure) {
        super.departure = departure;
        marked = new HashSet<>();
        cells = new HashSet<>();
        dist = new HashMap<>();
        pred = new HashMap<>();

        this.solved = false;
        this.solving = true;

        for (Cell cell : maze.getCells()) {
            cells.add(cell);
            dist.put(cell, Double.POSITIVE_INFINITY);
            pred.put(cell, null);
        }

        dist.put(super.departure, 0.0);
    }

    @Override
    public void setArrival(Cell arrival) {
        super.arrival = arrival;
    }

    @Override
    public void render(Graphics2D g2, int cellSize) {
        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                if (marked.contains(maze.getCell(w, h))) {
                    if (maze.getCell(w, h).getNorth() != null || maze.getCell(w, h).getSouth() != null || maze.getCell(w, h).getEast() != null || maze.getCell(w, h).getWest() != null) {
                        if (maze.getCell(w, h).getNorth() != null) {
                            g2.drawImage(CellTiles.MARKED_NORTH, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        if (maze.getCell(w, h).getSouth() != null) {
                            g2.drawImage(CellTiles.MARKED_SOUTH, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        if (maze.getCell(w, h).getEast() != null) {
                            g2.drawImage(CellTiles.MARKED_EAST, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        if (maze.getCell(w, h).getWest() != null) {
                            g2.drawImage(CellTiles.MARKED_WEST, w * cellSize, h * cellSize, cellSize, cellSize, null);
                        }
                        g2.drawImage(CellTiles.MARKED_CENTER, w * cellSize, h * cellSize, cellSize, cellSize, null);
                    }
                }
            }
        }
    }
}
