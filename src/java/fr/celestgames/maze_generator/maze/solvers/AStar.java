package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar extends Solver {
    HashSet<Cell> closed = new HashSet<>();
    PriorityQueue<Cell> open = new PriorityQueue<>(Comparator.comparingInt(Cell::getHeuristic));
    HashMap<Cell, Double> distances = new HashMap<>();
    HashMap<Cell, Cell> parents = new HashMap<>();


    public AStar(Maze maze) {
        super(maze);
    }

    @Override
    public void update() {
        if (open.isEmpty() || (arrival == null && departure == null)) {
            solvable = false;
            solving = false;
        } else {
            Cell current = open.poll();
            closed.add(current);

            for (Cell neighbor : current.getNeighbors()) {
                if (!closed.contains(neighbor)) {
                    double newDistance = distances.get(current) + 1;

                    if (distances.get(neighbor) > newDistance) {
                        neighbor.setHeuristic((int) (newDistance + neighbor.getDistance(arrival)));
                        distances.put(neighbor, newDistance);
                        parents.put(neighbor, current);
                        open.add(neighbor);
                    }
                }

                if (neighbor == arrival) {
                    solved = true;
                    solving = false;
                    return;
                }
            }
        }
    }

    @Override
    public void path() {
        if ((departure != null && arrival != null) && solved) {
            Cell current = arrival;
            path.add(current);

            while (current != departure) {
                current = parents.get(current);
                path.add(current);
            }

            path.add(departure);
        }
    }

    @Override
    public void setDeparture(Cell departure) {
        super.departure = departure;
        closed = new HashSet<>();
        open = new PriorityQueue<>(Comparator.comparingInt(Cell::getHeuristic));
        path = new HashSet<>();
        distances = new HashMap<>();

        solved = false;
        solving = true;

        open.add(departure);
        closed.add(departure);
        distances.put(departure, 0.0);

        for (Cell cell : maze.getCells()) {
            if (cell != departure) {
                distances.put(cell, Double.POSITIVE_INFINITY);
            }
        }

    }

    @Override
    public void setArrival(Cell arrival) {
        super.arrival = arrival;
    }

    @Override
    public void render(Graphics2D g2, int cellSize) {
        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                if (closed.contains(maze.getCell(w, h))) {
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
