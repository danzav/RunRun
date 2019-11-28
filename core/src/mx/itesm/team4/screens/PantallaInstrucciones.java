package mx.itesm.team4.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.team4.RunRun;

public class PantallaInstrucciones extends Pantalla{

    private final RunRun juego;
    //camara
    private OrthographicCamera camara;
    private Viewport vista;

    //batch
    private SpriteBatch batch;

    //fondo
    private Texture texturaFondo;

    //escena de menu (botones)
    private Stage escenaAD;

    //texto Acerca de
    //private TextoSprite textoSprite;


    public PantallaInstrucciones(RunRun inicio) {
        this.juego=inicio;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearMenu();
    }

    /*private void crearTextoSprite() {
        Texture texturaTexto = new Texture("Imagenes_Final/Pantallas/Instruciones_00.png");
        textoSprite = new TextoSprite(texturaTexto,0,0);
    }*/

    private void crearMenu() {
        escenaAD=new Stage(vista);
        //boton Regresar a Juego
        TextureRegionDrawable btnRegresar=new TextureRegionDrawable(new TextureRegion(new Texture("Return.png")));
        TextureRegionDrawable btnRegresarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Return_Push.png")));
        ImageButton btnRegreso= new ImageButton(btnRegresar,btnRegresarOprimido);
        btnRegreso.setPosition(ANCHO - btnRegresar.getMinWidth() - 80,ALTO-btnRegresar.getMinHeight()- 55);
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

        escenaAD.addActor(btnRegreso);


        Gdx.input.setInputProcessor(escenaAD);
    }

    private void cargarTexturas() {
        texturaFondo=new Texture("FondoAD.png");
    }

    private void configurarVista() {
        camara=new OrthographicCamera();
        camara.position.set(ANCHO/2, ALTO/2, 0);
        camara.update();

        vista= new StretchViewport(ANCHO,ALTO, camara);

        batch= new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch escala de acuerdo a la vista
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        //textoSprite.render(batch);
        batch.end();

        escenaAD.draw();
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();//liberar memoria

    }

}
