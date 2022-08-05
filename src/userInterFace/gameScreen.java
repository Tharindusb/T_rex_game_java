package userInterFace;

import static userInterFace.gameScreen.GROUND;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;

import Objects.Cactus;
import Objects.Clouds;
import Objects.EnemiesControl;
import Objects.Land;
import Objects.MainCharacter;
import Util.Resource;

public class gameScreen extends JPanel implements Runnable, KeyListener {
	
	private MainCharacter maincharacter;
	private Thread thread;
	public static final int  GAME_START_STATE =0;
	public static final int  GAME_PLAY_STATE =1;
	public static final int  GAME_OVER_STATE =2;
	public static final float GROUND = 110; // by pixel
	private Land land;
	private Clouds clouds;
	private EnemiesControl enemiesControl;
	private int gameState = GAME_START_STATE; 
	private BufferedImage imageGameOver,imageReplay,imagewelcome;
	private int score;
//	private Cactus cactus;

	public gameScreen() {
		
		thread = new Thread(this);
		maincharacter = new MainCharacter();
		maincharacter.setX(50);
		maincharacter.setY(40);
		land = new Land(this);
		clouds = new Clouds();
		enemiesControl = new EnemiesControl(maincharacter,this);
		imageGameOver =Resource.getResouceImage("data/gameover_text.png");
		imageReplay =Resource.getResouceImage("data/replay_button.png");
		imagewelcome =Resource.getResouceImage("data/name3.png");
//		cactus = new Cactus();
	}

	
	public void startGame() {
		thread.start(); 
	}

	@Override
	public void run() {
		while(true) {
			try {
				update();
				repaint(); // to draw req again and again
				Thread.sleep(20);
				
			}catch(InterruptedException e){
				System.out.println(e);
			}	
		}
	}
	
	public void update() {
		switch(gameState) {
		case GAME_PLAY_STATE:
			maincharacter.update();
			land.update();
			clouds.update();
			enemiesControl.update();
			if(!maincharacter.getAlive()) {
				gameState = GAME_OVER_STATE;
			}
			break;
		}	
	}
	
	public void plusScore(int score) {
		this.score +=score;
//		if(score % 100 == 0) {
//		}
	}
	
	
	
	//Graphics and drawable file
	public void paint(Graphics g) {
		g.setColor(Color.decode("#64c2f5"));
		g.fillRect(0, 0, getWidth(), getHeight());
//		g.setColor(Color.black);
//		g.drawLine(0,(int) GROUND,getWidth(),(int)GROUND);
		
		switch(gameState) {
		case GAME_START_STATE:
			maincharacter.draw(g);
			g.drawImage(imagewelcome, 0, 0,null);
			g.drawString("Press Space To Start", 250, 100);
			break;
		case GAME_PLAY_STATE:
			clouds.draw(g);
			land.draw(g);
//			cactus.draw(g);
			maincharacter.draw(g);
			enemiesControl.draw(g);
			g.drawString("SCORE "+String.valueOf(score), 500, 20);
			break;
		case GAME_OVER_STATE:
			clouds.draw(g);
			land.draw(g);
//			cactus.draw(g);
			maincharacter.draw(g);
			enemiesControl.draw(g);
			g.drawImage(imageGameOver,210, 50,null);
			g.drawImage(imageReplay, 300,75 ,null);
			break;
			
		}			
	}
	
	private void resetGame() {
		maincharacter.setAlive(true);
		maincharacter.setX(50);
		maincharacter.setY(60);
		enemiesControl.reset();
		score =0;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		maincharacter.jump();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Key Released");
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if(gameState == GAME_START_STATE ) {
				gameState = GAME_PLAY_STATE;
			}else if(gameState==GAME_PLAY_STATE) {
				maincharacter.jump();
			}else if(gameState==GAME_OVER_STATE) {
				resetGame();
				gameState = GAME_PLAY_STATE;
				
			}
			break;
		}
	}
}
