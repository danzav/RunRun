package mx.itesm.team4.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static final int APP_WIDTH = 1280;
    public static final int APP_HEIGHT = 720;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 90f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final float RUNNER_X = 2;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 2f;
    public static final float RUNNER_HEIGHT = 2.3f;

    public static final String BACKGROUND_IMAGE_PATH = "Fondo largo.png";
    public static final String GROUND_IMAGE_PATH = "ground.png";
    public static final float WORLD_TO_SCREEN = 32;
    public static final String FONT_NAME = "roboto_bold.ttf";
    public static final String PLAY_REGION_NAME = "Niveles_Play";
    public static final String PAUSE_REGION_NAME = "Pantalla_Pausa";
    public static final String BIG_PLAY_REGION_NAME = "Niveles_Play";
    public static final String SPRITES_ATLAS_PATH = "characters.txt";

    public static float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_DODGE_X = 2f;
    public static final float RUNNER_DODGE_Y = 1.5f;
    public static final float RUNNER_GRAVITY_SCALE = 3f;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);
    public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;

    public static final float ENEMY_X = 40f;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
    public static final float RUNNING_LONG_ENEMY_Y = 2f;
    public static final float FLYING_ENEMY_Y = 3f;
    public static final float time = 0f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-20f, 0);

    public static final String[] RUNNER_RUNNING_REGION_NAMES = new String[] {"Personaje_run1", "Personaje_run3"};
    public static final String RUNNER_DODGING_REGION_NAME = "Personaje_run2";
    public static final String RUNNER_HIT_REGION_NAME = "Personaje_run4";
    public static final String RUNNER_JUMPING_REGION_NAME = "Personaje_run5";

    public static final String[] RUNNING_SMALL_ENEMY_REGION_NAMES = new String[] {"Villano_nivel1_1", "Villano_nivel1_2"};
    public static final String[] FLYING_SMALL_ENEMY_REGION_NAMES = new String[] {"Villano_nivel2_1", "Villano_nivel2_2"};
    public static final String[] RUNNING_LONG_ENEMY_REGION_NAMES = new String[] {"Villano_nivel3_1", "Villano_nivel3_2"};

    public static final String BACKGROUND_ASSETS_ID = "background";
    public static final String GROUND_ASSETS_ID = "ground";
    public static final String RUNNER_RUNNING_ASSETS_ID = "runner_running";
    public static final String RUNNER_DODGING_ASSETS_ID = "runner_dodging";
    public static final String RUNNER_HIT_ASSETS_ID = "runner_hit";
    public static final String RUNNER_JUMPING_ASSETS_ID = "runner_jumping";
    public static final String RUNNING_SMALL_ENEMY_ASSETS_ID = "running_small_enemy";
    public static final String RUNNING_LONG_ENEMY_ASSETS_ID = "running_long_enemy";
    public static final String FLYING_SMALL_ENEMY_ASSETS_ID = "flying_small_enemy";





}
