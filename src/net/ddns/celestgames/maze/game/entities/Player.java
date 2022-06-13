package net.ddns.celestgames.maze.game.entities;

import java.awt.*;

import static net.ddns.celestgames.maze.game.engine.CollisionDetector.wallCollision;

public class Player extends Entity {

    public Player(String name) {
        super("player", name);
        super.setHeight(12);
        super.setWidth(12);
        super.setCollisionArea(new Rectangle(2, 2, 8, 8));
    }

    @Override
    public void update() {
        if (wallCollision(this)) {

        }
    }
}
