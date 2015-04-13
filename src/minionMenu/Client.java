package minionMenu;


public class Client implements IClientCallback {
	private IClientView view;
	private ConnectionThread conn;
	private boolean connected = false;
	public Client(IClientView view) {
		this.view = view;
	}
	
	@Override
	public void connect() {
		conn = new ConnectionThread(8000, "localhost", this);
		conn.start();
	}

	@Override
	public void connected() {
		view.setStatus("Conectado. Aguardando...");
		connected = true;
	}

	@Override
	public void Error(String msg) {
		view.setStatus("Erro: " + msg);
		connected = false;
	}

	@Override
	public void highscoreReceived(String substring) {
		String txt = "<html><p align=center>";
		txt += "HIGHSCORES<br>";
		String[] strings = substring.trim().split(" ");
		if(strings.length > 2 && (strings.length % 2 == 0)) {
			for(int i = 0; i < strings.length; i += 2) {
				txt += strings[i] + "   " + strings[i+1] + "<br>";
			}
		}
		txt +="</html>";
		view.setStatus(txt);
	}

	@Override
	public void finish() {
		conn.closeConnection();
	}

	@Override
	public void sendScore(String name, int score) {
		conn.sendMessage("HS:" + name + " " + score);
	}

	@Override
	public boolean getConnected() {
		return connected;
	}
}
