package fr.celestgames.maze_generator.ui;

import java.awt.image.BufferedImage;

public interface Scene {
    void update();

    BufferedImage render();
}
