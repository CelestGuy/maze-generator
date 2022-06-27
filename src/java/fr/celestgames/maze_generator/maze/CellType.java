package fr.celestgames.maze_generator.maze;

import java.awt.image.BufferedImage;
import java.time.chrono.ThaiBuddhistChronology;

import static fr.celestgames.maze_generator.utils.ImageUtils.readImage;

public enum CellType {
    H_LINE,
    V_LINE,
    CROSS,
    DEFAULT,

    SE_CORNER,
    SW_CORNER,
    NE_CORNER,
    NW_CORNER,

    EAST_T,
    NORTH_T,
    WEST_T,
    SOUTH_T,

    EAST,
    NORTH,
    WEST,
    SOUTH;

    private static final BufferedImage Default = readImage("/assets/textures/cells/default/default.png");
    private static final BufferedImage h_line = readImage("/assets/textures/cells/default/h_line.png");
    private static final BufferedImage v_line = readImage("/assets/textures/cells/default/v_line.png");
    private static final BufferedImage cross = readImage("/assets/textures/cells/default/cross.png");

    private static final BufferedImage se_corner = readImage("/assets/textures/cells/default/corners/se.png");
    private static final BufferedImage sw_corner = readImage("/assets/textures/cells/default/corners/sw.png");
    private static final BufferedImage ne_corner = readImage("/assets/textures/cells/default/corners/ne.png");
    private static final BufferedImage nw_corner = readImage("/assets/textures/cells/default/corners/nw.png");

    private static final BufferedImage east_t = readImage("/assets/textures/cells/default/t/east.png");
    private static final BufferedImage west_t = readImage("/assets/textures/cells/default/t/west.png");
    private static final BufferedImage north_t = readImage("/assets/textures/cells/default/t/north.png");
    private static final BufferedImage south_t = readImage("/assets/textures/cells/default/t/south.png");

    private static final BufferedImage east = readImage("/assets/textures/cells/default/east.png");
    private static final BufferedImage north = readImage("/assets/textures/cells/default/north.png");
    private static final BufferedImage west = readImage("/assets/textures/cells/default/west.png");
    private static final BufferedImage south = readImage("/assets/textures/cells/default/south.png");


    private static final BufferedImage marked_h_line = readImage("/assets/textures/cells/marked/h_line.png");
    private static final BufferedImage marked_v_line = readImage("/assets/textures/cells/marked/v_line.png");
    private static final BufferedImage marked_cross = readImage("/assets/textures/cells/marked/cross.png");

    private static final BufferedImage marked_se_corner = readImage("/assets/textures/cells/marked/corners/se.png");
    private static final BufferedImage marked_sw_corner = readImage("/assets/textures/cells/marked/corners/sw.png");
    private static final BufferedImage marked_ne_corner = readImage("/assets/textures/cells/marked/corners/ne.png");
    private static final BufferedImage marked_nw_corner = readImage("/assets/textures/cells/marked/corners/nw.png");

    private static final BufferedImage marked_east_t = readImage("/assets/textures/cells/marked/t/east.png");
    private static final BufferedImage marked_west_t = readImage("/assets/textures/cells/marked/t/west.png");
    private static final BufferedImage marked_north_t = readImage("/assets/textures/cells/marked/t/north.png");
    private static final BufferedImage marked_south_t = readImage("/assets/textures/cells/marked/t/south.png");

    private static final BufferedImage marked_east = readImage("/assets/textures/cells/marked/east.png");
    private static final BufferedImage marked_north = readImage("/assets/textures/cells/marked/north.png");
    private static final BufferedImage marked_west = readImage("/assets/textures/cells/marked/west.png");
    private static final BufferedImage marked_south = readImage("/assets/textures/cells/marked/south.png");


    private static final BufferedImage path_h_line = readImage("/assets/textures/cells/path/h_line.png");
    private static final BufferedImage path_v_line = readImage("/assets/textures/cells/path/v_line.png");
    private static final BufferedImage path_cross = readImage("/assets/textures/cells/path/cross.png");

