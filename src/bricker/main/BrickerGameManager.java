package bricker.main;
import bricker.brick_strategies.*;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.ImageRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import danogl.util.Counter;

/**
 * The main class managing the game logic for the Brick Breaker game.
 */
public class BrickerGameManager extends GameManager {
    // Constants related to Pucks
    /**
     * The number of new pucks created in a certain action.
     */
    private static final int NUM_OF_NEW_PUCKS = 2;

    /**
     * The speed at which pucks move.
     */
    private static final float PUCK_SPEED = 300;

    /**
     * The speed at which hearts fall.
     */
    private static final float HEART_SPEED = 100;

// Constants related to game dimensions
    /**
     * The width of the border in the game window.
     */
    private static final int BORDER_WIDTH = 30;

    /**
     * The width of the paddle along the Y-axis.
     */
    private static final int PADDLE_WIDTH = 15; // Y-axis dimension of the paddle

    /**
     * The length of the paddle along the X-axis.
     */
    private static final int PADDLE_LEN = 100; // X-axis dimension of the paddle

    /**
     * The width of the ball.
     */
    private static final int BALL_WIDTH = 30;

    /**
     * The length of the ball.
     */
    private static final int BALL_LEN = 30;

    /**
     * The speed at which the ball moves.
     */
    private static final int BALL_SPEED = 300;

    /**
     * Represents half of a value (0.5F).
     */
    private static final float HALF = 0.5F;

// File paths for game assets
    /**
     * The file path for the image of the ball.
     */
    private static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * The file path for the sound of the ball.
     */
    private static final String BALL_SOUND_PATH = "assets/blop.wav";

    /**
     * The file path for the image of the paddle.
     */
    private static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * The file path for the background image.
     */
    private static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * The file path for the image of a brick.
     */
    private static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * The size of the heart image.
     */
    private static final float HEART_SIZE = 30F;

    /**
     * The file path for the image of a heart.
     */
    private static final String HEART_IMAGE = "assets/heart.png";

    /**
     * The message displayed when the player loses.
     */
    private static final String LOSEING_MESSAGE = "You lose! Play again?";

    /**
     * The message displayed when the player wins.
     */
    private static final String WINNING_MESSAGE = "You win! Play again?";

    /**
     * The file path for the image of a puck.
     */
    private static final String PUCK_IMG_PATH = "assets/mockBall.png";

    /**
     * The file path for a silenced version of the blop sound.
     */
    private static final String BLOP_SOUND_PATH = "assets/blop_cut_silenced.wav";

    /**
     * Maximum number of occurrences allowed for certain actions, such as doubling.
     * This constant defines the limit for how many times a particular action can be performed.
     * Adjust this value based on the specific requirements of the game or application.
     */
    private final int MAX_NUMBER_OF_DOUBLE = 1;

// Game-related variables
    /**
     * The counter for bricks in the game.
     */
    private Counter brickCounter;

    /**
     * The number of bricks in a row.
     */
    private final int brickInRow;

    /**
     * The number of rows of bricks.
     */
    private final int rowOfBricks;

    /**
     * The height of bricks.
     */
    private static final int BRICKS_HEIGHT = 15;

    /**
     * The gap between bricks.
     */
    private static final int BRICK_GAP = 1;

    /**
     * The dimensions of the game window.
     */
    private final Vector2 windowDimensions;

    /**
     * The current number of lives.
     */
    private int current_lives;

    /**
     * The maximum number of lives a player can have.
     */
    private final int maxLives = 4;

    /**
     * The Ball object in the game.
     */
    private Ball Ball;

    /**
     * The total number of bricks in the game.
     */
    private final int TOTAL_BRICKS;

    /**
     * The NumericLife object representing numeric lives in the game.
     */
    private NumericLife numericLife;

    /**
     * The GraphicLife object representing graphical lives in the game.
     */
    private GraphicLife graphicLife;

    /**
     * The UserInputListener for handling user input.
     */
    private UserInputListener inputListener;

