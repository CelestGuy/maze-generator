package fr.celestgames.maze.game.engine;

import fr.celestgames.maze.game.entities.Entity;

import java.awt.*;

public class CollisionDetector {
    public static void checkCollision(Entity entity, Entity target) {
        Rectangle entityArea = new Rectangle(entity.x + entity.collisionArea.x + (entity.speed * entity.dirX), entity.y + entity.collisionArea.y + (entity.speed * entity.dirY), entity.collisionArea.width, entity.collisionArea.height);
        Rectangle targetArea = new Rectangle(target.x + target.collisionArea.x + (target.speed * target.dirX), target.y + target.collisionArea.y + (target.speed * target.dirY), target.collisionArea.width, target.collisionArea.height);

        if (entityArea.intersects(targetArea)) {
            if ((entity.x + entity.dirX + entity.collisionArea.x + entity.collisionArea.width >= target.collisionArea.x)
                    || entity.x + entity.collisionArea.x + entity.dirX <= target.x + target.collisionArea.x) {
                entity.dirX = entity.dirX * -1;
            }
            if ((entity.dirY < 0 && target.dirY > 0) || (entity.dirY > 0 && target.dirY < 0)) {
                entity.dirY = entity.dirY * -1;
            }
        }
    }

    public static boolean wallCollision(Entity entity) {
        return false;
    }
}
