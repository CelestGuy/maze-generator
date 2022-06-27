package fr.celestgames.maze_generator.maze;

import java.util.ArrayList;

public class Cell {
    private Cell north;
    private Cell south;
    private Cell west;
    private Cell east;

    private final int x;
    private final int y;

    private CellType type;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        type = CellType.DEFAULT;
    }

    void setType() {
        if (north != null && south != null && west != null && east != null) {
            type = CellType.CROSS;
        } else if (north != null && south != null && west == null && east == null) {
            type = CellType.V_LINE;
        } else if (north == null && south == null && west != null && east != null) {
            type = CellType.H_LINE;
        } else if (north == null && south != null && west != null && east != null) {
            type = CellType.SOUTH_T;
        } else if (north != null && south == null && west != null && east != null) {
            type = CellType.NORTH_T;
        } else if (north != null && south != null && west == null && east != null) {
            type = CellType.EAST_T;
        } else if (north != null && south != null && west != null && east == null) {
            type = CellType.WEST_T;
        } else if (north == null && south != null && west == null && east != null) {
            type = CellType.SE_CORNER;
        } else if (north != null && south == null && west == null && east != null) {
            type = CellType.NE_CORNER;
        } else if (north == null && south != null && west != null && east == null) {
            type = CellType.SW_CORNER;
        } else if (north != null && south == null && west != null && east == null) {
            type = CellType.NW_CORNER;
        } else if (north != null && south == null && west == null && east == null) {
            type = CellType.NORTH;
        } else if (north == null && south != null && west == null && east == null) {
            type = CellType.SOUTH;
        } else if (north == null && south == null && west != null && east == null) {
            type = CellType.WEST;
        } else if (north == null && south == null && west == null && east != null) {
            type = CellType.EAST;
        } else {
            type = CellType.DEFAULT;
        }
    }

    public CellType getType() {
        return this.type;
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
        setType();
    }

    public void setSouth(Cell south) {
        this.south = south;
        setType();
    }

    public void setWest(Cell west) {
        this.west = west;
        setType();
    }

    public void setEast(Cell east) {
        this.east = east;
        setType();
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

    /*
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
     */
}
