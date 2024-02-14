package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import java.awt.Color;
//TODO add decrption here
/**
 * Represents the numeric life component in the game, displaying the remaining lives.
 * Extends the GameObject class.
 */
public class NumericLife extends GameObject {
    /**
     * The current number of lives left.
     */
    private int livesLeft;

    /**
     * The maximum number of lives allowed.
     */
    private final int Max_life;

    /**
     * The TextRenderable used for displaying the numeric life.
     */
    private TextRenderable textRenderable;

    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;




    /**
     * Constructs a NumericLife object with the specified initial lives, maximum life, position, dimensions,
     * and associated game manager.
     *
     * @param initialLives The initial number of lives for the NumericLife object.
     * @param Max_life The maximum number of lives the NumericLife object can have.
     * @param topLeftCorner The top-left corner position of the NumericLife object.
     * @param dimensions The dimensions (width and height) of the NumericLife object.
     * @param gameManager The BrickerGameManager associated with the NumericLife object.
     */
    public NumericLife(int initialLives, int Max_life,
                       Vector2 topLeftCorner,
                       Vector2 dimensions
                       , BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, null);
        this.livesLeft = initialLives;
        this.Max_life = Max_life;
        this.gameManager = gameManager;
        textRenderable = new TextRenderable("", null, false, true); // bolded text
        textRenderable.setColor(Color.RED);
        GameObject text = new GameObject(topLeftCorner, dimensions, textRenderable);
        gameManager.addNumericLife(text);
        updateText();
    }

    /**
     * Should be called once per frame.
     *
     * @param deltaTime The time elapsed, in seconds, since the last frame.
     *                  Can be used to determine a new position/velocity by multiplying
     *                  this delta with the velocity/acceleration respectively
     *                  and adding to the position/velocity:
     *                  velocity += deltaTime*acceleration
     *                  pos += deltaTime*velocity
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateText();
    }

    /**
     * Update the displayed text based on the current number of lives.
     */
    private void updateText() {
        textRenderable.setString("Lives Left: " + livesLeft);
    }

    /**
     * Decrease the number of lives.
     */
    public void decreaseLives() {
        if (livesLeft >= 0) {
            livesLeft--;
            updateText();
        }
    }

    /**
     * Increase the number of lives.
     */
    public void increaseLives() {
        if (livesLeft <Max_life){
        livesLeft++;
        updateText();
    }}

    /**
     * Get the current number of lives.
     *
     * @return The number of lives left.
     */
    public int getLivesLeft() {
        return livesLeft;
    }
}
