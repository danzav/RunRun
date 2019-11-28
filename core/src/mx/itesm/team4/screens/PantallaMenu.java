package mx.itesm.team4.screens;

import com.badlogic.gdx.Gdx;
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

import mx.itesm.team4.RunRun;
import mx.itesm.team4.stages.GameStage;

public class PantallaMenu extends Pantalla {
    private final RunRun juego;
    //fondo
    private Texture texturaFondo;


    //escena de menu (botones)
    private Stage escenaMenu;



    public PantallaMenu(RunRun inicio) {
        this.juego=inicio;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearMenu();
    }

    private void crearMenu() {
        escenaMenu=new Stage(vista);
        //boton Inicio Juego
        TextureRegionDrawable btnJugar=new TextureRegionDrawable(new TextureRegion(new Texture("Play_Boton_00.png")));
        TextureRegionDrawable btnJugarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Play_Push_Boton_00.png")));
        ImageButton btnInicioJuego= new ImageButton(btnJugar,btnJugarOprimido);
        btnInicioJuego.setPosition(50,ALTO/2);
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

        //boton acerca de
        TextureRegionDrawable btnAcerDe=new TextureRegionDrawable(new TextureRegion(new Texture("acercade_00.png")));
        TextureRegionDrawable btnAcerDeOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Acercade_push.png")));
        ImageButton btnInicioAcerca= new ImageButton(btnAcerDe,btnAcerDeOprimido);
        btnInicioAcerca.setPosition(300,ALTO/2);

        btnInicioAcerca.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                juego.setScreen(new PantallaAcercaDe(juego));

            }
        });

        //instrucciones boton
        TextureRegionDrawable btnInst=new TextureRegionDrawable(new TextureRegion(new Texture("infromacion_bnt_00.png")));
        TextureRegionDrawable btnInstOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("infromacion_push_Bnt.png")));
        ImageButton btnInicioInst= new ImageButton(btnInst,btnInstOprimido);
        btnInicioInst.setPosition(700,ALTO/2);

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
            btnMute.setPosition(1000,ALTO/2);

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
            escenaMenu.addActor(btnMute);
        }
        else{
            ImageButton btnMute= new ImageButton(btnMuteOn,btnMutePress,btnMuteOFF);
            btnMute.setPosition(1000,ALTO/2);

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
            escenaMenu.addActor(btnMute);
        }



        escenaMenu.addActor(btnInicioJuego);
        escenaMenu.addActor(btnInicioAcerca);
        escenaMenu.addActor(btnInicioInst);

        Gdx.input.setInputProcessor(escenaMenu);
    }


    private void cargarTexturas() {
        texturaFondo=new Texture("Fondo.png");
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
        //batch
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

        escenaMenu.draw();
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

