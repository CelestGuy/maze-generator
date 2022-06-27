package fr.celestgames.maze_generator.maze.solvers;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra extends Solver {
    public final ArrayList<Cell> marked;
    private final HashMap<Cell, Integer> distances = new HashMap<>();
    public Dijkstra(Maze maze) {
        super(maze);
        marked = new ArrayList<>();
    }

    @Override
    public void solve() {
        solving = true;
        if ((departure != null && arrival != null) && !solved) {
            ArrayDeque<Cell> queue = new ArrayDeque<>();

            queue.push(departure);
            distances.put(departure, 0);

            for (Cell cell : maze.getCells()) {
                if (cell != departure) {
                    distances.put(cell, Integer.MAX_VALUE);
                }
            }

            while (!queue.isEmpty()) {
                if (clockTime > 0) {
                    try {
                        Thread.sleep(clockTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Cell current = queue.poll();
                marked.add(current);

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
                            return;
                        }
                    }
                }
            }

            solved = true;
        } else {
            System.out.println("Departure or arrival is null");
        }
    }

    @Override
    public void path() {
        if ((departure != null && arrival != null) && solved) {
            Cell current = arrival;
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
}
