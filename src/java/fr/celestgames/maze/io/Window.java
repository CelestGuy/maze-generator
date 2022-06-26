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

        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {

            }
        }

        graphics2D.dispose();
        g.dispose();
    }
}
