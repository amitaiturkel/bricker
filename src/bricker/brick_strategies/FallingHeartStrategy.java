/**
 * Implements a collision strategy that adds falling hearts to the game when a collision occurs
 * between a brick and a ball.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.util.Vector2;

/**
 * Implements a collision strategy that adds falling hearts to the game when a collision occurs
 * between a brick and a ball.
 */
public class FallingHeartStrategy extends BasicCollisionStrategy {
    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * Constructs a new FallingHeartStrategy instance.
     *
     * @param gameManager The game manager responsible for managing the game state.
     */
    public FallingHeartStrategy(BrickerGameManager gameManager) {
        super(gameManager);
        this.gameManager = gameManager;
    }

    /**
     * Handles collisions between a brick and a ball, adding falling hearts to the game.
     *
     * @param obj1 The first game object involved in the collision (a brick).
     * @param obj2 The second game object involved in the collision (a ball).
     */
    @Override
    public void onCollision(GameObject obj1, GameObject obj2) {
        super.onCollision(obj1, obj2);
        if (obj1 instanceof Brick && obj2 instanceof Ball) {
            gameManager.addFallHearts(obj1.getCenter());
        }
    }
}
