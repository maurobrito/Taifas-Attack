package minionMenu;

public interface IClientCallback {
	void connected();
	void Error(String msg);
	void highscoreReceived(String substring);
	void connect();
	void finish();
	void sendScore(String name, int score);
	boolean getConnected();
}
