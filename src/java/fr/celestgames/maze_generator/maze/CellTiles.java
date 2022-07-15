package fr.celestgames.maze_generator.maze;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.celestgames.maze_generator.utils.ImageUtils.readImage;

public class CellTiles {
    public static final BufferedImage BASE = readImage("/assets/textures/cells/base.png");

    public static final BufferedImage EAST = readImage("/assets/textures/cells/default/east.png");
    public static final BufferedImage WEST = readImage("/assets/textures/cells/default/west.png");
    public static final BufferedImage NORTH = readImage("/assets/textures/cells/default/north.png");
    public static final BufferedImage SOUTH = readImage("/assets/textures/cells/default/south.png");
    public static final BufferedImage CENTER = readImage("/assets/textures/cells/default/center.png");
    public static final BufferedImage NORTH_WEST = readImage("/assets/textures/cells/default/north_west.png");
    public static final BufferedImage NORTH_EAST = readImage("/assets/textures/cells/default/north_east.png");
    public static final BufferedImage SOUTH_WEST = readImage("/assets/textures/cells/default/south_west.png");
    public static final BufferedImage SOUTH_EAST = readImage("/assets/textures/cells/default/south_east.png");

    public static final BufferedImage MARKED_SOUTH = readImage("/assets/textures/cells/marked/south.png");
    public static final BufferedImage MARKED_NORTH = readImage("/assets/textures/cells/marked/north.png");
    public static final BufferedImage MARKED_EAST = readImage("/assets/textures/cells/marked/east.png");
    public static final BufferedImage MARKED_WEST = readImage("/assets/textures/cells/marked/west.png");
    public static final BufferedImage MARKED_CENTER = readImage("/assets/textures/cells/marked/center.png");
    public static final BufferedImage MARKED_NORTH_WEST = readImage("/assets/textures/cells/marked/north_west.png");
    public static final BufferedImage MARKED_NORTH_EAST = readImage("/assets/textures/cells/marked/north_east.png");
    public static final BufferedImage MARKED_SOUTH_WEST = readImage("/assets/textures/cells/marked/south_west.png");
    public static final BufferedImage MARKED_SOUTH_EAST = readImage("/assets/textures/cells/marked/south_east.png");

    public static final BufferedImage PATH_SOUTH = readImage("/assets/textures/cells/path/south.png");
    public static final BufferedImage PATH_NORTH = readImage("/assets/textures/cells/path/north.png");
    public static final BufferedImage PATH_EAST = readImage("/assets/textures/cells/path/east.png");
    public static final BufferedImage PATH_WEST = readImage("/assets/textures/cells/path/west.png");
    public static final BufferedImage PATH_CENTER = readImage("/assets/textures/cells/path/center.png");
    public static final BufferedImage PATH_NORTH_WEST = readImage("/assets/textures/cells/path/north_west.png");
    public static final BufferedImage PATH_NORTH_EAST = readImage("/assets/textures/cells/path/north_east.png");
    public static final BufferedImage PATH_SOUTH_WEST = readImage("/assets/textures/cells/path/south_west.png");
    public static final BufferedImage PATH_SOUTH_EAST = readImage("/assets/textures/cells/path/south_east.png");

    public static final BufferedImage DEPARTURE = readImage("/assets/textures/cells/departure.png");
    public static final BufferedImage ARRIVAL = readImage("/assets/textures/cells/arrival.png");

    public static final BufferedImage VISITED_SOUTH = readImage("/assets/textures/cells/visited/south.png");
    public static final BufferedImage VISITED_NORTH = readImage("/assets/textures/cells/visited/north.png");
    public static final BufferedImage VISITED_EAST = readImage("/assets/textures/cells/visited/east.png");
    public static final BufferedImage VISITED_WEST = readImage("/assets/textures/cells/visited/west.png");
    public static final BufferedImage VISITED_CENTER = readImage("/assets/textures/cells/visited/center.png");
    public static final BufferedImage VISITED_NORTH_WEST = readImage("/assets/textures/cells/visited/north_west.png");
    public static final BufferedImage VISITED_NORTH_EAST = readImage("/assets/textures/cells/visited/north_east.png");
    public static final BufferedImage VISITED_SOUTH_WEST = readImage("/assets/textures/cells/visited/south_west.png");
    public static final BufferedImage VISITED_SOUTH_EAST = readImage("/assets/textures/cells/visited/south_east.png");

    public static final BufferedImage MARKER = readImage("/assets/textures/cells/marker.png");
}
