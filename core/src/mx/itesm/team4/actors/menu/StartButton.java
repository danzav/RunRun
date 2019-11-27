package mx.itesm.team4.actors.menu;

import com.badlogic.gdx.math.Rectangle;

import mx.itesm.team4.enums.GameState;
import mx.itesm.team4.utils.Constants;
import mx.itesm.team4.utils.GameManager;

public class StartButton extends GameButton {

    public interface StartButtonListener {
        public void onStart();
    }

    private StartButtonListener listener;

    public StartButton(Rectangle bounds, StartButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.BIG_PLAY_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManager.getInstance().getGameState() != GameState.OVER) {
            remove();
        }
    }

    @Override
    public void touched() {
        listener.onStart();
    }

}
