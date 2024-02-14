/**
 * Implements a collision strategy that adds a paddle to the game when a collision occurs
 * between a brick and a ball.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import danogl.GameObject;

/**
 * Implements a collision strategy that adds a paddle to the game when a collision occurs
 * between a brick and a ball.
 */
public class PaddleStrategy extends BasicCollisionStrategy {

    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * Constructs a new PaddleStrategy instance.
     *
     * @param gameManager The game manager responsible for managing the game state.
     */
    public PaddleStrategy(BrickerGameManager gameManager) {
        super(gameManager);
        this.gameManager = gameManager;
    }

    /**
     * Handles collisions between a brick and a ball, adding a paddle to the game.
     *
     * @param one The first game object involved in the collision (a brick).
     * @param two The second game object involved in the collision (a ball).
     */
    @Override
    public void onCollision(GameObject one, GameObject two) {
        if (one instanceof Brick && two instanceof Ball) {
            this.gameManager.addPaddle();
            super.onCollision(one, two);
        }
    }
}
