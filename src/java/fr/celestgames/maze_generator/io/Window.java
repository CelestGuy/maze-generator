package fr.celestgames.maze_generator.io;

import fr.celestgames.maze_generator.maze.CellTiles;
import fr.celestgames.maze_generator.maze.Maze;
import fr.celestgames.maze_generator.ui.MazeScene;
import fr.celestgames.maze_generator.ui.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze_generator.utils.ImageUtils.readImage;

public class Window implements Runnable {
    private final Thread windowThread = new Thread(this, "Window Thread");
    private final JFrame window = new JFrame();
    private final JPanel panel = new JPanel();
    public final Keyboard k;
    public final Mouse m;
    private int width;
    private int height;
    private Scene scene;

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

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    @Override
    public void run() {
        while (true) {
            width = window.getWidth();
            height = window.getHeight();

            if (scene != null) {
                Graphics2D g = (Graphics2D) panel.getGraphics();
                g.drawImage(scene.render(), 0, 0, null);
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Thread getThread() {
        return windowThread;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public String getTitle() {
        return window.getTitle();
    }

    public void setIcon(String iconPath) {
        window.setIconImage(readImage(iconPath));
    }
}