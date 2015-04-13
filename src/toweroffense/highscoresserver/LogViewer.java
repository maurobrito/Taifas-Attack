package toweroffense.highscoresserver;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class LogViewer extends JPanel {
	// DIMENSIONS
	private static Dimension PANEL_SIZE = new Dimension(355,300);
		
	private JTextArea log;
	private JScrollPane scrollPane1;
	
	public LogViewer() {
		super();
	}
	
	public void buildPanel() {
		setMaximumSize(PANEL_SIZE);
		setMinimumSize(PANEL_SIZE);
		setPreferredSize(PANEL_SIZE);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		log = new JTextArea();
		DefaultCaret caret = (DefaultCaret)log.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		log.setColumns(30);
		log.setLineWrap(true);
		log.setRows(18);
		log.setWrapStyleWord(true);
		log.setEditable(false);
		//add(log);
		scrollPane1 = new JScrollPane(log);
		add(scrollPane1);
	}
	
	public void showMessage(String msg) {
		log.append(msg + "\n");
		
	}
}
