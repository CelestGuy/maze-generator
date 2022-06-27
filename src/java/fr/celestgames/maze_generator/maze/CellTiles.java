package fr.celestgames.maze_generator.maze;

import java.awt.image.BufferedImage;

import static fr.celestgames.maze_generator.utils.ImageUtils.readImage;

public class CellTiles {
    public static final BufferedImage BASE = readImage("/assets/textures/cells/base.png");

    public static final BufferedImage EAST = readImage("/assets/textures/cells/default/east.png");
    public static final BufferedImage WEST = readImage("/assets/textures/cells/default/west.png");
    public static final BufferedImage NORTH = readImage("/assets/textures/cells/default/north.png");
    public static final BufferedImage SOUTH = readImage("/assets/textures/cells/default/south.png");
    public static final BufferedImage CENTER = readImage("/assets/textures/cells/default/center.png");

    public static final BufferedImage MARKED_SOUTH = readImage("/assets/textures/cells/marked/south.png");
    public static final BufferedImage MARKED_NORTH = readImage("/assets/textures/cells/marked/north.png");
    public static final BufferedImage MARKED_EAST = readImage("/assets/textures/cells/marked/east.png");
    public static final BufferedImage MARKED_WEST = readImage("/assets/textures/cells/marked/west.png");
    public static final BufferedImage MARKED_CENTER = readImage("/assets/textures/cells/marked/center.png");

    public static final BufferedImage PATH_SOUTH = readImage("/assets/textures/cells/path/south.png");
    public static final BufferedImage PATH_NORTH = readImage("/assets/textures/cells/path/north.png");
    public static final BufferedImage PATH_EAST = readImage("/assets/textures/cells/path/east.png");
    public static final BufferedImage PATH_WEST = readImage("/assets/textures/cells/path/west.png");
    public static final BufferedImage PATH_CENTER = readImage("/assets/textures/cells/path/center.png");

    public static final BufferedImage DEPARTURE = readImage("/assets/textures/cells/departure.png");
    public static final BufferedImage ARRIVAL = readImage("/assets/textures/cells/arrival.png");
}
