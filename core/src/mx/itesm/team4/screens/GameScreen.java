package mx.itesm.team4.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import java.awt.Color;
import java.awt.Label;
import java.text.DecimalFormat;

import mx.itesm.team4.RunRun;
import mx.itesm.team4.actors.GameActor;
import mx.itesm.team4.actors.Runner;
import mx.itesm.team4.actors.Score;
import mx.itesm.team4.stages.GameStage;
import mx.itesm.team4.utils.BodyUtils;
import mx.itesm.team4.utils.Constants;
import mx.itesm.team4.utils.GameManager;
import mx.itesm.team4.utils.TextManager;


public class GameScreen extends Pantalla implements Screen {

    private GameStage stage;
    Stage score;
    Label scoreLabel;
    private RunRun juego;
    public static int score1;
    public static int multiplier;
    private static float timer = 1;
    private Runner runner;
    private GameOverScreen gameOverScreen;


    public GameScreen(RunRun juego) {
        this.juego = juego;
        stage = new GameStage(juego);

    }


    @Override
    public void render(float delta) {
        //Clear the screen
        borrarPantalla();
        timer += (Gdx.graphics.getDeltaTime()/100000000);

        //Update the stage
        stage.draw();
        stage.act(delta);
        score1 = 1;
        multiplier = 5;
        TextManager.initialize(300f,250f);
        SpriteBatch batch = new SpriteBatch();
        TextManager.displayMessage(batch);
        GameManager.score += multiplier * delta;
        Constants.ENEMY_LINEAR_VELOCITY.scl(((delta*.010f)+(timer*1.0000000000000000000000000000000000000000000000000000000000001f)),0);
    }




    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
