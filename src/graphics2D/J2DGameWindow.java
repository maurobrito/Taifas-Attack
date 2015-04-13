package graphics2D;

import interfaces.ICircle;
import interfaces.IGame;
import interfaces.IGameWindow;
import interfaces.ILabel;
import interfaces.ILine;
import interfaces.IPoint;
import interfaces.IRect;
import interfaces.ISprite;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import minionMenu.Client;
import minionMenu.IClientCallback;
import minionMenu.IClientView;
import minionMenu.MinionMenu;

//game's main window
@SuppressWarnings("serial")
public class J2DGameWindow extends Canvas implements IGameWindow {
	
	private Boolean running = true;
	private BufferStrategy strategy;
	private IGame game = null;
	private JFrame frame;
	private int width = 800;
	private int height = 600;
	private long lastLoopTime;
	private long startTime;
	
	/** The current accelerated graphics context */
	private Graphics2D g;
	
	public J2DGameWindow() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Start time counters
		startTime = System.currentTimeMillis();
		lastLoopTime = startTime;
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);

	}

	@Override
	public void setResolution(int x, int y) {
		width = x;
		height = y;
	}

	@Override
	public void startRendering() {
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(null);

		setBounds(5, 5, width, height);
		panel.add(this);

		setIgnoreRepaint(true);

		// finally make the window visible

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		// add a listener to respond to the user closing the window. If they

		// do we'd like to exit the game

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		requestFocus();
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		gameLoop();
	}
	
	Graphics2D getDrawGraphics() {
		return g;
	}
	
	/**
	 * Run the main game loop. This method keeps rendering the scene
	 * and requesting that the callback update its screen.
	 */
	private void gameLoop() {
		lastLoopTime = System.currentTimeMillis();
		
		while (running) {
			// Get hold of a graphics context for the accelerated 

			// surface and blank it out
			g = (Graphics2D) strategy.getDrawGraphics();
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			
			if(game != null) {
				long actualTime = System.currentTimeMillis();
				long delta = actualTime - lastLoopTime;
				lastLoopTime = actualTime;
				game.update(delta);
				game.draw(delta);
			}
			
			g.dispose();
			strategy.show();
		}
	}
	
	public void setGame(IGame game) {
		this.game = game;
	}

	@Override
	public ILabel createLabel(String text, Font font) {
		return new J2DLabel(this, text, font);
	}

	@Override
	public ISprite createSprite(String ref) {
		try {
			return new J2DSprite(this, ImageIO.read(new File(ref)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public IPoint createPoint() {
		return new J2DPoint(this);
	}
	
	@Override
	public ILine createLine() {
		return new J2DLine(this);
	}
	
	@Override
	public IRect createRect() {
		return new J2DRect(this);
	}
	
	@Override
	public ICircle createCircle() {
		return new J2DCircle(this);
	}

	@Override
	public void endGame(final int score) {
		running = false;
		
		final JDialog dialog = new JDialog(frame, "Game Over!") {};
		
		//Add contents to it. It must have a close button,
        //since some L&Fs (notably Java/Metal) don't provide one
        //in the window decorations for dialogs.
        final JLabel label = new JLabel("Pontuação: " + score);
        label.setHorizontalAlignment(JLabel.CENTER);
        Font font = label.getFont();
        label.setFont(label.getFont().deriveFont(font.BOLD, 16.0f));
        JPanel namePane = new JPanel(new FlowLayout());
        namePane.add(new JLabel("Nome:"));
        final JTextField name = new JTextField();
        name.setColumns(20);
        namePane.add(name);
        
        JButton closeButton = new JButton("Close");
        final JButton enviarButton = new JButton("Enviar");
        
        JPanel closePanel = new JPanel();
        closePanel.setLayout(new BoxLayout(closePanel,
                                           BoxLayout.LINE_AXIS));
        closePanel.add(Box.createHorizontalGlue());
        closePanel.add(closeButton);
        closePanel.add(enviarButton);
        closePanel.setBorder(BorderFactory.
            createEmptyBorder(0,0,5,5));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(label, BorderLayout.PAGE_START);
        contentPane.add(namePane, BorderLayout.CENTER);
        contentPane.add(closePanel, BorderLayout.PAGE_END);
        contentPane.setOpaque(true);
        dialog.setContentPane(contentPane);

        IClientView view = new IClientView() {
        	public void setStatus(String msg) {
        		//label.setText(msg);
        	}
		};
		final IClientCallback c = new Client(view);
		c.connect();
		
		closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
                c.finish();
                frame.dispose();
                MinionMenu minionMenu = new MinionMenu();
            }
        });
		
		enviarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(c.getConnected()) {
					label.setText("Enviado!");
					enviarButton.setEnabled(false);
					c.sendScore(name.getText(), score);
					c.finish();
				} else { 
					label.setText("Não foi possível enviar!");
					enviarButton.setEnabled(false);
				}
			}
		});
        //Show it.
        dialog.setSize(new Dimension(300, 150));
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
		
	}
}
