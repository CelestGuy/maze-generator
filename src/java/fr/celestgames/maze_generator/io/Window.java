package fr.celestgames.maze_generator.io;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.builders.Builder;
import fr.celestgames.maze_generator.maze.builders.CellMerging;
import fr.celestgames.maze_generator.maze.solvers.BellmanFord;
import fr.celestgames.maze_generator.maze.solvers.Dijkstra;
import fr.celestgames.maze_generator.maze.solvers.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze_generator.utils.ImageUtils.readImage;

public class Window extends JPanel implements Runnable {
    public final JFrame window = new JFrame();

    private Thread windowThread = new Thread(this);
    private int width;
    private int height;
    private final int scale;
    private final int cameraXPos;
    private final int cameraYPos;

    private boolean grid = false;

    private static final BufferedImage marker = readImage("/assets/textures/cells/marker.png");

    private Maze maze;
    private Solver solver;
    private Builder builder;
    private int tileSize;

    private Keyboard k;
    private Mouse m;

    public Window() {
        window.setTitle("A simple maze generator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        scale = 5;
        height = 480;
        width = 720;
        cameraXPos = 0;
        cameraYPos = 0;

        k = new Keyboard();
        m = new Mouse();

        addMouseListener(m);
        addKeyListener(k);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);

        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setMaze(Maze m) {
        this.maze = m;
    }

    public void setBuilder(Builder b) {
        this.builder = b;
    }

    public void setSolver(Solver s) {
        this.solver = s;
    }


    @Override
    public void run() {
        while (true) {
            update();
            /*try {
                Thread.sleep(8, 333333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public void update() {
        width = window.getWidth();
        height = window.getHeight();
        tileSize = (int) ((int) ((double) height / (double) maze.getHeight()) * 0.9) + 1;

        grid = k.tKey;

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        if (this.maze != null) {
            int mazeHeight = (maze.getHeight()) * (tileSize + 1);
            int mazeWidth = (maze.getWidth()) * (tileSize + 1);

            for (int h = 0; h < maze.getHeight(); h++) {
                for (int w = 0; w < maze.getWidth(); w++) {
                    int y = (h * tileSize) + (height / 2 - mazeHeight / 2);
                    int x = (w * tileSize) + (width / 2 - mazeWidth / 2);
                    graphics2D.drawImage(CellTiles.BASE, x, y, tileSize, tileSize, null);
                    Cell cell = maze.cells[h][w];

                    if (cell.getNorth() != null || cell.getSouth() != null || cell.getWest() != null || cell.getEast() != null) {
                        graphics2D.drawImage(CellTiles.CENTER, x, y, tileSize, tileSize, null);

                        if (cell.getNorth() != null) {
                            graphics2D.drawImage(CellTiles.NORTH, x, y, tileSize, tileSize, null);
                        }
                        if (cell.getSouth() != null) {
                            graphics2D.drawImage(CellTiles.SOUTH, x, y, tileSize, tileSize, null);
                        }
                        if (cell.getWest() != null) {
                            graphics2D.drawImage(CellTiles.WEST, x, y, tileSize, tileSize, null);
                        }
                        if (cell.getEast() != null) {
                            graphics2D.drawImage(CellTiles.EAST, x, y, tileSize, tileSize, null);
                        }
                    }

                    if (solver.isSolving()) {
                        if (solver instanceof Dijkstra s) {
                            if (s.marked.contains(cell)) {
                                if (cell.getNorth() != null || cell.getSouth() != null || cell.getWest() != null || cell.getEast() != null) {
                                    graphics2D.drawImage(CellTiles.MARKED_CENTER, x, y, tileSize, tileSize, null);

                                    if (cell.getNorth() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_NORTH, x, y, tileSize, tileSize, null);
                                    }
                                    if (cell.getSouth() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_SOUTH, x, y, tileSize, tileSize, null);
                                    }
                                    if (cell.getWest() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_WEST, x, y, tileSize, tileSize, null);
                                    }
                                    if (cell.getEast() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_EAST, x, y, tileSize, tileSize, null);
                                    }
                                }
                            }
                        } else if (solver instanceof BellmanFord s) {
                            if (s.marked.contains(cell)) {
                                if (cell.getNorth() != null || cell.getSouth() != null || cell.getWest() != null || cell.getEast() != null) {
                                    graphics2D.drawImage(CellTiles.MARKED_CENTER, x, y, tileSize, tileSize, null);

                                    if (cell.getNorth() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_NORTH, x, y, tileSize, tileSize, null);
                                    }
                                    if (cell.getSouth() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_SOUTH, x, y, tileSize, tileSize, null);
                                    }
                                    if (cell.getWest() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_WEST, x, y, tileSize, tileSize, null);
                                    }
                                    if (cell.getEast() != null) {
                                        graphics2D.drawImage(CellTiles.MARKED_EAST, x, y, tileSize, tileSize, null);
                                    }
                                }
                            }
                        }
                    }

                    if (solver.isSolved()) {
                        if (solver.getPath().contains(cell)) {
                            if (cell.getNorth() != null || cell.getSouth() != null || cell.getWest() != null || cell.getEast() != null) {
                                graphics2D.drawImage(CellTiles.PATH_CENTER, x, y, tileSize, tileSize, null);

                                if (cell.getNorth() != null && solver.getPath().contains(cell.getNorth())) {
                                    graphics2D.drawImage(CellTiles.PATH_NORTH, x, y, tileSize, tileSize, null);
                                }
                                if (cell.getSouth() != null && solver.getPath().contains(cell.getSouth())) {
                                    graphics2D.drawImage(CellTiles.PATH_SOUTH, x, y, tileSize, tileSize, null);
                                }
                                if (cell.getWest() != null && solver.getPath().contains(cell.getWest())) {
                                    graphics2D.drawImage(CellTiles.PATH_WEST, x, y, tileSize, tileSize, null);
                                }
                                if (cell.getEast() != null && solver.getPath().contains(cell.getEast())) {
                                    graphics2D.drawImage(CellTiles.PATH_EAST, x, y, tileSize, tileSize, null);
                                }
                            }
                        }
                    }

                    if (grid) {
                        if (builder instanceof CellMerging c) {
                            if (cell.getNorth() != null || cell.getSouth() != null || cell.getWest() != null || cell.getEast() != null) {
                                int t = c.numTab[h][w];
                                graphics2D.setColor(new Color(((t % 255) * 5) % 255, (t % 255), ((t % 255) * t) % 255, 50));
                                graphics2D.fillRect(x, y, tileSize, tileSize);
                            }
                        }

                        graphics2D.setColor(Color.BLACK);
                        graphics2D.drawRect(x, y, tileSize, tileSize);
                    }

                    if (solver.isSolved() || solver.isSolving()) {
                        if (cell == solver.getDeparture()) {
                            graphics2D.drawImage(CellTiles.DEPARTURE, x, y, tileSize, tileSize, null);
                        } else if (cell == solver.getArrival()) {
                            graphics2D.drawImage(CellTiles.ARRIVAL, x, y, tileSize, tileSize, null);
                        }
                    }
                }
            }

            /*
            int y = (builder.getLastCell().getY() * tileSize) + (height / 2 - mazeHeight / 2);
            int x = (builder.getLastCell().getX() * tileSize) + (width / 2 - mazeWidth / 2);

            */
        }

        graphics2D.dispose();
        g.dispose();
    }

    public Thread getThread() {
        return windowThread;
    }
}
