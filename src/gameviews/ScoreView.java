package gameviews;

import interfaces.IGameWindow;
import interfaces.ILabel;

import java.awt.Color;
import java.awt.Font;

public class ScoreView {
	private ILabel label;
	
	public ScoreView(IGameWindow gameWindow) {
		label = gameWindow.createLabel("", new Font("Century Gothic", Font.PLAIN, 24));
	}
	
	public void draw(int score) {
		
		label.setColor(Color.ORANGE);
		label.setText("Score: " + score);
		label.drawCentered(400, 40);
	}
}
