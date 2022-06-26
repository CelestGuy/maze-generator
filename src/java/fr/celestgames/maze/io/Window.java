package fr.celestgames.maze.io;

import fr.celestgames.maze.structure.Maze;
import fr.celestgames.maze.structure.MazeParts;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze.utils.ImageUtils.readImage;

public class Window extends JPanel {
    public final JFrame window = new JFrame();
    private final int width;
    private final int height;
    private final BufferedImage path = readImage("/assets/textures/maze/path.png");
    private final BufferedImage path2 = readImage("/assets/textures/maze/path2.png");
    private final BufferedImage wall = readImage("/assets/textures/maze/wall.png");
    private final int scale;
    private final int cameraXPos;
    private final int cameraYPos;

    private Maze maze;

    public Window() {
        window.setTitle("A simple maze generator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        scale = 2;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        int tileSize = (int) (((double) maze.getHeight() / (double) height) / ((double) maze.getWidth() / (double) width) + 1) * scale;
        int mazeHeight = (maze.getHeight() * tileSize);
        int mazeWidth = (maze.getWidth() * tileSize);

        for (int h = 0; h < maze.getHeight(); h++) {
            for (int w = 0; w < maze.getWidth(); w++) {
                int y = (h * tileSize) + (height / 2 - mazeHeight / 2);
                int x = (w * tileSize) + (width / 2 - mazeWidth / 2);

                if (maze.cell[h][w] == MazeParts.WALL) {
                    graphics2D.drawImage(wall, x, y, tileSize, tileSize, null);

                } else if (maze.cell[h][w] == MazeParts.PATH) {
                    graphics2D.drawImage(path, x, y, tileSize, tileSize, null);
                }
            }
        }

        graphics2D.dispose();
        g.dispose();
    }
}
