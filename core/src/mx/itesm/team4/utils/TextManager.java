package mx.itesm.team4.utils;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

import java.text.DecimalFormat;

public class TextManager {
    static BitmapFont font; // we draw the text to the screen using this variable

    // viewport width and height
    static float width,height;
    public static int multiplier = 3;
    public static DecimalFormat scoreD = new DecimalFormat("#.#####");

    public static void initialize(float width,float height){
        font = new BitmapFont();
        TextManager.width = 1300f;
        TextManager.height= 980f;
        //set the font color to red
        font.setColor(Color.PINK);
        //scale the font size according to screen width
        font.getData().setScale(width/100f);
        font.setUseIntegerPositions(false);
    }

    public static void displayMessage(SpriteBatch batch){
        float fontWidth =  new GlyphLayout(font, ""+GameManager.score).width; // get the width of the text being displayed
        batch.begin();
        //top the score display at top right corner
        font.draw(batch, ""+scoreD.format(GameManager.score*multiplier), (width+100f) - fontWidth - width/15f,height*0.70f);
        batch.end();
    }

    public static void displayFinal(float score, float highscore, SpriteBatch batch){
        float fontWidth =  new GlyphLayout(font, ""+score).width; // get the width of the text being displayed
        batch.begin();
        //top the score display at top right corner
        font.draw(batch, "Score actual: "+scoreD.format(score*multiplier), (width-540) - fontWidth - width/15f,height*0.46f);
        font.draw(batch, "HighScore: "+scoreD.format(highscore), (width-540) - fontWidth - width/15f,height*0.37f);

        batch.end();
    }
}
