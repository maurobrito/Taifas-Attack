package toweroffense.highscoresserver;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import toweroffense.server.IServerCallback;

@SuppressWarnings("serial")
public class PlayerList extends JPanel {
	// DIMENSIONS
	private static Dimension PANEL_SIZE = new Dimension(150,300);
	private static Dimension LIST_SIZE = new Dimension(110,230);
	
	private JList<String> playerList;
	private DefaultListModel<String> listModel;
	
	private JScrollPane listScrl;
	
	private JButton viewInfoButton;
	
	private IServerCallback callback;
	
	public PlayerList() {
		super();
	}
	
	public void buildPanel() {
		setMaximumSize(PANEL_SIZE);
		setMinimumSize(PANEL_SIZE);
		setPreferredSize(PANEL_SIZE);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		listModel = new DefaultListModel<>();
		
		playerList = new JList<>(listModel);
		listScrl = new JScrollPane(playerList);
		listScrl.setMinimumSize(LIST_SIZE);
		listScrl.setSize(LIST_SIZE);
		listScrl.setPreferredSize(LIST_SIZE);
		add(listScrl);
		
		viewInfoButton = new JButton("View Info");
		viewInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playerList.getSelectedIndex() == -1)
					return;
				
				if(callback == null) {
					System.err.println("ServerCallback was not set.");
					return;
				}
				
				String[] infos = callback.getInfo(playerList.getSelectedIndex());
				
				if(infos == null)
					return;
				
				String msg = "";
				for(String s : infos) {
					msg += s + "\n";
				}
				JOptionPane.showMessageDialog(getParent(), msg, "Score Info", JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(viewInfoButton);
	}
	
	public void setPlayerList(List<String> names) {
		listModel = new DefaultListModel<>();
		Iterator<String> it = names.iterator();
		
		while(it.hasNext()) {
			listModel.addElement(it.next());
		}
		
		playerList.setModel(listModel);
	}

	public void setCallback(IServerCallback callback) {
		this.callback = callback;
	}
}
