package toweroffense.highscoresserver;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import toweroffense.server.IServerCallback;

@SuppressWarnings("serial")
public class CommandTable extends JPanel {
	private JButton startServer;
	private JButton closeServer;
	
	public CommandTable() {
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER));
		startServer = new JButton("Start Server");
		startServer.setEnabled(false);
		add(startServer);
		closeServer = new JButton("Close Server");
		closeServer.setEnabled(false);
		add(closeServer);
	}
	
	public void addEventsListener(final IServerCallback callback) {
		startServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				callback.startServer();
				
			}
		});
		
		closeServer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				callback.closeServer();
				
			}
		});
		
		startServer.setEnabled(true);
	}

	public void setOnline() {
		startServer.setEnabled(false);
		closeServer.setEnabled(true);
	}
	
	public void setOffline() {
		startServer.setEnabled(true);
		closeServer.setEnabled(false);
	}
}