    /**
     * The WindowController for handling window-related operations.
     */
    private WindowController windowController;

    /**
     * The ImageReader for reading images.
     */
    private ImageReader imageReader;

    /**
     * The SoundReader for reading sounds.
     */
    private SoundReader soundReader;

    /**
     * The maximum number of pucks allowed in the game.
     */
    private final int MaxNumOfPuck;

    /**
     * An array to store Puck objects.
     */
    private final Puck[] ArrayPucks;

    /**
     * An array to store FallingHeart objects.
     */
    private final FallingHeart[] ArrayFallHearts;

    /**
     * The maximum number of falling hearts allowed in the game.
     */
    private final int Max_Fall_Hearts;

    /**
     * The current number of falling hearts in the game.
     */
    private int currentFallHeart;

    /**
     * The current number of pucks in the game.
     */
    private int currentPuck;

    /**
     * A flag indicating whether a PlusPaddle exists in the game.
     */
    private boolean Plus_paddle_exist;

    /**
     * The PlusPaddle object in the game.
     */
    private PlusPaddle Plus_paddle;

    /**
     * The maximum number of hits allowed for a PlusPaddle.
     */
    private int PLUS_PADDLE_MAX_HIT = 4;

    /**
     * A flag indicating whether the camera is null.
     */
    private boolean CameraisNull;

    /**
     * The maximum number of hits allowed for the camera.
     */
    private int camera_max_hits_num;

    /**
     * The constant representing the maximum number of hits allowed for the camera.
     */
    private final int CAMERA_MAX_HITS_NUM = 4;

    /**
     * The last recorded number of hits for the ball.
     */
    private int balLastHits;

    /**
     * The initial number of lives at the start of the game.
     */
    private final int Startlife = 3;

    /**
     * An array to store Brick objects.
     */
    private final Brick[] list_of_Bricks;

    /**
     * The constant representing the value zero.
     */
    private final int ZERO = 0;

    /**
     * The constant representing the value one.
     */
    private final int ONE = 1;

    /**
     * The constant representing the value two.
     */
    private final int TWO = 2;

    /**
     * The default number of bricks in a row.
     */
    private final int DEFAULT_IN_ROW = 8;

    /**
     * The default number of rows of bricks.
     */
    private final int DEFAULT_ROW = 7;

    /**
     * The distance for a heart.
     */
    private final int DIS_FOR_HEART = 50;

    /**
     * The distance for a graphical heart.
     */
    private final int DIS_FOR_GHEART = 35;

    /**
     * The proportion of pucks to the size of a ball.
     */
    private final float PUCK_PER_BALL_SIZE = 0.75F;

// Miscellaneous constants
    /**
     * The constant representing the value ten.
     */
    private final int VEC_TEN = 10;



    // TODO: check speeds for ball and pucks, and sizes
    // TODO: add dec
    // TODO: check codeing style


