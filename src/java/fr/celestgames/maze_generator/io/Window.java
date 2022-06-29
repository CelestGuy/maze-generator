package fr.celestgames.maze_generator.io;

import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze_generator.utils.ImageUtils.readImage;

public class Window implements Runnable {
    private final Thread windowThread = new Thread(this);
    private final JFrame window = new JFrame();
    private final JPanel panel = new JPanel();
    private final Keyboard k;
    private final Mouse m;
    private int width;
    private int height;
    private Maze maze;

    public Window() {
        window.setTitle("A simple maze generator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        height = 480;
        width = 720;

        k = new Keyboard();
        m = new Mouse();

        panel.addMouseListener(m);
        panel.addKeyListener(k);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);

        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void run() {
        while (true) {
            width = window.getWidth();
            height = window.getHeight();

            BufferedImage image = maze.render();

            if (image != null) {
                panel.getGraphics().drawImage(image, 0, 0, maze.getWidth() * 16 , maze.getHeight() * 16, null);
            }
        }
    }

    public Thread getThread() {
        return windowThread;
    }
}