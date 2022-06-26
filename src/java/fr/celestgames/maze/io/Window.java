package fr.celestgames.maze.io;

import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.MazeParts;
import fr.celestgames.maze.structure.builders.Builder;
import fr.celestgames.maze.structure.solvers.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze.utils.ImageUtils.readImage;

public class Window extends JPanel {
    public final JFrame window = new JFrame();
    private int width;
    private int height;
    private final BufferedImage path = readImage("/assets/textures/maze/path.png");
    private final BufferedImage path2 = readImage("/assets/textures/maze/path2.png");
    private final BufferedImage wall = readImage("/assets/textures/maze/wall.png");
    private final int scale;
    private final int cameraXPos;
    private final int cameraYPos;

    private Maze maze;
    private Solver solver;
    private Builder builder;
    private int tileSize;

    public Window() {
        window.setTitle("A simple maze generator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        scale = 5;
        height = 480;
        width = 720;
        cameraXPos = 0;
        cameraYPos = 0;
;
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

    public void update() {
        repaint();
        width = window.getWidth();
        height = window.getHeight();
        tileSize = (int) ((double) height / (double) maze.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        int mazeHeight = (maze.getHeight()) * (tileSize + 1);
        int mazeWidth = (maze.getWidth()) * (tileSize + 1);

        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                int y = (h * tileSize) + (height / 2 - mazeHeight / 2);
                int x = (w * tileSize) + (width / 2 - mazeWidth / 2);

                if (maze.cell[h][w] == MazeParts.WALL) {
                    graphics2D.setColor(Color.DARK_GRAY);

                } else if (maze.cell[h][w] == MazeParts.PATH) {
                    graphics2D.setColor(Color.WHITE);
                }
                graphics2D.fillRect(x, y, tileSize, tileSize);
            }
        }

        int y = ((builder.oldPoint.y * 2 + 1) * tileSize) + (height / 2 - mazeHeight / 2);
        int x = ((builder.oldPoint.x * 2 + 1) * tileSize) + (width / 2 - mazeWidth / 2);

        graphics2D.setColor(Color.RED);
        graphics2D.fillRect(x, y, tileSize, tileSize);

        graphics2D.dispose();
        g.dispose();
    }
}
