/**
 * Factory class for creating random collision strategies.
 */
package bricker.brick_strategies;

import danogl.gui.WindowController;
import danogl.util.Counter;
import bricker.main.BrickerGameManager;

import java.util.Random;

/**
 * Factory class for creating random collision strategies.
 */
public class StrategyFactory {
    /**
     * The maximum number of occurrences allowed for certain actions, such as doubling.
     * This constant defines the limit for how many times a particular action can be performed.
     * Adjust this value based on the specific requirements of the game or application.
     */
    private int maxDouble;

    /**
     * A random number generator for use within the factory.
     */
    private static final Random random = new Random();

    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * The window controller for handling window-related operations.
     */
    private final WindowController windowController;

    /**
     * Constructs a new StrategyFactory instance.
     *
     * @param gameManager       The game manager responsible for managing the game state.
     * @param windowController  The window controller for handling window-related operations.
     * @param maxDouble         The maximum number of double behaviors allowed.
     */
    public StrategyFactory(BrickerGameManager gameManager, WindowController windowController, int maxDouble) {
        this.gameManager = gameManager;
        this.windowController = windowController;
        this.maxDouble = maxDouble;
    }

    /**
     * Creates a random collision strategy based on predefined probabilities.
     *
     * @return A randomly selected collision strategy.
     */
    public CollisionStrategy createRandomStrategy() {
        double probability = random.nextDouble();

        if (probability < 0.5) {
            return new BasicCollisionStrategy(gameManager);
        } else if (probability < 0.6) {
            return new PuckStrategy(gameManager);
        } else if (probability < 0.7) {
            return new PaddleStrategy(gameManager);
        } else if (probability < 0.8) {
            return new CameraStrategy(gameManager, windowController);
        } else if (probability < 0.9) {
            return new FallingHeartStrategy(gameManager);
        } else {
            return new DoubleBehaviorStrategy(gameManager, windowController,
                    new Counter(0), maxDouble);
        }
    }
}
