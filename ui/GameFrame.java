package ui;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import ui.animations.Animation;
import ui.sounds.SoundType;

import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements KeyListener, MouseListener, MouseMotionListener{

		private MainGamePanel mainGamePanel;
	
		
	
	public GameFrame() {
		super("Escape from Koc");
		
		//this.game = game;
		
		mainGamePanel = new MainGamePanel();
		this.add(mainGamePanel);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);	
		this.setVisible(true);
		this.setResizable(false);
		//GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].setFullScreenWindow(this);
		pack();	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		

	}
	
	public void addAnimation(Animation anim) {
		mainGamePanel.addAnimation(anim);
	}
	
	public void addAndPlaySound(SoundType soundType) {
		mainGamePanel.addAndPlaySound(soundType);
	}
	
	public void playSound(SoundType soundType) {
		mainGamePanel.playSound(soundType);
	}
	
	public void stopSound(SoundType soundType) {
		mainGamePanel.stopSound(soundType);
	}
	
	public void render() {
		//System.out.println("rendering scene");
		mainGamePanel.repaint();
		
	}

	public MainGamePanel getMainPanel() {
		return mainGamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
			mainGamePanel.keyPressed(e);
		
			Game.getInstance().pressKey(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Game.getInstance().releaseKey(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mainGamePanel.clickOnUIObject(e.getPoint());
		Game.getInstance().pressMouse(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Game.getInstance().releaseMouse(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mainGamePanel.mouseOverUIObj(e.getPoint());
	}

	public void addEvent(GameEventType type) {
		// TODO Auto-generated method stub
		if(type == GameEventType.CLOSE_GAME) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else {
			mainGamePanel.handleEvent(type);
		}
	}


	public void newDialog(String text) {
		mainGamePanel.newDialog(text);
	}
}
	