package scenes;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hud {
	/**
	 * create a new stage and a viewport to keep the Hud locked, while the eventually the screen
	 * can move independently with the character.
	 */
	public Stage stage;
	private Viewport viewport;
	
	private static Integer score;
	
	//Scene2D Widgets
	private static Label scoreLabel;
	private Label scoreDisplayLabel;
	
	
	public Hud(SpriteBatch sb) {
		score = 0;
		
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
		stage = new Stage(viewport, sb);
	
		
		Table table = new Table(); //Creating our table of widgets
		table.top();
		table.setFillParent(true); // makes the Table to the size of our stage.
		
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		scoreDisplayLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		
		table.add(scoreDisplayLabel).expandX().padTop(10); //Adding a widget to the table
		table.row(); //New row
		table.add(scoreLabel).expandX(); 
		
		stage.addActor(table); 
	}
	
	//updates the score
	public static void addScore(int value) {
		score+=value;
		scoreLabel.setText(String.format("%06d", score));
	}
	
	public static void removeScore(int value) {
		score-=value;
		scoreLabel.setText(String.format("%06d", score));
	}

	public static void progressStage(){
		addScore(1000);
	}
}
