package fr.celestgames.maze.game.entities;

import java.awt.*;

public abstract class Entity {
    public final String id;
    public final String name;
    public int x;
    public int dirX;
    public int y;
    public int height;
    public int width;
    public int speed;
    public Rectangle collisionArea;
    public int dirY;

    public Entity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getCollisionArea() {
        return collisionArea;
    }

    public void setCollisionArea(Rectangle collisionArea) {
        this.collisionArea = collisionArea;
    }
}