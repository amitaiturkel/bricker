/**
 * Represents a special type of paddle that disappears after a certain number of hits.
 */
package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a special type of paddle that disappears after a certain number of hits.
 */
public class PlusPaddle extends Paddle {
    /**
     * The number of hits required for the PlusPaddle to disappear.
     */
    private final int hitsToDisappear;

    /**
     * The current number of hits received by the PlusPaddle.
     */
    private int currentHits;

    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;


    /**
     * Constructs a new PlusPaddle instance.
     *
     * @param topLeftCorner    Position of the object, in window coordinates (pixels).
     *                         Note that (0,0) is the top-left corner of the window.
     * @param dimensions       Width and height in window coordinates.
     * @param renderable       The renderable representing the object. Can be null, in which case
     *                         the GameObject will not be rendered.
     * @param inputListener    User input listener for handling keyboard input.
     * @param windowDimensions Dimensions of the game window.
     * @param hitsToDisappear  The number of hits required for the paddle to disappear.
     * @param gameManager      The game manager associated with the paddle.
     */
    public PlusPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                      UserInputListener inputListener,
                      Vector2 windowDimensions, int hitsToDisappear, BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable, inputListener, windowDimensions, gameManager);
        this.hitsToDisappear = hitsToDisappear;
        this.currentHits = 0;
        this.gameManager = gameManager;
    }

    /**
     * Handles collision with other game objects.
     *
     * @param other     The other game object involved in the collision.
     * @param collision Information about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other instanceof Ball) {
            currentHits++;
        }
    }

    /**
     * Updates the plus paddle's state and checks if it should disappear.
     *
     * @param deltaTime The time elapsed, in seconds, since the last frame.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (currentHits == hitsToDisappear) {
            gameManager.remove_Plus_paddle();
        }
    }

    /**
     * Determines if the plus paddle should collide with a given game object.
     *
     * @param other The other game object to check for collision.
     * @return True if the plus paddle should collide, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other instanceof FallingHeart || super.shouldCollideWith(other);
    }
}
