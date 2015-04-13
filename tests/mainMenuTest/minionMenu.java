package mainMenuTest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class minionMenu extends JFrame 
implements ListSelectionListener{
	
	private JList<Object> availableMinions;
	private JList<Object> selectedMinions;	
	private DefaultListModel<Object> availableMinionsModel;
	private DefaultListModel<Object> selectedMinionsModel;
	
	private Icon minionDisplay;
	
	private static int MINIONS_LIMIT = 5;
	private static int NUMBER_OF_ROWS = 20;
	private static int SCREEN_WIDTH = 640;
	private static int SCREEN_HEIGHT = 480;
	
	private JButton addButton;
	private JButton removeButton;
	private JButton finishButton;
	private JPanel leftSide;
	private JPanel rightSide;
	private JPanel windowCenter;
	private JLabel imageHolder;
	private JLabel availableMinonsTitle;
	private JLabel selectedMinionsTitle;
	
	public minionMenu(){
		super("Minion Selection");
	}
	
	public void buildUI() {
		// minions lists initializations and configuration
		availableMinonsTitle = new JLabel("Available Minions");
		selectedMinionsTitle = new JLabel("Selected Minions");
		
		availableMinionsModel = new DefaultListModel<>();
		selectedMinionsModel = new DefaultListModel<>();
		
		availableMinions = new JList<>(availableMinionsModel);	
		selectedMinions = new JList<>(selectedMinionsModel);
		
		setLayout(new BorderLayout());
		
		availableMinionsModel.addElement("minion 1");
		availableMinionsModel.addElement("minion 2");
		
		availableMinions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectedMinions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		availableMinions.setLayoutOrientation(JList.VERTICAL_WRAP);
		availableMinions.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		availableMinions.setVisibleRowCount(NUMBER_OF_ROWS);
		selectedMinions.setVisibleRowCount(NUMBER_OF_ROWS);
		
		// buttons and image initializations and configuration
		minionDisplay = new ImageIcon("assets/Doente1.png");
		
		addButton = new JButton(">>");
		removeButton = new JButton("REMOVE");
		finishButton = new JButton("FINISH");
		
		addButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));
		removeButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));
		finishButton.setPreferredSize(new Dimension(SCREEN_WIDTH/5, SCREEN_HEIGHT/8));
		
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
		windowCenter.add(finishButton);
		
		rightSide.add(selectedMinionsTitle, BorderLayout.NORTH);
		rightSide.add(selectedMinions, BorderLayout.CENTER);
		
		leftSide.setPreferredSize(new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT));
		windowCenter.setPreferredSize(new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT));
		rightSide.setPreferredSize(new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT));
		
		add(leftSide, BorderLayout.WEST);
		add(windowCenter, BorderLayout.CENTER);
		add(rightSide, BorderLayout.EAST);
	}
	
	public void implementActionListeners(){
		addButton.addActionListener(new AddButtonListener());
		removeButton.addActionListener(new RemoveButtonListener());
		finishButton.addActionListener(new FinishButtonListener());
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
			
			
		}
		
	}
	
	public static void main(String[] args){
		minionMenu main_menu = new minionMenu();
		main_menu.buildUI();
		main_menu.implementActionListeners();
		main_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_menu.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		main_menu.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
