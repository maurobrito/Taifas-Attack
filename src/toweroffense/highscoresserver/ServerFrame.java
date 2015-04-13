package toweroffense.highscoresserver;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import toweroffense.server.IServerCallback;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame {
	LogViewer logViewer;
	PlayerList pList;
	CommandTable commands;
	public ServerFrame() {
		super();
	}
	
	public void buildFrame() {
		setLayout(new BorderLayout());
		
		commands = new CommandTable();
		add(commands, BorderLayout.PAGE_START);
		
		logViewer = new LogViewer();
		logViewer.buildPanel();
		add(logViewer, BorderLayout.CENTER);
		
		pList = new PlayerList();
		pList.buildPanel();
		add(pList, BorderLayout.LINE_END);
		
		pack();
		setResizable(false);
		setVisible(true);
	}

	public void setCallback(IServerCallback server) {
		pList.setCallback(server);
		commands.addEventsListener(server);
	}
	
	public void println(String msg) {
		logViewer.showMessage(msg);
	}
	
	public void setPlayerList(List<String> names) {
		pList.setPlayerList(names);
	}
	
	public void setOnline() {
		commands.setOnline();
	}
	
	public void setOffline() {
		commands.setOffline();
	}
}
