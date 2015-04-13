package toweroffense.server;

import java.util.List;

public interface IServerCallback {
	String[] getInfo(int selectedIndex);
	void startServer();
	void closeServer();
	void showMessage(String string);
	void highscoreReceived(HighScoresClientConn conn, String highscore);
	List<HighscoreEntry> getHighscores();
}
