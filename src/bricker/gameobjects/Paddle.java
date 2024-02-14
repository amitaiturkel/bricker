/**
 * Represents a paddle game object with collision, rendering, and user input handling properties.
 */
package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

/**
 * Represents a paddle game object with collision, rendering, and user input handling properties.
 */
public class Paddle extends GameObject {
    /**
     * The movement speed of the paddle.
     */
    private static final float MOVEMENT_SPEED = 300;

    /**
     * The input listener responsible for handling user input.
     */
    private final UserInputListener inputListener;

    /**
     * The dimensions of the game window.
     */
    private final Vector2 windowDimensions;

    /**
     * The dimensions of the paddle.
     */
    private final Vector2 paddleDimensions;

    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;


    /**
     * Constructs a new Paddle instance.
     *
     * @param topLeftCorner    Position of the object, in window coordinates (pixels).
     *                         Note that (0,0) is the top-left corner of the window.
     * @param dimensions       Width and height in window coordinates.
     * @param renderable       The renderable representing the object. Can be null, in which case
     *                         the GameObject will not be rendered.
     * @param inputListener    User input listener for handling keyboard input.
     * @param windowDimensions Dimensions of the game window.
     * @param gameManager      The game manager associated with the paddle.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener inputListener, Vector2 windowDimensions, BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
        this.paddleDimensions = dimensions;
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
        if (other instanceof FallingHeart) {
            gameManager.heartHitPaddle(other.getTag());
        }
    }

    /**
     * Updates the paddle's position based on user input and ensures it stays within screen boundaries.
     *
     * @param deltaTime The time elapsed, in seconds, since the last frame.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;

        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
        }

        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }

        Vector2 newPosition = getTopLeftCorner().add(movementDir.mult(MOVEMENT_SPEED * deltaTime));

        if (newPosition.x() >= 0 && newPosition.x() + paddleDimensions.x() <= windowDimensions.x() &&
                newPosition.y() >= 0 && newPosition.y() + paddleDimensions.y() <= windowDimensions.y()) {
            setVelocity(movementDir.mult(MOVEMENT_SPEED));
        } else {
            setVelocity(Vector2.ZERO);
        }
    }
}
