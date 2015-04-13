package minionMenu;

import gamelogic.minions.MinionFactory.MinionType;
import gamelogic.Game;
import gamelogic.MinionSystem;
import graphics2D.J2DGameWindow;
import interfaces.IGame;
import interfaces.IGameWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import toweroffense.server.HighScoresListener;

@SuppressWarnings({ "serial", "unused" })

public class MinionMenu extends JFrame 
implements WindowListener{
	
	private JList<Object> availableMinions;
	private JList<Object> selectedMinions;	
	private DefaultListModel<Object> availableMinionsModel;
	private DefaultListModel<Object> selectedMinionsModel;
	
	private Icon minionDisplay;
	private ImageIcon minionImage;
	
	//limits the number of minions that the player can choose
	private static int MINIONS_LIMIT = 8;
	private static int NUMBER_OF_ROWS = 20;
	private static int SCREEN_WIDTH = 640;
	private static int SCREEN_HEIGHT = 480;
	
	private JButton addButton;
	private JButton removeButton;
	private JButton finishButton;
	private JButton highscoresButton;
	private JPanel leftSide;
	private JPanel rightSide;
	private JPanel windowCenter;
	private JLabel imageHolder;
	private JLabel availableMinonsTitle;
	private JLabel selectedMinionsTitle;
	
	private MinionSystem minSys;
	private MinionType[] minions;
	
	private IGameWindow gameWindow;
	private IGame game;
	private MinionMenu minionMenu;
	
	public MinionMenu(){
		super("Minion Selection");
		buildUI();
		implementActionListeners();
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void buildUI() {
		// minions lists initializations and configuration
		availableMinonsTitle = new JLabel("Available Minions");
		selectedMinionsTitle = new JLabel("Selected Minions (max = 8)");
		
		availableMinionsModel = new DefaultListModel<>();
		selectedMinionsModel = new DefaultListModel<>();
		
		availableMinions = new JList<>(availableMinionsModel);	
		selectedMinions = new JList<>(selectedMinionsModel);
		
		setLayout(new BorderLayout());
		
		availableMinionsModel.addElement("Tanker");
		availableMinionsModel.addElement("Agi");
		availableMinionsModel.addElement("Healer");
		availableMinionsModel.addElement("Taifas");
		
		availableMinions.addListSelectionListener(new availableMinionsListSelectionHandler());
		
		availableMinions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedMinions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		availableMinions.setLayoutOrientation(JList.VERTICAL_WRAP);
		availableMinions.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		availableMinions.setVisibleRowCount(NUMBER_OF_ROWS);
		selectedMinions.setVisibleRowCount(NUMBER_OF_ROWS);
		
		// buttons and image initializations and configuration
		minionImage = new ImageIcon("assets/glass.png");
		minionDisplay = minionImage;
		
		addButton = new JButton(">>");
		removeButton = new JButton("REMOVE");
		finishButton = new JButton("FINISH");
		highscoresButton = new JButton("HIGHSCORES");

		addButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));
		removeButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));
		finishButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));
		highscoresButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));


		imageHolder = new JLabel(minionDisplay);
		
		// Panels initializations and configuration
		leftSide = new JPanel(new BorderLayout());
		windowCenter = new JPanel(new FlowLayout());
		rightSide = new JPanel(new BorderLayout());
		
		leftSide.add(availableMinonsTitle, BorderLayout.NORTH);
		leftSide.add(availableMinions, BorderLayout.CENTER);

		windowCenter.add(imageHolder);
		windowCenter.add(addButton);
		windowCenter.add(removeButton);
		windowCenter.add(highscoresButton);
		windowCenter.add(finishButton);
		
		rightSide.add(selectedMinionsTitle, BorderLayout.NORTH);
		rightSide.add(selectedMinions, BorderLayout.CENTER);
		
		leftSide.setPreferredSize(new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT));
		windowCenter.setPreferredSize(new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT));
		rightSide.setPreferredSize(new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT));
		
		add(leftSide, BorderLayout.WEST);
		add(windowCenter, BorderLayout.CENTER);
		add(rightSide, BorderLayout.EAST);
		
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	//action listener implementations
	public void implementActionListeners(){
		addButton.addActionListener(new AddButtonListener());
		removeButton.addActionListener(new RemoveButtonListener());
		finishButton.addActionListener(new FinishButtonListener());
		highscoresButton.addActionListener(new HighScoreButtonListener(this));
	}
	
	class AddButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){
			int index = availableMinions.getSelectedIndex();
			int putIndex = selectedMinionsModel.getSize();
			
			if(index != -1 && putIndex < MINIONS_LIMIT ){
				selectedMinionsModel.insertElementAt(
						availableMinionsModel.getElementAt(index), putIndex);
			}
			
		}
	}
	
	class RemoveButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = selectedMinions.getSelectedIndex();
			
			if(index != -1)
				selectedMinionsModel.removeElementAt(index);
		}
		
	}
	
	class FinishButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(selectedMinionsModel.getSize() > 0){
				MinionType[] minions = 
						new MinionType[selectedMinionsModel.getSize()];
				
				for(int i = 0; i < selectedMinionsModel.getSize(); i++){
					switch(selectedMinionsModel.getElementAt(i).toString()){
					case "Tanker":
						minions[i] = MinionType.TANKER;
						break;
					case "Agi":
						minions[i] = MinionType.AGI;
						break;
					case "Healer":
						minions[i] = MinionType.HEALER;
						break;
					case "Taifas":
						minions[i] = MinionType.TAIFAS;
						break;
					}
				}
				
				MinionMenu.this.minions = minions;
				MinionMenu.this.dispose();
			}
		}
		
	}
	
	class HighScoreButtonListener implements ActionListener{
		private JFrame frame;
		
		public HighScoreButtonListener(JFrame frame) {
			this.frame = frame;
		}
		
		@SuppressWarnings({ "static-access", "serial" })
		@Override
		public void actionPerformed(ActionEvent e){
			
			final JDialog dialog = new JDialog(frame, "highscores") {};
			
			//Add contents to it. It must have a close button,
            //since some L&Fs (notably Java/Metal) don't provide one
            //in the window decorations for dialogs.
            final JLabel label = new JLabel("<html><p align=center>"
                + "Aguarde...<br>");
            label.setHorizontalAlignment(JLabel.CENTER);
            Font font = label.getFont();
            label.setFont(label.getFont().deriveFont(font.BOLD, 16.0f));

            JButton closeButton = new JButton("Close");
            
            
            JPanel closePanel = new JPanel();
            closePanel.setLayout(new BoxLayout(closePanel,
                                               BoxLayout.LINE_AXIS));
            closePanel.add(Box.createHorizontalGlue());
            closePanel.add(closeButton);
            closePanel.setBorder(BorderFactory.
                createEmptyBorder(0,0,5,5));

            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(label, BorderLayout.CENTER);
            contentPane.add(closePanel, BorderLayout.PAGE_END);
            contentPane.setOpaque(true);
            dialog.setContentPane(contentPane);

            IClientView view = new IClientView() {
            	public void setStatus(String msg) {
            		label.setText(msg);
            	}
			};
			final IClientCallback c = new Client(view);
			c.connect();
			
			closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                    dialog.dispose();
                    c.finish();
                }
            });
			
            //Show it.
            dialog.setSize(new Dimension(300, 300));
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
		}
	}

	//change the image of the minion depending on which one I select
	class availableMinionsListSelectionHandler implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			switch(availableMinions.getSelectedIndex()){
			case 0:
				minionImage = new ImageIcon("assets/TankerSprite1.png");
				imageHolder.setIcon(minionImage);
				break;
			case 1:
				minionImage = new ImageIcon("assets/AgiSprite1.png");
				imageHolder.setIcon(minionImage);
				break;
			case 2:
				minionImage = new ImageIcon("assets/HealerSprite1.png");
				imageHolder.setIcon(minionImage);
				break;
			case 3:
				minionImage = new ImageIcon("assets/TaifasSprite1.png");
				imageHolder.setIcon(minionImage);
				break;
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//closes the minion select window and opens the game window
	@Override
	public void windowClosed(WindowEvent e) {
		Thread t = new Thread() {
			@Override
			public void run() {
				gameWindow = new J2DGameWindow();
				gameWindow.setTitle("Taifas Attack");
				
				game = new Game(gameWindow, minions);
				game.init();
				
				gameWindow.setGame(game);
				gameWindow.startRendering();
			};
		};
		t.start();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}
