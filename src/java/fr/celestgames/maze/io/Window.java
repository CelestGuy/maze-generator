package fr.celestgames.maze.io;

import fr.celestgames.maze.enums.MazeParts;
import fr.celestgames.maze.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze.utils.ImageUtils.readImage;

public class Window extends JPanel {
    private final JFrame window = new JFrame();
    private final Game game;
    private final int width;
    private final int height;
    private final BufferedImage path = readImage("/assets/textures/maze/path.png");
    private final BufferedImage path2 = readImage("/assets/textures/maze/path2.png");
    private final BufferedImage wall = readImage("/assets/textures/maze/wall.png");
    private final int scale;
    private final int cameraXPos;
    private final int cameraYPos;

    public Window(Game game) {
        this.game = game;

        window.setTitle("A Mazing temple - The Buggiest Game Ever Created");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        scale = 2;
        height = 480;
        width = 720;
        cameraXPos = 0;
        cameraYPos = 0;

        addKeyListener(game.keyboard);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);

        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        int tileSize = (int) (((double) game.mainMaze.height / (double) height) / ((double) game.mainMaze.width / (double) width) + 1) * scale;
        int mazeHeight = (game.mainMaze.height * tileSize);
        int mazeWidth = (game.mainMaze.width * tileSize);

        for (int h = 0; h < game.mainMaze.height; h++) {
            for (int w = 0; w < game.mainMaze.width; w++) {
                int y = (h * tileSize) + (height / 2 - mazeHeight / 2);
                int x = (w * tileSize) + (width / 2 - mazeWidth / 2);

                if (game.mainMaze.cell[h][w] == MazeParts.WALL) {
                    graphics2D.drawImage(wall, x, y, tileSize, tileSize, null);

                } else if (game.mainMaze.cell[h][w] == MazeParts.PATH
                        || game.mainMaze.cell[h][w] == MazeParts.ARRIVAL) {
                    graphics2D.drawImage(path, x, y, tileSize, tileSize, null);
                } else if (game.mainMaze.cell[h][w] == MazeParts.CROSS) {
                    graphics2D.setColor(Color.RED);
                    graphics2D.fillRect(x, y, tileSize, tileSize);
                }
            }
        }

        graphics2D.dispose();
        g.dispose();
    }
}
