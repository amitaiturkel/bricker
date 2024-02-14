/**
 * Implements a basic collision strategy for handling collisions between game objects.
 * Specifically designed to handle interactions between bricks and balls.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.brick_strategies.CollisionStrategy;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;

/**
 * Implements a basic collision strategy for handling collisions between game objects.
 * Specifically designed to handle interactions between bricks and balls.
 */
public class BasicCollisionStrategy implements CollisionStrategy {
    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * Constructs a new BasicCollisionStrategy instance.
     *
     * @param gameManager The game manager responsible for managing the game state.
     */
    public BasicCollisionStrategy(BrickerGameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Handles collisions between game objects, specifically between bricks and balls.
     *
     * @param obj1 The first game object involved in the collision.
     * @param obj2 The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject obj1, GameObject obj2) {

        // Remove the brick from the game
        if (obj1 instanceof Brick && obj2 instanceof Ball) {
            gameManager.removeBrick(obj1);
        }
        if (obj2 instanceof Brick && obj1 != obj2) {
            // Make sure not to remove the same brick twice
            gameManager.removeBrick(obj2);
        }
    }
}
