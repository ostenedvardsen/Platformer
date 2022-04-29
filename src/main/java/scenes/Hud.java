package scenes;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
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
	public Stage stage;
	private Viewport viewport;
	public boolean initializedHud = false;
	
	ArrayList<Player> allPlayers;
	private Table table;
	private static Label scoreLabel;
	private static Label gameOverLabel;
	private static Label newGameLabel;
	private static Label gameDoneLabel;
	private static Label congratulations;
	
	public Hud(SpriteBatch sb, ArrayList<Player> players) {
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
		stage = new Stage(viewport, sb);

		table = new Table();
		table.top();
		table.setFillParent(true);
		allPlayers = players;
		String allScores = scoreMethod();

		scoreLabel = new Label(allScores, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		table.add(scoreLabel).expandX();
		table.add().row();
		gameOverLabel = new Label("GAME OVER", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		newGameLabel = new Label("Press 'R' to go back to Main Menu", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		gameDoneLabel = new Label("GAME DONE", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		congratulations = new Label("CONGRATULATIONS", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
	
		stage.addActor(table);
		initializedHud = true;
	}

	public void updateHud(ArrayList<Player> players){
		allPlayers = players;
		String newAllScores = scoreMethod();
		setHudText(newAllScores);
		
		if (players.size() == 0) gameOver();
		
	}

	private String scoreMethod(){
		String totalScoreString = "";
		for(Player player: allPlayers){
			int currentPlayer = player.getID();
			totalScoreString += "Score player " + currentPlayer + ": ";
			totalScoreString += String.format("%06d", player.getScore());
			totalScoreString += "  HP:  " + player.getHealth();
			totalScoreString += "      ";
		}
		return totalScoreString;
	}

	private void setHudText (String totalScoreString){
		scoreLabel.setText(totalScoreString);
	}

	public void gameOver() {
		table.clear();
		table.center();
		table.add(gameOverLabel);
		gameOverLabel.setFontScaleX(5);
		gameOverLabel.setFontScaleY(5);
		table.add().row();
		table.add(newGameLabel);
		newGameLabel.setFontScale(2, 2);
	}
	
	public void gameDone() {
		table.clear();
		table.center();
		table.add(congratulations);
		congratulations.setFontScale(4, 4);
		table.add().row();
		table.add(gameDoneLabel);
		gameDoneLabel.setFontScale(5, 5);
		table.add().row();
		table.add(newGameLabel);
		newGameLabel.setFontScale(3, 3);

	}
	
}
