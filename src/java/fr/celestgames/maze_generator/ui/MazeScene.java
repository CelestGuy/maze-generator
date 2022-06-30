package fr.celestgames.maze_generator.ui;

import fr.celestgames.maze_generator.io.Window;
import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.solvers.Solver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class MazeScene implements Scene {
    private Maze maze;
    private Builder builder;
    private Solver solver;
    private final Window window;

    private boolean debug;
    private boolean built;

    private int builderClockTime;
    private int solverClockTime;

    private static final int CELL_SIZE = 16;

    public MazeScene(Window window) {
        this.window = window;
        this.debug = false;
        this.built = false;
        this.builderClockTime = 10;
        this.solverClockTime = 10;
    }

    @Override
    public void update() {
        debug = window.k.tKey;

        if (!built) {
            if (builderClockTime > 0) {
                try {
                    Thread.sleep(builderClockTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            builder.update();
            built = builder.isBuilt();
        } else if (!solver.isSolved() && solver.isSolving()) {
            if (solverClockTime > 0) {
                try {
                    Thread.sleep(solverClockTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            solver.update();
            if (solver.isSolved()) {
                solver.path();
            }
        }
    }

    @Override
    public BufferedImage render() {
        BufferedImage image = new BufferedImage(maze.getWidth() * CELL_SIZE, maze.getHeight() * CELL_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = image.createGraphics();

        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                g2.drawImage(CellTiles.BASE, w * CELL_SIZE, h * CELL_SIZE, null);
                if (maze.getCell(w, h).getNorth() != null || maze.getCell(w, h).getSouth() != null || maze.getCell(w, h).getEast() != null || maze.getCell(w, h).getWest() != null) {
                    if (maze.getCell(w, h).getNorth() != null) {
                        g2.drawImage(CellTiles.NORTH, w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    if (maze.getCell(w, h).getSouth() != null) {
                        g2.drawImage(CellTiles.SOUTH, w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    if (maze.getCell(w, h).getEast() != null) {
                        g2.drawImage(CellTiles.EAST, w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    if (maze.getCell(w, h).getWest() != null) {
                        g2.drawImage(CellTiles.WEST, w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    g2.drawImage(CellTiles.CENTER, w * CELL_SIZE, h * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }

        if (debug) {
            if (!built) {
                builder.render(g2, CELL_SIZE);
            } else {
                solver.render(g2, CELL_SIZE);
            }
            if (!window.getTitle().contains("- Debug")) {
                window.setTitle(window.getTitle() + " - Debug");
            }
        } else {
            window.setTitle(window.getTitle().replace(" - Debug", ""));
        }

        if (solver.isSolved()) {
            HashSet<Cell> path = solver.getPath();
            for (Cell cell : solver.getPath()) {
                int x = cell.getX();
                int y = cell.getY();

                if (cell.getNorth() != null || cell.getSouth() != null || cell.getEast() != null || cell.getWest() != null) {
                    if (path.contains(cell.getNorth())) {
                        g2.drawImage(CellTiles.PATH_NORTH, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    if (path.contains(cell.getSouth())) {
                        g2.drawImage(CellTiles.PATH_SOUTH, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    if (path.contains(cell.getEast())) {
                        g2.drawImage(CellTiles.PATH_EAST, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    if (path.contains(cell.getWest())) {
                        g2.drawImage(CellTiles.PATH_WEST, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                    }
                    g2.drawImage(CellTiles.PATH_CENTER, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }

        if (solver.isSolving() || solver.isSolved()) {
            g2.drawImage(CellTiles.ARRIVAL, solver.getArrival().getX() * CELL_SIZE, solver.getArrival().getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            g2.drawImage(CellTiles.DEPARTURE, solver.getDeparture().getX() * CELL_SIZE, solver.getDeparture().getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
        }

        g2.dispose();
        image.flush();
        return image;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }

    public void setBuilderClockTime(int builderClockTime) {
        this.builderClockTime = builderClockTime;
    }

    public void setSolverClockTime(int solverClockTime) {
        this.solverClockTime = solverClockTime;
    }
}
