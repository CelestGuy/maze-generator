package fr.celestgames.maze_generator.ui;

import fr.celestgames.maze_generator.io.Window;
import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.solvers.AStar;
import fr.celestgames.maze_generator.maze.solvers.BellmanFord;
import fr.celestgames.maze_generator.maze.solvers.Solver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Scanner;

public class PathFindingScene implements Scene {
    private Maze maze;
    private Solver solver;
    private final Window window;

    private boolean debug;
    private boolean editMode = false;

    private int solverClockTime;

    private static final int CELL_SIZE = 16;

    public PathFindingScene(Window window) {
        this.window = window;
        this.debug = true;
        this.solverClockTime = 10;
    }

    @Override
    public void update() {
        if (!solver.isSolved() && solver.isSolving()) {
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
        } else {
            Scanner scanner = new Scanner(System.in);
            if (!editMode) {
                System.out.print("Enter a command (help for help): ");
                String line = scanner.nextLine().trim().toLowerCase();
                if (line.equals("e")) {
                    System.out.println("-- Edit Mode --");
                    editMode = true;
                } else if (line.equals("f")) {
                    System.out.println("-- Fill Mode --");

                    System.out.print("Enter a cell to start from (x, y): ");
                    String[] start = scanner.nextLine().split(",");
                    System.out.print("Enter a cell to end at (x, y): ");
                    String[] end = scanner.nextLine().split(",");

                    int startX = Integer.parseInt(start[0]);
                    int startY = Integer.parseInt(start[1]);
                    int endX = Integer.parseInt(end[0]);
                    int endY = Integer.parseInt(end[1]);

                    for (int y = startY; y <= endY; y++) {
                        for (int x = startX; x <= endX; x++) {
                            maze.setVoidCell(x, y);
                        }
                    }
                } else if (line.equals("p")) {
                    System.out.println("-- Path Mode --");
                    System.out.print("Enter a cell to start from (x, y): ");
                    String[] start = scanner.nextLine().split(",");
                    solver.setDeparture(maze.getCell(Integer.parseInt(start[0]), Integer.parseInt(start[1])));

                    System.out.print("Enter a cell to end at (x, y): ");
                    String[] end = scanner.nextLine().split(",");
                    solver.setArrival(maze.getCell(Integer.parseInt(end[0]), Integer.parseInt(end[1])));
                } else if (line.equals("c")) {
                    System.out.println("-- Change Mode --");
                    System.out.println("Enter an algo to change : ");
                    String l = scanner.nextLine().trim().toLowerCase();

                    if (l.equals("bf")) {
                        solver = new BellmanFord(maze);
                    } else if (l.equals("a")) {
                        solver = new AStar(maze);
                    }
                }
            } else {
                System.out.print("Enter a cell (x, y) or q: ");
                String line = scanner.nextLine();

                if (line.trim().equalsIgnoreCase("q")) {
                    editMode = false;
                } else {
                    String[] start = line.split(",");
                    maze.setVoidCell(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
                }
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
            solver.render(g2, CELL_SIZE);

            if (!window.getTitle().contains("- Debug")) {
                window.setTitle(window.getTitle() + " - Debug");
            }
        } else {
            window.setTitle(window.getTitle().replace(" - Debug", ""));
        }

        if (!solver.getPath().isEmpty()) {
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

        if (solver.getArrival() != null && solver.getDeparture() != null) {
            g2.drawImage(CellTiles.ARRIVAL, solver.getArrival().getX() * CELL_SIZE, solver.getArrival().getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            g2.drawImage(CellTiles.DEPARTURE, solver.getDeparture().getX() * CELL_SIZE, solver.getDeparture().getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
        }

        g2.dispose();
        image.flush();
        return image;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setSolverClockTime(int solverClockTime) {
        this.solverClockTime = solverClockTime;
    }

    public Solver getSolver() {
        return solver;
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }
}
