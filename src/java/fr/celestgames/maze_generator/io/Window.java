package fr.celestgames.maze_generator.io;

import fr.celestgames.maze_generator.maze.Cell;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.maze.CellType;
import fr.celestgames.maze_generator.maze.builders.Builder;
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

    int frameCount = 0;
    @Override
    public void run() {
        while (true) {
            update();
            try {
                Thread.sleep(8, 333333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frameCount++;
        }
    }

    public void update() {
        width = window.getWidth();
        height = window.getHeight();
        tileSize = (int) ((int) ((double) height / (double) maze.getHeight()) * 0.9);

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

                    BufferedImage img = maze.cells[h][w].getType().getTile();

                    graphics2D.drawImage(img, x, y, tileSize, tileSize, null);

                    if (grid) {
                        graphics2D.setColor(Color.black);
                        graphics2D.drawRect(x, y, tileSize, tileSize);
                    }

                    if (solver.isSolving() && solver instanceof Dijkstra s) {
                        BufferedImage marked_img = maze.cells[h][w].getType().getMarkedTile();

                        if (s.marked.contains(maze.cells[h][w])) {
                            graphics2D.drawImage(marked_img, x, y, tileSize, tileSize, null);
                        }
                    }

                    if (solver.isSolved()) {
                        BufferedImage path_img = maze.cells[h][w].getType().getPathTile();

                        if (solver.getPath().contains(maze.cells[h][w])) {
                            graphics2D.drawImage(path_img, x, y, tileSize, tileSize, null);
                        }
                    }
                }
            }

            int y = (builder.getLastCell().getY() * tileSize) + (height / 2 - mazeHeight / 2);
            int x = (builder.getLastCell().getX() * tileSize) + (width / 2 - mazeWidth / 2);

            graphics2D.setColor(Color.RED);
            graphics2D.drawImage(marker, x, y, tileSize, tileSize, null);
        }

        graphics2D.dispose();
        g.dispose();
    }

    public Thread getThread() {
        return windowThread;
    }
}
