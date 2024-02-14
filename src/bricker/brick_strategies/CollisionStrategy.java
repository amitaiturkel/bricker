/**
 * Defines the interface for collision strategies between game objects.
 * Implementations of this interface should provide custom behavior for handling collisions.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Defines the contract for collision strategies between game objects.
 * Implementations of this interface should provide custom behavior for handling collisions.
 */
public interface CollisionStrategy {

    /**
     * Handles collisions between two game objects.
     *
     * @param one The first game object involved in the collision.
     * @param two The second game object involved in the collision.
     */
    void onCollision(GameObject one, GameObject two);
}
