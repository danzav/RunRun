package mx.itesm.team4.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.text.DecimalFormat;

import mx.itesm.team4.enums.Difficulty;
import mx.itesm.team4.enums.GameState;

public class GameManager implements GameEventListener {
    private static GameManager ourInstance = new GameManager();

    public static final String PREFERENCES_NAME = "preferences";
    private static final String MAX_SCORE_PREFERENCE = "max_score";
    private static final String ACHIEVEMENT_COUNT_PREFERENCE_SUFFIX = "_count";
    private static final String ACHIEVEMENT_UNLOCKED_PREFERENCE_SUFFIX = "_unlocked";
    private GameState gameState;
    private Difficulty difficulty;
    private GameEventListener gameEventListener;
    public static float score = 1.0f;
    public DecimalFormat scoreD = new DecimalFormat("#.##");

    public static void getScore(){
        while (score < 10000){
            score += Gdx.graphics.getDeltaTime()*2;
        }
    }

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
        gameState = GameState.OVER;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isMaxDifficulty() {
        return difficulty == Difficulty.values()[Difficulty.values().length - 1];
    }

    public void resetDifficulty() {
        setDifficulty(Difficulty.values()[0]);
    }

    public void setGameEventListener(GameEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
    }

    @Override
    public void displayLeaderboard() {
        gameEventListener.displayLeaderboard();
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFERENCES_NAME);
    }

    public void saveScore(int score) {
        Preferences preferences = getPreferences();
        int maxScore = preferences.getInteger(MAX_SCORE_PREFERENCE, 0);
        if (score > maxScore) {
            preferences.putInteger(MAX_SCORE_PREFERENCE, score);
            preferences.flush();
        }
    }




}