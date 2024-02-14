/**
 * Implements a collision strategy that adds pucks to the game when a collision occurs
 * between a brick and a ball.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import danogl.GameObject;

import java.util.Random;

/**
 * Implements a collision strategy that adds pucks to the game when a collision occurs
 * between a brick and a ball.
 */
public class PuckStrategy extends BasicCollisionStrategy {
    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * Constructs a new PuckStrategy instance.
     *
     * @param gameManager The game manager responsible for managing the game state.
     */
    public PuckStrategy(BrickerGameManager gameManager) {
        super(gameManager);
        this.gameManager = gameManager;
    }

    /**
     * Handles collisions between a brick and a ball, adding pucks to the game.
     *
     * @param one The first game object involved in the collision (a brick).
     * @param two The second game object involved in the collision (a ball).
     */
    @Override
    public void onCollision(GameObject one, GameObject two) {
        if ((one instanceof Brick && two instanceof Ball) ||
                (one instanceof Brick && one != two && two instanceof Ball)) {
            // Add pucks to the game
            this.gameManager.addPucks();
        }

        super.onCollision(one, two);
    }
}
