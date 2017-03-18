package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.sun.prism.Image;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;
import edu.virginia.engine.events.IEventListener;






/**
 * Highest level class for creating a game in Java.
 * 
 * */
public class Game extends DisplayObjectContainer implements ActionListener, KeyListener , MouseListener,IEventDispatcher{
	Rectangle rect = new Rectangle();
	
	/* Frames per second this game runs at */
	private int FRAMES_PER_SEC = 60;

	/* The main JFrame that holds this game */
	private JFrame mainFrame;
	JLabel label;
	
	/* Timer that this game runs on */
	private Timer gameTimer;
	long startTime;
	
	/* The JPanel for this game */
	private GameScenePanel scenePanel;
	
	public void editMainFrame(Component comp){
		mainFrame.add(comp);
		
	}

	public Game(String gameId, int width, int height) {
		super(gameId);
		
		
		setUpMainFrame(gameId, width, height);
		
		setScenePanel(new GameScenePanel(this));
		
		/* Use an absolute layout */
		
		scenePanel.setLayout(null);
		scenePanel.setBackground(Color.WHITE);
		
		
		
		
	}
	
	
	public void setFramesPerSecond(int fps){
		if(fps > 0) this.FRAMES_PER_SEC = fps;
	}

	public void setUpMainFrame(String gameId, int width, int height) {
		this.mainFrame = new JFrame();
		getMainFrame().setTitle(gameId);
		getMainFrame().setResizable(false);
		getMainFrame().setVisible(true);
		getMainFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getMainFrame().setBounds(0, 0, width, height);
		getMainFrame().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getMainFrame().addKeyListener(this);
		getMainFrame().addMouseListener(this);
	}

	/**
	 * Starts the game
	 */
	public void start() {
		
		
		
		if (gameTimer == null) {
			gameTimer = new Timer(1000 / FRAMES_PER_SEC, this);
			gameTimer.start();
			
			
		} else {
			gameTimer.start();
		}
		startTime = System.currentTimeMillis();
		
	}
	


	/**
	 * Stops the animation.
	 */
	public void stop() {
		pause();
		gameTimer = null;
	}

	public void pause() {
		if (gameTimer != null) {
			gameTimer.stop();
		}
	}
	
	public void exitGame(){
		stop();
		this.mainFrame.setVisible(false);
		this.mainFrame.dispose();
	}
	
	/**
	 * Close the window
	 * */
	public void closeGame(){
		this.stop();
		if(this.getMainFrame() != null){
			this.getMainFrame().setVisible(false);
			this.getMainFrame().dispose();
		}
	}


	/**
	 * Called once per frame. updates the game, redraws the screen, etc. May
	 * need to optimize this if games get too slow.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaintGame();
		//System.out.println("hello world");
		
		
		
	}
	
	/**
	 * Forces a repaint
	 * */
	public void repaint(){repaintGame();}
	public void repaintGame(){
		if(getScenePanel() != null){
			getScenePanel().validate();
			getScenePanel().repaint();
		}
	}

	protected void nextFrame(Graphics g) {

		try {
			/* Update all objects on the stage */
			this.update(pressedKeys);

			/* Draw everything on the screen */
			this.draw(g);
		} catch (Exception e) {
			System.out
					.println("Exception in nextFrame of game. Stopping game (no frames will be drawn anymore");
			stop();
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics g){
		/* Start with no transparency */
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				1.0f));
		
		super.draw(g);
		
		
		
	}
	

	public JFrame getMainFrame() {
		return this.mainFrame;
	}
	
	public void setScenePanel(GameScenePanel scenePanel) {
		this.scenePanel = scenePanel;
		this.getMainFrame().add(this.scenePanel);
		getMainFrame().setFocusable(true);
		getMainFrame().requestFocusInWindow();
		
	}

	public GameScenePanel getScenePanel() {
		return scenePanel;
	}

	ArrayList<String> pressedKeys = new ArrayList<String>();
	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(KeyEvent.getKeyText(e.getKeyCode())))
			pressedKeys.add(KeyEvent.getKeyText(e.getKeyCode()));
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains(KeyEvent.getKeyText(e.getKeyCode())))
			pressedKeys.remove(KeyEvent.getKeyText(e.getKeyCode()));
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	ArrayList<IEventListener> eventlist =  new ArrayList<>();
    @Override
    public void addEventListener(IEventListener listener, String eventType) {
        eventlist.add(listener);
    }

    @Override
    public void removeEventListener(IEventListener listener, String eventType) {
        eventlist.remove(listener);
    }

    @Override
    public void dispatchEvent(Event event) {
        for (int i =0; i<eventlist.size();i++){
            eventlist.get(i).handleEvent(event);
        }
    }

    public void dispatchEvent(Event event,Sprite sprite) {
        for (int i =0; i<eventlist.size();i++){
            eventlist.get(i).handleEvent(event,sprite);
        }
    }
    @Override
    public boolean hasEventListener(IEventListener listener, String eventType) {
        if (eventlist.contains(listener)){return true;}
        return false;
    }
}
