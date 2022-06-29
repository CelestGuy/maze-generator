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
    private Keyboard k;
    private Mouse m;

    public Window() {
        window.setTitle("A simple maze generator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        height = 480;
        width = 720;

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

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;



        graphics2D.dispose();
        g.dispose();
    }

    public Thread getThread() {
        return windowThread;
    }
}
