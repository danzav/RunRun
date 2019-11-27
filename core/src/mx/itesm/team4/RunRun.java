package mx.itesm.team4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mx.itesm.team4.screens.GameScreen;
import mx.itesm.team4.screens.PantallaTitulo;
import mx.itesm.team4.utils.AssetsManager;

public class RunRun extends Game {
	private final AssetManager manager=new AssetManager();
	private Music audioFondo;
	
	@Override
	public void create () {
		setScreen(new PantallaTitulo(this));

		manager.load("na/cancionFina.mp3",Music.class);
		manager.finishLoading();
		audioFondo=manager.get("na/cancionFina.mp3");
		audioFondo.setLooping(true);
		audioFondo.setVolume(1F);
		audioFondo.play();
	}

	public AssetManager getManager(){
		return manager;
	}

	public void playMusic(){
		audioFondo.play();
	}
	public void stopMusic(){
		audioFondo.stop();
	}
	public boolean isPlaying(){
		return audioFondo.isPlaying();
	}

}
