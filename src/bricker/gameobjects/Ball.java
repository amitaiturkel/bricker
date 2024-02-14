/**
 * Represents a ball game object with collision and rendering properties.
 */
package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a ball game object with collision and rendering properties.
 */
public class Ball extends GameObject {
    /**
     * The sound played on collisions involving the ball.
     */
    private final Sound collisionSound;

    /**
     * Counter for tracking the number of collisions involving the ball.
     */
    private int collisionCounter;

    /**
     * Constructs a new Ball instance.
     *
     * @param topLeftCorner   Position of the object, in window coordinates (pixels).
     *                        Note that (0,0) is the top-left corner of the window.
     * @param dimensions      Width and height in window coordinates.
     * @param renderable      The renderable representing the object. Can be null, in which case
     *                        the GameObject will not be rendered.
     * @param collisionSound  The sound played on collision with other objects.
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
        this.collisionCounter = 0;
    }

    /**
     * Handles actions when a collision occurs with another game object.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (!(other instanceof FallingHeart)) {
            super.onCollisionEnter(other, collision);
            collisionSound.play();
            Vector2 newVelocity = getVelocity().flipped(collision.getNormal());
            setVelocity(newVelocity);
            this.collisionCounter += 1;
        }
    }

    /**
     * Retrieves the collision counter value.
     *
     * @return The number of collisions the ball has encountered.
     */
    public int getCollisionCounter() {
        return this.collisionCounter;
    }

    /**
     * Determines whether the ball should collide with a given game object.
     *
     * @param other The other game object.
     * @return True if the ball should collide with the given object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other instanceof FallingHeart || super.shouldCollideWith(other);
    }
}
