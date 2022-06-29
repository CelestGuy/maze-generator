package fr.celestgames.maze_generator.maze;

import java.util.ArrayList;

public class Cell {
    private Cell north;
    private Cell south;
    private Cell west;
    private Cell east;

    private final int x;
    private final int y;

    private int count;

    private int heuristic;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell getNorth() {
        return north;
    }

    public Cell getSouth() {
        return south;
    }

    public Cell getWest() {
        return west;
    }

    public Cell getEast() {
        return east;
    }

    public void setNorth(Cell north) {
        this.north = north;
    }

    public void setSouth(Cell south) {
        this.south = south;
    }

    public void setWest(Cell west) {
        this.west = west;
    }

    public void setEast(Cell east) {
        this.east = east;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Cell> getNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        if (north != null) {
            neighbors.add(north);
        }
        if (south != null) {
            neighbors.add(south);
        }
        if (west != null) {
            neighbors.add(west);
        }
        if (east != null) {
            neighbors.add(east);
        }
        return neighbors;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
