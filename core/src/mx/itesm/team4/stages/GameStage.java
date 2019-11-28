package mx.itesm.team4.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.font.GraphicAttribute;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import javax.xml.soap.Text;

import mx.itesm.team4.RunRun;
import mx.itesm.team4.actors.Background;
import mx.itesm.team4.actors.Enemy;
import mx.itesm.team4.actors.Ground;
import mx.itesm.team4.actors.Runner;
import mx.itesm.team4.actors.Score;
import mx.itesm.team4.actors.menu.PauseButton;
import mx.itesm.team4.actors.menu.StartButton;
import mx.itesm.team4.enums.Difficulty;
import mx.itesm.team4.enums.GameState;
import mx.itesm.team4.screens.GameOverScreen;
import mx.itesm.team4.screens.GameScreen;
import mx.itesm.team4.screens.Pantalla;
import mx.itesm.team4.screens.PantallaMenu;
import mx.itesm.team4.screens.PantallaTitulo;
import mx.itesm.team4.utils.BodyUtils;
import mx.itesm.team4.utils.Constants;
import mx.itesm.team4.utils.GameManager;
import mx.itesm.team4.utils.TextManager;
import mx.itesm.team4.utils.WorldUtils;

import static mx.itesm.team4.utils.TextManager.multiplier;
import static mx.itesm.team4.utils.TextManager.scoreD;

public class GameStage extends Stage implements ContactListener {
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private World world;
    private Ground ground;
    private Runner runner;
    private PauseButton pauseButton;
    private StartButton startButton;
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;
    private RunRun juego = new RunRun();

    private OrthographicCamera camera;
    private Viewport vista;
    private Stage botonesPausa;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;
    private Score score;
    private float totalTimePassed;
    private boolean tutorialShown;


    public static int score1;


    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        setUpStageBase();
        setupTouchControlAreas();
        setupCamera();
        //Gdx.input.setInputProcessor(this);
        //setUpMainMenu();
        //onGameOver();
        //setUpPause();
        score1 = 0;
        TextManager.initialize(200,200);
        SpriteBatch batch = new SpriteBatch();
        TextManager.displayMessage(batch);


    }
    public GameStage(RunRun inicio){
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        this.juego =inicio;
        setUpStageBase();
        setupTouchControlAreas();
        setupCamera();
        //Gdx.input.setInputProcessor(this);
        //setUpMainMenu();
        //onGameOver();
        //setUpPause();
        score1 = 0;
        TextManager.initialize(200,200);
        SpriteBatch batch = new SpriteBatch();
        TextManager.displayMessage(batch);

    }

    private void setUpStageBase() {
        setUpWorld();
        setUpFixedMenu();

    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
        setUpRunner();
        createEnemy();
        setUpScore();
        //setUpPause();
    }

    private void setUpMainMenu() {
        setUpStart();
        //setUpLeaderboard();
        //setUpAbout();
        //setUpShare();
        //setUpAchievements();
    }

    private void setUpBackground() {
        addActor(new Background());
    }

    private void setUpFixedMenu() {
        setUpScore();
        //setUpPause();
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.hit();
            System.out.println("game over");
            System.out.println(scoreD.format(GameManager.score*multiplier));
            juego.setScreen(new PantallaMenu(juego));

        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b))) {
            runner.landed();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setUpPause() {
        Rectangle pauseButtonBounds = new Rectangle(getCamera().viewportWidth / 64,
                getCamera().viewportHeight * 1 / 2, getCamera().viewportHeight / 10,
                getCamera().viewportHeight / 10);
        pauseButton = new PauseButton(pauseButtonBounds, new GamePauseButtonListener());
        addActor(pauseButton);
    }

    private class GameStartButtonListener implements StartButton.StartButtonListener {

        @Override
        public void onStart() {
            clear();
            setUpStageBase();
            //setUpCharacters();
            setUpPause();
            //setUpTutorial();
            onGameResumed();
        }

    }

    private void setUpStart() {
        Rectangle startButtonBounds = new Rectangle(getCamera().viewportWidth * 3 / 16,
                getCamera().viewportHeight / 4, getCamera().viewportWidth / 4,
                getCamera().viewportWidth / 4);
        startButton = new StartButton(startButtonBounds, new GameStartButtonListener());
        addActor(startButton);
    }

    private class GamePauseButtonListener implements PauseButton.PauseButtonListener {

        @Override
        public void onPause() {
            onGamePaused();
        }

        @Override
        public void onResume() {
            onGameResumed();
        }

    }


    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0,
                getCamera().viewportWidth / 2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameManager.getInstance().getGameState() == GameState.PAUSED) return;

        if (GameManager.getInstance().getGameState() == GameState.RUNNING) {
            totalTimePassed += delta;
            updateDifficulty();
        }

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
        //TODO: Implement interpolation

    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
            runner.dodge();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (runner.isDodging()) {
            runner.stopDodge();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
    }

    /**
     * Helper function to get the actual coordinates in my world
     * @param x
     * @param y
     */
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                createEnemy();
            }
            world.destroyBody(body);
        }
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(world));
        addActor(enemy);
    }

    private void setUpScore() {
        Rectangle scoreBounds = new Rectangle(getCamera().viewportWidth * 47 / 64,
                getCamera().viewportHeight * 57 / 64, getCamera().viewportWidth / 4,
                getCamera().viewportHeight / 8);
        score = new Score(scoreBounds);
        addActor(score);
    }

    private void updateDifficulty() {

        if (GameManager.getInstance().isMaxDifficulty()) {
            return;
        }

        Difficulty currentDifficulty = GameManager.getInstance().getDifficulty();

        if (totalTimePassed > GameManager.getInstance().getDifficulty().getLevel() * 5) {

            int nextDifficulty = currentDifficulty.getLevel() + 1;
            String difficultyName = "DIFFICULTY_" + nextDifficulty;
            GameManager.getInstance().setDifficulty(Difficulty.valueOf(difficultyName));

            runner.onDifficultyChange(GameManager.getInstance().getDifficulty());
            score.setMultiplier(GameManager.getInstance().getDifficulty().getScoreMultiplier());

        }

    }

    private void onGamePaused() {
        GameManager.getInstance().setGameState(GameState.PAUSED);
    }

    private void onGameResumed() {
        GameManager.getInstance().setGameState(GameState.RUNNING);
    }

    private void onGameOver() {
        GameManager.getInstance().setGameState(GameState.OVER);
        GameManager.getInstance().resetDifficulty();
        totalTimePassed = 0;
        //setUpMainMenu();
    }

    //private void onGameAbout() {
    //    GameManager.getInstance().setGameState(GameState.ABOUT);
    //clear();
    //    setUpStageBase();
    //    setUpGameLabel();
    //    setUpAboutText();
    //    setUpAbout();
    //}

}