    private static final BufferedImage path_se_corner = readImage("/assets/textures/cells/path/corners/se.png");
    private static final BufferedImage path_sw_corner = readImage("/assets/textures/cells/path/corners/sw.png");
    private static final BufferedImage path_ne_corner = readImage("/assets/textures/cells/path/corners/ne.png");
    private static final BufferedImage path_nw_corner = readImage("/assets/textures/cells/path/corners/nw.png");

    private static final BufferedImage path_east_t = readImage("/assets/textures/cells/path/t/east.png");
    private static final BufferedImage path_west_t = readImage("/assets/textures/cells/path/t/west.png");
    private static final BufferedImage path_north_t = readImage("/assets/textures/cells/path/t/north.png");
    private static final BufferedImage path_south_t = readImage("/assets/textures/cells/path/t/south.png");

    private static final BufferedImage path_east = readImage("/assets/textures/cells/path/east.png");
    private static final BufferedImage path_north = readImage("/assets/textures/cells/path/north.png");
    private static final BufferedImage path_west = readImage("/assets/textures/cells/path/west.png");
    private static final BufferedImage path_south = readImage("/assets/textures/cells/path/south.png");

    private static final BufferedImage no = readImage("");

    public BufferedImage getTile() {
        if (this == H_LINE) {
            return h_line;
        } else if (this == V_LINE) {
            return v_line;
        } else if (this == CROSS) {
            return cross;
        } else if (this == SE_CORNER) {
            return se_corner;
        } else if (this == SW_CORNER) {
            return sw_corner;
        } else if (this == NE_CORNER) {
            return ne_corner;
        } else if (this == NW_CORNER) {
            return nw_corner;
        } else if (this == EAST_T) {
            return east_t;
        } else if (this == WEST_T) {
            return west_t;
        } else if (this == NORTH_T) {
            return north_t;
        } else if (this == SOUTH_T) {
            return south_t;
        } else if (this == EAST) {
            return east;
        } else if (this == NORTH) {
            return north;
        } else if (this == WEST) {
            return west;
        } else if (this == SOUTH) {
            return south;
        } else if (this == DEFAULT) {
            return Default;
        } else {
            return no;
        }
    }

    public BufferedImage getMarkedTile() {
        if (this == H_LINE) {
            return marked_h_line;
        } else if (this == V_LINE) {
            return marked_v_line;
        } else if (this == CROSS) {
            return marked_cross;
        } else if (this == SE_CORNER) {
            return marked_se_corner;
        } else if (this == SW_CORNER) {
            return marked_sw_corner;
        } else if (this == NE_CORNER) {
            return marked_ne_corner;
        } else if (this == NW_CORNER) {
            return marked_nw_corner;
        } else if (this == EAST_T) {
            return marked_east_t;
        } else if (this == WEST_T) {
            return marked_west_t;
        } else if (this == NORTH_T) {
            return marked_north_t;
        } else if (this == SOUTH_T) {
            return marked_south_t;
        } else if (this == EAST) {
            return marked_east;
        } else if (this == NORTH) {
            return marked_north;
        } else if (this == WEST) {
            return marked_west;
        } else if (this == SOUTH) {
            return marked_south;
        } else {
            return no;
        }
    }

    public BufferedImage getPathTile() {
        if (this == H_LINE) {
            return path_h_line;
        } else if (this == V_LINE) {
            return path_v_line;
        } else if (this == CROSS) {
            return path_cross;
        } else if (this == SE_CORNER) {
            return path_se_corner;
        } else if (this == SW_CORNER) {
            return path_sw_corner;
        } else if (this == NE_CORNER) {
            return path_ne_corner;
        } else if (this == NW_CORNER) {
            return path_nw_corner;
        } else if (this == EAST_T) {
            return path_east_t;
        } else if (this == WEST_T) {
            return path_west_t;
        } else if (this == NORTH_T) {
            return path_north_t;
        } else if (this == SOUTH_T) {
            return path_south_t;
        } else if (this == EAST) {
            return path_east;
        } else if (this == NORTH) {
            return path_north;
        } else if (this == WEST) {
            return path_west;
        } else if (this == SOUTH) {
            return path_south;
        } else {
            return no;
        }
    }
}
