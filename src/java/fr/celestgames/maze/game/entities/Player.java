package fr.celestgames.maze.game.entities;

import fr.celestgames.maze.game.engine.CollisionDetector;

import java.awt.*;

public class Player extends Entity {

    public Player(String name) {
        super("player", name);
        super.setHeight(12);
        super.setWidth(12);
        super.setCollisionArea(new Rectangle(2, 2, 8, 8));
    }

    @Override
    public void update() {
        if (CollisionDetector.wallCollision(this)) {

        }
    }
}
