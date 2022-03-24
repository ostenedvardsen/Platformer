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

import entity.Player;
import java.util.ArrayList;

public class Hud {
	/**
	 * create a new stage and a viewport to keep the Hud locked, while the eventually the screen
	 * can move independently with the character.
	 */
	public Stage stage;
	private Viewport viewport;
	public boolean initializedHud = false;

	ArrayList<Player> allPlayers;

	//Scene2D Widgets
	private static Label scoreLabel;
	private Label scoreDisplayLabel;

	
	public Hud(SpriteBatch sb, ArrayList<Player> players) {
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
		stage = new Stage(viewport, sb);

		Table table = new Table(); //Creating our table of widgets
		table.top();
		table.setFillParent(true); // makes the Table to the size of our stage.

		allPlayers = players;
		String allScores = scoreMethod();

		scoreLabel = new Label(allScores, new Label.LabelStyle(new BitmapFont(), Color.BLACK));

		// table.add(scoreDisplayLabel).expandX().padTop(10); //Adding a widget to the table
		// table.row(); //New row
		table.add(scoreLabel).expandX();
		
		stage.addActor(table);
		initializedHud = true;
	}

	public void updateHud(){
		String newAllScores = scoreMethod();
		setHudText(newAllScores);
	}

	private String scoreMethod(){
		String totalScoreString = "";
		for(Player player: allPlayers){
			int currentPlayer = allPlayers.indexOf(player) + 1;
			totalScoreString += "Score player " + currentPlayer + ": ";
			totalScoreString += String.format("%06d", player.getScore());
			totalScoreString += "      ";
		}
		return totalScoreString;
	}

	private void setHudText (String totalScoreString){
		scoreLabel.setText(totalScoreString);
	}

}