    /**
     * Constructor for BrickerGameManager.
     *
     * @param windowTitle      Title of the game window.
     * @param windowDimensions Dimensions of the game window.
     * @param BrickInRow       Number of bricks in each row.
     * @param RowOfBricks      Number of rows of bricks.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, int
            BrickInRow, int RowOfBricks) {
        super(windowTitle, windowDimensions);
        this.brickInRow = BrickInRow;
        this.rowOfBricks = RowOfBricks;
        this.windowDimensions = windowDimensions;
        this.TOTAL_BRICKS = BrickInRow * RowOfBricks;
        list_of_Bricks = new Brick[TOTAL_BRICKS + ONE];
        this.MaxNumOfPuck = TOTAL_BRICKS * TWO;
        this.ArrayPucks = new Puck[this.MaxNumOfPuck + TWO];
        currentPuck = ZERO;
        Max_Fall_Hearts = TOTAL_BRICKS;
        ArrayFallHearts = new FallingHeart[Max_Fall_Hearts + ONE];
        currentFallHeart = ZERO;


    }

    /**
     * Constructor for BrickerGameManager with default brick layout.
     *
     * @param windowTitle      Title of the game window.
     * @param windowDimensions Dimensions of the game window.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
        brickInRow = DEFAULT_IN_ROW;
        rowOfBricks = DEFAULT_ROW;
        this.windowDimensions = windowDimensions;
        this.TOTAL_BRICKS = brickInRow * rowOfBricks;
        list_of_Bricks = new Brick[TOTAL_BRICKS + ONE];
        Max_Fall_Hearts = TOTAL_BRICKS;
        this.MaxNumOfPuck = TOTAL_BRICKS * TWO;
        this.ArrayPucks = new Puck[this.MaxNumOfPuck + TWO];
        currentPuck = ZERO;
        ArrayFallHearts = new FallingHeart[Max_Fall_Hearts + ONE];
        currentFallHeart = ZERO;


    }

    /**
     * Initializes the game by setting up the game objects, including the ball, paddle,
     * walls, background, bricks, hearts, and numeric life display.
     *
     * @param imageReader      Reads images for game objects.
     * @param soundReader      Reads sounds for game objects.
     * @param inputListener    Listens for user input.
     * @param windowController Manages the game window.
     */
    @Override
    public void initializeGame(
            ImageReader imageReader,
            SoundReader soundReader, UserInputListener inputListener,
            WindowController windowController) {
        Plus_paddle_exist = false;
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        CameraisNull = true;
        this.windowController = windowController;
        this.inputListener = inputListener;
        Vector2 windowDimensions = windowController.getWindowDimensions();
        this.brickCounter = new Counter(ZERO);
        createBall(BALL_IMAGE_PATH, BALL_SOUND_PATH, imageReader, soundReader, windowDimensions);
        camera_max_hits_num = CAMERA_MAX_HITS_NUM;
        balLastHits = ZERO;
        createPaddle(PADDLE_IMAGE_PATH, imageReader, inputListener, windowDimensions);
        createWalls(null, windowDimensions);
        createBackground(BACKGROUND_IMAGE_PATH, imageReader, windowDimensions);
        ImageRenderable BrickImage = imageReader.readImage(BRICK_IMAGE_PATH, true);
        createBricks(BrickImage);
        ImageRenderable HeartImage = imageReader.readImage(HEART_IMAGE, true);
        Vector2 heartLeftTopCorner = new Vector2(TWO * HEART_SIZE, windowDimensions.y() -
                DIS_FOR_GHEART);
        GraphicLife graphicLife = createHearts(HeartImage, heartLeftTopCorner);
        this.graphicLife = graphicLife;
        Vector2 numHeartLeftTopCorner = new Vector2(TWO * HEART_SIZE, windowDimensions.y() -
                DIS_FOR_HEART);
        Vector2 numVector = new Vector2(VEC_TEN, VEC_TEN);
        current_lives = maxLives;
        NumericLife numericLife = new NumericLife(Startlife, maxLives, numHeartLeftTopCorner
                , numVector, this);
        this.numericLife = numericLife;
        this.gameObjects().addGameObject(numericLife, Layer.BACKGROUND);
    }


    /**
     * Removes the specified brick game object from the game world, updating the brick count.
     *
     * @param obj1 The brick game object to be removed.
     */
    public void removeBrick(GameObject obj1) {
        int tag = Integer.parseInt(obj1.getTag());
        if (list_of_Bricks[tag] != null) { // to prevent double stra to delete twice
            this.brickCounter.decrement();
            this.gameObjects().removeGameObject(list_of_Bricks[tag], Layer.STATIC_OBJECTS);
            list_of_Bricks[tag] = null;
        }

    }

