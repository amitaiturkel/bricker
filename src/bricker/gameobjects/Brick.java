/**
 * Represents a brick game object with collision and rendering properties.
 */
package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a brick game object with collision and rendering properties.
 */
public class Brick extends GameObject {
    /**
     * The collision strategy used for handling collisions between game objects.
     */
    private final CollisionStrategy collisionStrategy;

    /**
     * Constructs a new Brick instance.
     *
     * @param topLeftCorner      Position of the object, in window coordinates (pixels).
     *                           Note that (0,0) is the top-left corner of the window.
     * @param dimensions         Width and height in window coordinates.
     * @param renderable         The renderable representing the object. Can be null, in which case
     *                           the GameObject will not be rendered.
     * @param collisionStrategy  The collision strategy to be applied when colliding with other objects.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                 CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy = collisionStrategy;
    }

    /**
     * Handles actions when a collision occurs with another game object.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        collisionStrategy.onCollision(this, other);
    }

    /**
     * Determines whether the brick should collide with a given game object.
     *
     * @param other The other game object.
     * @return True if the brick should collide with the given object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other instanceof FallingHeart || super.shouldCollideWith(other);
    }
}
