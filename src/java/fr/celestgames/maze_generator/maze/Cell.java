package fr.celestgames.maze_generator.maze;

import java.util.ArrayList;
import java.util.HashSet;

public class Cell {
    private Cell north;
    private Cell south;
    private Cell west;
    private Cell east;
    private Cell north_east;
    private Cell north_west;
    private Cell south_east;
    private Cell south_west;

    private final int x;
    private final int y;

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

    public Cell getNorthEast() {
        return north_east;
    }

    public Cell getNorthWest() {
        return north_west;
    }

    public Cell getSouthEast() {
        return south_east;
    }

    public Cell getSouthWest() {
        return south_west;
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

    public void setNorthEast(Cell north_east) {
        this.north_east = north_east;
    }

    public void setNorthWest(Cell north_west) {
        this.north_west = north_west;
    }

    public void setSouthEast(Cell south_east) {
        this.south_east = south_east;
    }

    public void setSouthWest(Cell south_west) {
        this.south_west = south_west;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public HashSet<Cell> getNeighbors() {
        HashSet<Cell> neighbors = new HashSet<>();
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
        if (north_east != null) {
            neighbors.add(north_east);
        }
        if (north_west != null) {
            neighbors.add(north_west);
        }
        if (south_east != null) {
            neighbors.add(south_east);
        }
        if (south_west != null) {
            neighbors.add(south_west);
        }

        return neighbors;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getDistance(Cell cell) {
        return Math.abs(x - cell.getX()) + Math.abs(y - cell.getY());
    }
}