    /**
     * Creates and initializes a GraphicLife instance for representing player lives using heart images.
     *
     * @param heartImage         The image renderable for the heart.
     * @param heartLeftTopCorner The top-left corner position of the heart display.
     * @return The created GraphicLife instance.
     */
    private GraphicLife createHearts(ImageRenderable heartImage, Vector2 heartLeftTopCorner) {
        Vector2 Dimensions = new Vector2(HEART_SIZE, HEART_SIZE);
        GraphicLife graphicLife = new GraphicLife(heartLeftTopCorner, Dimensions, heartImage,
                this, Startlife, maxLives);
        this.gameObjects().addGameObject(graphicLife, Layer.BACKGROUND);
        graphicLife.putHearts();
        return graphicLife;


    }

    /**
     * Updates the game state based on the elapsed time.
     *
     * @param deltaTime The time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        checkWinningKeyPress();
        removeFallenHearts();
        deactivateCameraIfNeeded();
        removeOffScreenPucks();
        updateBallPositionAndLives();
        checkForWinningMessage();
    }

    /**
     * Checks if the "UP" key is pressed and initiates the winning message if true.
     */
    private void checkWinningKeyPress() {
        if (inputListener.isKeyPressed(KeyEvent.VK_W)) {
            displayWinningMessage();
        }
    }

    /**
     * Removes fallen hearts from the screen.
     */
    private void removeFallenHearts() {
        for (int i = 0; i < currentFallHeart; i++) {
            if (ArrayFallHearts[i] != null && ArrayFallHearts[i].getCenter().y() > windowDimensions.y()) {
                gameObjects().removeGameObject(ArrayFallHearts[i]);
                ArrayFallHearts[i] = null;
            }
        }
    }

    /**
     * Deactivates the camera if conditions are met.
     */
    private void deactivateCameraIfNeeded() {
        if (camera() != null) {
            checkAndDeactivateCamera();
        }
    }

    /**
     * Checks camera conditions and deactivates it if needed.
     */
    private void checkAndDeactivateCamera() {
        if (CameraisNull) {
            CameraisNull = false;
            balLastHits = Ball.getCollisionCounter();
        }

        if (Ball.getCollisionCounter() - balLastHits >= camera_max_hits_num) {
            CameraisNull = true;
            setCamera(null);
        }
    }

    /**
     * Removes off-screen pucks.
     */
    private void removeOffScreenPucks() {
        for (int i = 0; i < currentPuck; i++) {
            if (ArrayPucks[i] != null && ArrayPucks[i].getCenter().y() > windowDimensions.y()) {
                gameObjects().removeGameObject(ArrayPucks[i]);
                ArrayPucks[i] = null;
            }
        }
    }

    /**
     * Updates the ball position and lives based on its position on the screen.
     */
    private void updateBallPositionAndLives() {
        if (Ball.getCenter().y() > windowDimensions.y()) {
            handleBallOffScreen();
        }
    }

    /**
     * Handles the situation when the ball falls off the screen.
     */
    private void handleBallOffScreen() {
        Ball.setCenter(windowDimensions.mult(HALF));

        if (numericLife.getLivesLeft() > 1) {
            updateLivesAndHearts();
        } else {
            displayLosingMessage();
        }
    }

    /**
     * Updates lives and hearts when the ball falls off the screen.
     */
    private void updateLivesAndHearts() {
        current_lives -= 1;
        graphicLife.removeHearts();
        numericLife.decreaseLives();
    }

    /**
     * Checks if all bricks are destroyed and displays the winning message if true.
     */
    private void checkForWinningMessage() {
        if (brickCounter.value() == 0) {
            displayWinningMessage();
        }
    }

    /**
     * Displays the winning message and handles the user's response.
     */
    private void displayWinningMessage() {
        String message = WINNING_MESSAGE;
        handleUserResponse(message);
    }

    /**
     * Displays the losing message and handles the user's response.
     */
    private void displayLosingMessage() {
        String message = LOSEING_MESSAGE;
        handleUserResponse(message);
    }

    /**
     * Handles the user's response to a message (Play again or Close window).
     *
     * @param message The message to display.
     */
    private void handleUserResponse(String message) {
        boolean playAgain = windowController.openYesNoDialog(message);
        if (playAgain) {
            windowController.resetGame();
        } else {
            windowController.closeWindow();
        }
    }


