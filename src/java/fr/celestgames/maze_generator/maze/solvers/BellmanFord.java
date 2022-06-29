package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.util.ArrayList;
import java.util.HashMap;

public class BellmanFord extends Solver {
    public final ArrayList<Cell> marked;
    private final ArrayList<Cell> cells;

    public Cell marker;

    private final HashMap<Cell, Double> dist;

    private final HashMap<Cell, Cell> pred;

    public BellmanFord(Maze maze) {
        super(maze);
        marked = new ArrayList<>();
        cells = new ArrayList<>();
        dist = new HashMap<>();
        pred = new HashMap<>();

        for (Cell cell : maze.getCells()) {
            cells.add(cell);
            dist.put(cell, Double.POSITIVE_INFINITY);
            pred.put(cell, null);
        }
    }

    @Override
    public void solve() {
        if (super.departure != null && super.arrival != null) {
            solving = true;

            dist.put(super.departure, 0.0);
            for (int i = 0; i < cells.size() - 1; i++) {
                if (clockTime > 0) {
                    try {
                        Thread.sleep(clockTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

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

            solved = true;
        }

    }

    @Override
    public void path() {
        Cell dernierSommet = arrival;

        while (dernierSommet != null) {
            if (clockTime > 0) {
                try {
                    Thread.sleep(clockTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            path.add(dernierSommet);
            dernierSommet = pred.get(dernierSommet);
        }
    }
}
