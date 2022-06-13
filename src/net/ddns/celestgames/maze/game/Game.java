package net.ddns.celestgames.maze.game;

import net.ddns.celestgames.maze.builders.Builder;
import net.ddns.celestgames.maze.builders.MathVersion;
import net.ddns.celestgames.maze.builders.RecursiveBacktrack;
import net.ddns.celestgames.maze.enums.GenMode;
import net.ddns.celestgames.maze.io.Keyboard;
import net.ddns.celestgames.maze.io.Window;

import java.awt.*;
import java.util.Random;

public class Game implements Runnable {
    public final Thread mainThread = new Thread(this, "MAIN_THREAD");
    private final int width, height;
    public Maze mainMaze;
    public Window window;
    public Keyboard keyboard;
    public Builder mazeBuilder;

    public Game(int width, int height) {
        Random rand = new Random();

        this.height = height;
        this.width = width;

        this.mainMaze = new Maze(this.height, this.width);
        this.mainMaze.setGenMode(GenMode.RecursiveBacktrack);

        this.keyboard = new Keyboard();
        this.window = new Window(this);

        /*switch (rand.nextInt(2)) {
            case 0 -> {
                mazeBuilder = new RecursiveBacktrack(this, mainMaze);
            }
            case 1 -> {
                mazeBuilder = new MathVersion(this, mainMaze);
            }
        }*/
        mazeBuilder = new RecursiveBacktrack(this, mainMaze);

        this.mainThread.start();

        mazeBuilder.createPath();
    }

    @Override
    public void run() {
        while (mainThread.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mazeBuilder.updatePath();
            window.repaint();

            if (keyboard.rKeyPressed) {
                this.mainMaze = new Maze(height, width);
                this.mainMaze.setGenMode(GenMode.RecursiveBacktrack);

                if (mainMaze.getGenMode() == GenMode.RecursiveBacktrack) {
                    mazeBuilder = new RecursiveBacktrack(this, mainMaze);
                } else if (mainMaze.getGenMode() == GenMode.Mathieu) {
                    mazeBuilder = new MathVersion(this, mainMaze);
                }


                mazeBuilder.createPath();
            }

            if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
                Toolkit.getDefaultToolkit().sync();
            }
        }

        mainMaze = mazeBuilder.maze;
    }
}