    /**
     * Creates the game ball with specified image, sound, and initial properties.
     *
     * @param imagePath        The path to the image file for the ball.
     * @param soundPath        The path to the sound file for ball collisions.
     * @param imageReader      The image reader to load the ball image.
     * @param soundReader      The sound reader to load the collision sound.
     * @param windowDimensions The dimensions of the game window.
     */
    private void createBall(String imagePath, String soundPath, ImageReader imageReader,
                             SoundReader soundReader, Vector2 windowDimensions) {
        ImageRenderable ballImage = imageReader.readImage(imagePath, true);
        Sound collisionSound = soundReader.readSound(soundPath);
        Ball ball = new Ball(Vector2.ZERO, new Vector2(BALL_WIDTH, BALL_LEN), ballImage, collisionSound);

        // Set random initial velocity
        float ballValX = BALL_SPEED;
        float ballValY = BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballValY *= -1;
        }
        if (rand.nextBoolean()) {
            ballValX *= -1;
        }
        ball.setVelocity(new Vector2(ballValX, ballValY));
        ball.setCenter(windowDimensions.mult(HALF));

        this.gameObjects().addGameObject(ball);
        this.Ball = ball;
    }

    /**
     * Creates the game paddle with specified image, input listener, and initial properties.
     *
     * @param imagePath        The path to the image file for the paddle.
     * @param imageReader      The image reader to load the paddle image.
     * @param inputListener    The input listener to handle paddle controls.
     * @param windowDimensions The dimensions of the game window.
     */
    private void createPaddle(String imagePath, ImageReader imageReader,
                              UserInputListener inputListener, Vector2 windowDimensions) {
        ImageRenderable paddleImage = imageReader.readImage(imagePath, true);
        Paddle paddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_LEN, PADDLE_WIDTH),
                paddleImage, inputListener, windowDimensions, this);
        paddle.setCenter(new Vector2(windowDimensions.x() * HALF,
                windowDimensions.y() - PADDLE_WIDTH));

        this.gameObjects().addGameObject(paddle);
    }

    /**
     * Adds a special paddle to the game if it doesn't already exist.
     * The special paddle has additional properties and is activated when called.
     */
    public void addPaddle() {
        if (!Plus_paddle_exist) {
            Plus_paddle_exist = true;
            ImageRenderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH,
                    true);
            PlusPaddle paddle = new PlusPaddle(Vector2.ZERO, new Vector2(PADDLE_LEN, PADDLE_WIDTH),
                    paddleImage, inputListener, windowDimensions,
                    PLUS_PADDLE_MAX_HIT, this);
            paddle.setCenter(new Vector2(windowDimensions.x() * HALF, windowDimensions.y()
                    * HALF - PADDLE_WIDTH));
            this.gameObjects().addGameObject(paddle);
            Plus_paddle = paddle;
        }
    }

    /**
     * Removes the special paddle from the game.
     * Resets the flag and removes the paddle object from the game objects.
     */
    public void remove_Plus_paddle() {
        Plus_paddle_exist = false;
        this.gameObjects().removeGameObject(Plus_paddle);
        Plus_paddle = null;
    }


    /**
     * Creates the bricks for the game based on the specified parameters.
     * Calculates the dimensions, positioning, and strategy for each brick,
     * and adds them to the game.
     *
     * @param BrickImage The image to be used for the bricks.
     */
    private void createBricks(ImageRenderable BrickImage) {
        float totalGapWidth = BRICK_GAP * (this.brickInRow - 1);
        float brickWidth = (this.windowDimensions.x() - totalGapWidth - 2 * BRICK_GAP) / this.brickInRow;
        StrategyFactory strategyFactory = new StrategyFactory
                (this, windowController, MAX_NUMBER_OF_DOUBLE);

        Vector2 dimensions = new Vector2(brickWidth, BRICKS_HEIGHT);
        Vector2 current_left_corner = new Vector2(BRICK_GAP, BRICK_GAP);
        int tag = 0;
        for (int row = 0; row < this.rowOfBricks; row++) {
            for (int col = 0; col < this.brickInRow; col++) {
                createBrick(current_left_corner, dimensions, BrickImage, strategyFactory, tag);
                tag += 1;
                current_left_corner = current_left_corner.add(new Vector2(brickWidth + BRICK_GAP,
                        0));
            }
            current_left_corner = new Vector2(BRICK_GAP, current_left_corner.y() +
                    BRICKS_HEIGHT + BRICK_GAP);
        }
    }

    /**
     * Creates a single brick and adds it to the game.
     * Associates a random collision strategy and sets the tag.
     *
     * @param topLeftCorner   The top-left corner of the brick.
     * @param dimensions      The dimensions (width, height) of the brick.
     * @param BrickImage      The image to be used for the brick.
     * @param strategyFactory The strategy factory to create collision strategies.
     * @param tag             The tag to identify the brick.
     */
    private void createBrick(Vector2 topLeftCorner, Vector2 dimensions, ImageRenderable BrickImage,
                             StrategyFactory strategyFactory, int tag) {
        CollisionStrategy collisionStrategy = strategyFactory.createRandomStrategy();

        Brick brick = new Brick(topLeftCorner, dimensions, BrickImage, collisionStrategy);
        brick.setTag(Integer.toString(tag));
        this.gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
        list_of_Bricks[tag] = brick;
        this.brickCounter.increment();
    }

    /**
     * Adds new pucks to the game based on the specified number.
     * Creates pucks with random initial velocities and adds them to the game.
     */
    public void addPucks() {
        for (int i = 0; i < NUM_OF_NEW_PUCKS; i++) {
            Puck puck = new Puck(windowDimensions.mult(HALF), new Vector2(BALL_WIDTH*
                    PUCK_PER_BALL_SIZE, BALL_LEN*PUCK_PER_BALL_SIZE),
                    this.imageReader.readImage(PUCK_IMG_PATH, true),
                    this.soundReader.readSound(BLOP_SOUND_PATH));
            float ballValx = PUCK_SPEED;
            float ballValy = PUCK_SPEED;
            Random rand = new Random();
            if (rand.nextBoolean()) {
                ballValy *= -1;
            }
            if (rand.nextBoolean()) {
                ballValx *= -1;
            }
            puck.setVelocity(new Vector2(ballValx, ballValy));
            puck.setCenter(windowDimensions.mult(HALF));
            this.gameObjects().addGameObject(puck);
            ArrayPucks[currentPuck] = puck;
            currentPuck++;
        }
    }


    /**
     * Adds falling hearts to the game based on the specified center position.
     * Creates a falling heart with the given center and adds it to the game.
     *
     * @param center The center position for the falling heart.
     */
    public void addFallHearts(Vector2 center) {
        ImageRenderable heartImage = imageReader.readImage(HEART_IMAGE, true);
        FallingHeart heart = new FallingHeart(center, new Vector2(HEART_SIZE, HEART_SIZE), heartImage);
        heart.setVelocity(Vector2.DOWN.mult(HEART_SPEED));
        heart.setTag(Integer.toString(currentFallHeart));
        ArrayFallHearts[currentFallHeart] = heart;
        gameObjects().addGameObject(ArrayFallHearts[currentFallHeart]);
        currentFallHeart++;
    }

    /**
     * Handles the event when a falling heart hits the paddle.
     * Increases the life count and removes the heart from the game.
     *
     * @param tag The tag of the falling heart.
     */
    public void heartHitPaddle(String tag) {
        int numTag = Integer.parseInt(tag);
        if (ArrayFallHearts[numTag] == null) {
            return;
        }
        gameObjects().removeGameObject(ArrayFallHearts[numTag]);
        ArrayFallHearts[numTag] = null;
        graphicLife.addHearts();
        numericLife.increaseLives();
    }

    /**
     * Creates the background for the game based on the specified parameters.
     * Adds a background image to the game.
     *
     * @param imagePath        The file path of the background image.
     * @param imageReader      The image reader to load the background image.
     * @param windowDimensions The dimensions of the game window.
     */
    private void createBackground(String imagePath, ImageReader imageReader, Vector2 windowDimensions) {
        ImageRenderable backgroundImage = imageReader.readImage(imagePath, true);
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, backgroundImage);
        background.setCenter(windowDimensions.mult(HALF));
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        this.gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /**
     * Creates walls for the game based on the specified parameters.
     * Adds left, right, and top walls to the game with given color and dimensions.
     *
     * @param color            The color of the walls.
     * @param windowDimensions The dimensions of the game window.
     */
    private void createWalls(Color color, Vector2 windowDimensions) {
        RectangleRenderable wallPic = null;
        float leftWallXSize = BORDER_WIDTH;
        float rightWallXSize = BORDER_WIDTH;
        float topWallXSize = windowDimensions.x();
        float rightWallYSize = windowDimensions.y();
        float topWallYSize = BORDER_WIDTH;
        Vector2 leftWallSize = new Vector2(leftWallXSize, windowDimensions.y());
        Vector2 rightWallSize = new Vector2(rightWallXSize, rightWallYSize);
        Vector2 topWallSize = new Vector2(topWallXSize, topWallYSize);
        // Create the walls
        GameObject leftWall = new GameObject(Vector2.ZERO, leftWallSize, wallPic);
        GameObject rightWall = new GameObject(new Vector2(windowDimensions.x(),
                windowDimensions.y()), rightWallSize, wallPic);
        GameObject topWall = new GameObject(Vector2.ZERO, topWallSize, wallPic);
        leftWall.setCenter(new Vector2(0, windowDimensions.y() * HALF));
        rightWall.setCenter(new Vector2(windowDimensions.x(), windowDimensions.y() * HALF));
        topWall.setCenter(new Vector2(windowDimensions.x() * HALF, 0));
        // Add walls to the game objects
        this.gameObjects().addGameObject(leftWall);
        this.gameObjects().addGameObject(rightWall);
        this.gameObjects().addGameObject(topWall);
    }
    /**
     * Adds a GameObject representing numeric life to the game with the specified layer.
     * The GameObject is typically associated with a TextRenderable for displaying the numeric life
     * count.
     *
     * @param text The GameObject representing numeric life to be added.
     *             This GameObject may include a TextRenderable for displaying the numeric life count.
     */
    public void addNumericLife(GameObject text) {
        this.gameObjects().addGameObject(text, Layer.BACKGROUND);
    }



    /**
     * The main method to start the BrickerGameManager.
     * Accepts command line arguments for custom brick configurations.
     *
     * @param args Command line arguments for the brick configuration.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            new BrickerGameManager("Bricker", new Vector2(700, 500)).run();
        } else {
            int arg0AsInt = Integer.parseInt(args[0]);
            int arg1AsInt = Integer.parseInt(args[1]);
            new BrickerGameManager("Bricker", new Vector2(700, 500), arg0AsInt,
                    arg1AsInt).run();
        }
    }
    /**
     * Adds a graphic life GameObject to the game with the specified layer.
     * The GameObject is added to the background layer.
     *
     * @param gameObject The graphic life GameObject to be added.
     */
    public void addGraphicLife(GameObject gameObject) {
        this.gameObjects().addGameObject(gameObject, Layer.BACKGROUND);
    }

    /**
     * Removes a graphic life GameObject from the game with the specified layer.
     * The GameObject is removed from the background layer.
     *
     * @param gameObject The graphic life GameObject to be removed.
     */
    public void removeGraphicLife(GameObject gameObject) {
        this.gameObjects().removeGameObject(gameObject, Layer.BACKGROUND);
    }

}



