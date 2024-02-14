/**
 * Represents a falling heart game object with collision and rendering properties.
 */
package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a falling heart game object with collision and rendering properties.
 */
public class FallingHeart extends GameObject {
    /**
     * Constructs a new FallingHeart instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public FallingHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    /**
     * Determines whether the falling heart should collide with a given game object.
     *
     * @param other The other game object.
     * @return True if the falling heart should collide with the given object, false otherwise.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return !(other instanceof Paddle) || (other instanceof PlusPaddle) || super.shouldCollideWith(other);
    }
}
