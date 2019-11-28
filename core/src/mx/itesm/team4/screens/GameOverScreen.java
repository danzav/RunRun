package mx.itesm.team4.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.soap.Text;

import mx.itesm.team4.RunRun;
import mx.itesm.team4.stages.GameStage;
import mx.itesm.team4.utils.GameManager;
import mx.itesm.team4.utils.TextManager;

public class GameOverScreen extends Pantalla /*Implements Screen*/ {

    private Viewport viewport;
    private Stage stage;
    private RunRun juego;

    private OrthographicCamera camara;
    private Viewport vista;

    //fondo
    private Texture texturaFondo;
    private Texture texturaGameOver;

    //escena de menu (botones)
    private Stage escenaGameOver;

    //scores
    private Preferences highScore=Gdx.app.getPreferences("highScore");
    private float score;

    public GameOverScreen(RunRun inicio){
        this.juego = inicio;
    }

    private void configurarVista() {
        camara=new OrthographicCamera();
        camara.position.set(ANCHO/2, ALTO/2, 0);
        camara.update();
        vista= new StretchViewport(ANCHO, ALTO, camara);
        batch= new SpriteBatch();
        //SpriteBatch batch = new SpriteBatch();
    }

    private void cargarTexturas() {
        texturaFondo=new Texture("Fondo_Game_Over.png");
        texturaGameOver=new Texture("Pantalla_score_00.png");
    }
    private void crearMenu() {
        escenaGameOver=new Stage(vista);
        //boton Regresar a Juego
        TextureRegionDrawable btnRegresar=new TextureRegionDrawable(new TextureRegion(new Texture("Home_Boton_00.png")));
        TextureRegionDrawable btnRegresarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Home_Push_Boton_00.png")));
        ImageButton btnRegreso= new ImageButton(btnRegresar,btnRegresarOprimido);
        btnRegreso.setPosition(ANCHO/2-btnRegreso.getWidth()-20,btnRegreso.getHeight()+100);
        //Siguientes Botones
        //Evento boton
        btnRegreso.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        TextureRegionDrawable btnJugar=new TextureRegionDrawable(new TextureRegion(new Texture("Play_Boton_00.png")));
        TextureRegionDrawable btnJugarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Play_Push_Boton_00.png")));
        ImageButton btnInicioJuego= new ImageButton(btnJugar,btnJugarOprimido);
        btnInicioJuego.setPosition(ANCHO/2+btnInicioJuego.getHeight()-30,btnInicioJuego.getHeight()+100);
        //Siguientes Botones
        //Evento boton
        btnInicioJuego.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                juego.setScreen(new GameScreen(juego));

            }
        });

        //instrucciones boton
        TextureRegionDrawable btnInst=new TextureRegionDrawable(new TextureRegion(new Texture("info_bnt_00.png")));
        TextureRegionDrawable btnInstOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("info_push_Bnt.png")));
        ImageButton btnInicioInst= new ImageButton(btnInst,btnInstOprimido);
        btnInicioInst.setPosition(ANCHO/2+btnInicioInst.getWidth()+150,btnInicioInst.getHeight()+100);

        btnInicioInst.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                juego.setScreen(new PantallaInstrucciones(juego));

            }
        });

        //boton settings
        TextureRegionDrawable btnMuteOFF=new TextureRegionDrawable(new TextureRegion(new Texture("Musica_Boton_00.png")));
        TextureRegionDrawable btnMuteOn= new TextureRegionDrawable(new TextureRegion(new Texture("Mute_Boton_00.png")));
        TextureRegionDrawable btnMutePress= new TextureRegionDrawable(new TextureRegion(new Texture("Musica_Boton_Push_00.png")));
        if(juego.isPlaying()){
            ImageButton btnMute= new ImageButton(btnMuteOFF,btnMutePress,btnMuteOn);
            btnMute.setPosition(ANCHO-btnMute.getHeight()-20,ALTO-btnMute.getHeight());

            btnMute.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    if(juego.isPlaying()){
                        juego.stopMusic();
                    }
                    else{
                        juego.playMusic();
                    }
                }
            });
            escenaGameOver.addActor(btnMute);
        }
        else{
            ImageButton btnMute= new ImageButton(btnMuteOn,btnMutePress,btnMuteOFF);
            btnMute.setPosition(ANCHO-btnMute.getHeight()-20,ALTO-btnMute.getHeight());

            btnMute.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    if(juego.isPlaying()){
                        juego.stopMusic();
                    }
                    else{
                        juego.playMusic();
                    }
                }
            });
            escenaGameOver.addActor(btnMute);
        }


        escenaGameOver.addActor(btnInicioInst);
        escenaGameOver.addActor(btnInicioJuego);
        escenaGameOver.addActor(btnRegreso);


        Gdx.input.setInputProcessor(escenaGameOver);
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearMenu();
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch escala de acuerdo a la vista
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.draw(texturaGameOver,ANCHO-texturaGameOver.getWidth()-100,ALTO-texturaGameOver.getHeight());
        batch.end();
        TextManager.initialize(360,640);
        //TextManager.displayMessage(batch);
        TextManager.displayFinal(GameStage.scoreActual,highScore.getFloat("highScore"),batch);

        escenaGameOver.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
