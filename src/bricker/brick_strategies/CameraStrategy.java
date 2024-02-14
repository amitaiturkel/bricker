/**
 * Implements a camera strategy that extends BasicCollisionStrategy to handle collisions
 * involving bricks, balls, and the camera.
 */
package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import bricker.gameobjects.Puck;
import danogl.GameObject;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;

/**
 * Implements a camera strategy that extends BasicCollisionStrategy to handle collisions
 * involving bricks, balls, and the camera.
 */
public class CameraStrategy extends BasicCollisionStrategy {
    /**
     * The game manager responsible for managing the game state.
     */
    private final BrickerGameManager gameManager;

    /**
     * The window controller for handling window-related operations.
     */
    private final WindowController windowController;


    /**
     * Constructs a new CameraStrategy instance.
     *
     * @param gameManager       The game manager responsible for managing the game state.
     * @param windowController  The window controller for handling window-related operations.
     */
    public CameraStrategy(BrickerGameManager gameManager, WindowController windowController) {
        super(gameManager);
        this.gameManager = gameManager;
        this.windowController = windowController;
    }

    /**
     * Handles collisions between game objects, specifically involving bricks, balls, and the camera.
     *
     * @param obj1 The first game object involved in the collision.
     * @param obj2 The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject obj1, GameObject obj2) {
        super.onCollision(obj1, obj2);
        if (obj1 instanceof Brick && (obj2 instanceof Ball && !(obj2 instanceof Puck))) {
            if (gameManager.camera() == null) {
                gameManager.setCamera(new Camera(obj2, Vector2.ZERO,
                        windowController.getWindowDimensions().mult(1.2f),
                        windowController.getWindowDimensions()));
            }
        }
    }
}
