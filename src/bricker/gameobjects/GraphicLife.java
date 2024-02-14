/**
 * Represents a graphic object on the game window showing hearts based on the player's lives.
 */
package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * Represents a graphic object on the game window showing hearts based on the player's lives.
 */
public class GraphicLife extends GameObject {
    //TODO add decrption here
    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * The spacing between hearts when rendering.
     * It is defined as a constant (static final) to ensure a consistent value across instances.
     */
    private static final int HEARTS_SPACING = 4;

    /**
     * An array to store heart GameObjects representing player lives.
     */
    private final GameObject[] heartsArray;

    /**
     * The top-left corner position of the heart display in the game window.
     */
    private final Vector2 topLeftCorner;

    /**
     * The dimensions of each heart GameObject.
     */
    private final Vector2 dimensions;

    /**
     * The renderable used for rendering the hearts.
     */
    private final Renderable renderable;

    /**
     * The index representing the current heart in the heartsArray.
     */
    private int currentHeartIndex;

    /**
     * The maximum number of lives a player can have.
     */
    private final int maxLives;

    /**
     * The initial number of lives at the start of the game.
     */
    private final int startLife;

    /**
     * Constructs a new GraphicLife instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     *                             the GameObject will not be rendered.
     * @param gameManager  The manger of game, objects to which hearts will be added.
     * @param startLife            The initial number of lives.
     * @param maxLife              The maximum number of lives.
     */
    public GraphicLife(Vector2 topLeftCorner,
                       Vector2 dimensions,
                       Renderable renderable,
                       BrickerGameManager gameManager,
                       int startLife, int maxLife) {
        super(topLeftCorner, dimensions, null);
        this.startLife = startLife;
        this.maxLives = maxLife;
        this.currentHeartIndex = startLife - 1;
        this.gameManager = gameManager;
        this.heartsArray = new GameObject[maxLife + 1];
        this.topLeftCorner = topLeftCorner;
        this.dimensions = dimensions;
        this.renderable = renderable;
    }

    /**
     * Initializes and adds hearts to the game window.
     */
    public void putHearts() {
        for (int i = 0; i < maxLives; i++) {
            GameObject heart = new GameObject(
                    topLeftCorner.add(Vector2.RIGHT.mult(i * (dimensions.x() + HEARTS_SPACING))),
                    dimensions,
                    renderable);
            heartsArray[i] = heart;
        }
        for (int i = 0; i < startLife; i++) {
            gameManager.addGraphicLife(heartsArray[i]);
        }
    }

    /**
     * Removes a heart from the game window.
     */
    public void removeHearts() {
        if (currentHeartIndex > 0) {
            gameManager.removeGraphicLife(heartsArray[currentHeartIndex]);
            currentHeartIndex -= 1;
        }
    }

    /**
     * Adds a heart to the game window.
     */
    public void addHearts() {
        if (currentHeartIndex + 1 < maxLives) {
            currentHeartIndex += 1;
            gameManager.addGraphicLife(heartsArray[currentHeartIndex]);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
