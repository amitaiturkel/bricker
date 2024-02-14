/**
 * Implements a collision strategy that combines two different collision strategies based on
 * certain conditions, providing a double behavior effect when a collision occurs between a brick and a ball.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.WindowController;
import danogl.util.Counter;

import java.util.Random;

/**
 * Implements a collision strategy that combines two different collision strategies based on
 * certain conditions, providing a double behavior effect when a collision occurs between a brick and a ball.
 */
public class DoubleBehaviorStrategy implements CollisionStrategy {
    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * The first layer collision strategy to be executed.
     */
    private CollisionStrategy firstLayer;

    /**
     * The second layer collision strategy to be executed.
     */
    private CollisionStrategy secondLayer;

    /**
     * The window controller for handling window-related operations.
     */
    private final WindowController windowController;

    /**
     * A random number generator for use within the strategy.
     */
    private static final Random random = new Random();


    /**
     * Constructs a new DoubleBehaviorStrategy instance.
     *
     * @param gameManager            The game manager responsible for managing the game state.
     * @param windowController      The window controller for handling window-related operations.
     * @param numberOfDouble        The counter for the number of double behaviors applied.
     * @param maxNumberOfDouble     The maximum number of double behaviors allowed.
     */
    public DoubleBehaviorStrategy(BrickerGameManager gameManager, WindowController windowController,
                                  Counter numberOfDouble, int maxNumberOfDouble) {
        this.gameManager = gameManager;
        this.windowController = windowController;
        if (numberOfDouble.value() >= maxNumberOfDouble) {
            // We've already chosen the double behavior
            firstLayer = this.oneOutFour();
            secondLayer = this.oneOutFour();
        } else {
            firstLayer = this.oneOutFive(numberOfDouble, maxNumberOfDouble);
            if (numberOfDouble.value() >= 1) {
                secondLayer = this.oneOutFour();
            } else {
                secondLayer = this.oneOutFive(numberOfDouble, maxNumberOfDouble);
            }
        }
    }

    /**
     * Chooses a collision strategy based on a one-out-of-five probability for double behavior.
     *
     * @param numberOfDouble        The counter for the number of double behaviors applied.
     * @param maxNumberOfDouble     The maximum number of double behaviors allowed.
     * @return The selected collision strategy.
     */
    public CollisionStrategy oneOutFive(Counter numberOfDouble, int maxNumberOfDouble) {
        double probability = random.nextInt(5);
        if (probability == 0) {
            numberOfDouble.increment();
            return new DoubleBehaviorStrategy(gameManager, windowController,
                    numberOfDouble, maxNumberOfDouble);
        } else if (probability == 1) {
            return new PuckStrategy(gameManager);
        } else if (probability == 2) {
            return new PaddleStrategy(gameManager);
        } else if (probability == 3) {
            return new CameraStrategy(gameManager, windowController);
        } else { // probability == 4
            return new FallingHeartStrategy(gameManager);
        }
    }

    /**
     * Chooses a collision strategy based on a one-out-of-four probability for double behavior.
     *
     * @return The selected collision strategy.
     */
    public CollisionStrategy oneOutFour() {
        double probability = random.nextInt(4);
        if (probability == 0) {
            return new PuckStrategy(gameManager);
        } else if (probability == 1) {
            return new PaddleStrategy(gameManager);
        } else if (probability == 2) {
            return new CameraStrategy(gameManager, windowController);
        } else { // probability == 3
            return new FallingHeartStrategy(gameManager);
        }
    }

    /**
     * Handles collisions between a brick and a ball, applying the combined behavior of two
     * collision strategies.
     *
     * @param one The first game object involved in the collision (a brick).
     * @param two The second game object involved in the collision (a ball).
     */
    @Override
    public void onCollision(GameObject one, GameObject two) {
        if (one instanceof Brick && two instanceof Ball) {
            firstLayer.onCollision(one, two);
            secondLayer.onCollision(one, two);
        }
    }
}
