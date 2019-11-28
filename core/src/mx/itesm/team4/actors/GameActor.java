package mx.itesm.team4.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.math.Rectangle;

import mx.itesm.team4.box2d.UserData;
import mx.itesm.team4.enums.Difficulty;
import mx.itesm.team4.utils.Constants;

public abstract class GameActor extends Actor {
    protected Body body;
    protected UserData userData;
    protected Rectangle screenRectangle;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
        screenRectangle = new Rectangle();
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() != null) {
            updateRectangle();
        } else {
            // This means the world destroyed the body (enemy or runner went out of bounds)
            remove();
        }

    }
    public abstract UserData getUserData();

    private void updateRectangle() {
        screenRectangle.x = (int) transformToScreen(body.getPosition().x - userData.getWidth() / 2);
        screenRectangle.y = (int) transformToScreen(body.getPosition().y - userData.getHeight() / 2);
        screenRectangle.width = (int) transformToScreen(userData.getWidth());
        screenRectangle.height = (int) transformToScreen(userData.getHeight());
    }

    protected float transformToScreen(float n) {
        return Constants.WORLD_TO_SCREEN * n;
    }

}
