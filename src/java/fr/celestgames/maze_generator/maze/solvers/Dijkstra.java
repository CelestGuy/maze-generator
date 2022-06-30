package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.HashMap;

public class Dijkstra extends Solver {
    public HashSet<Cell> marked;
    private HashMap<Cell, Integer> distances;
    private ArrayDeque<Cell> queue;

    public Dijkstra(Maze maze) {
        super(maze);
        marked = new HashSet<>();
        distances = new HashMap<>();
        queue = new ArrayDeque<>();
    }

    @Override
    public void update() {
        if ((departure != null && arrival != null) && !solved && solvable) {
            Cell current = queue.poll();
            marked.add(current);

            if (current != null) {
                for (Cell neighbor : current.getNeighbors()) {
                    if (neighbor != null && !marked.contains(neighbor)) {
                        int newDistance = distances.get(current) + 1;
                        int oldDistance = distances.get(neighbor);
                        if (newDistance < oldDistance) {
                            distances.put(neighbor, newDistance);
                            queue.add(neighbor);
                        }
                        if (neighbor == arrival) {
                            solved = true;
                            marked.add(neighbor);
                            return;
                        }
                    }
                }
            } else {
                solvable = false;
                System.out.println("No path found");
            }

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
        if ((departure != null && arrival != null) && solved) {
            Cell current = arrival;
            path.add(current);
            int distance = distances.get(current);

            while (current != departure) {
                for (Cell neighbor : current.getNeighbors()) {
                    if (neighbor != null && distances.get(neighbor) == distance - 1) {
                        path.add(neighbor);
                        current = neighbor;
                        distance--;
                        break;
                    }
                }
            }

            path.add(departure);
        }
    }

    @Override
    public void setDeparture(Cell departure) {
        this.departure = departure;

        this.marked = new HashSet<>();
        this.distances = new HashMap<>();
        this.queue = new ArrayDeque<>();

        this.solved = false;
        this.solving = true;

        queue.push(departure);
        distances.put(departure, 0);

        for (Cell cell : maze.getCells()) {
            if (cell != departure) {
                distances.put(cell, Integer.MAX_VALUE);
            }
        }
    }

    @Override
    public void setArrival(Cell arrival) {
        this.arrival = arrival;
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
