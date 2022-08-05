package userInterFace;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class gameWindow extends JFrame {
	
	public static final int SCREEN_WIDTH = 600;
	private gameScreen gamescreen;
		
	//create a constructor
	public gameWindow() {
		super("TYRANNOSAURUS");
		setSize(SCREEN_WIDTH, 170);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		gamescreen = new gameScreen();//new instance 
		add(gamescreen); // add game screen to the game window  
		addKeyListener(gamescreen);
	}
	
	public void startGame() {
		setVisible(true);
		gamescreen.startGame();
	}
	
	//main method
	public static void main(String args[]) {
//		new gameWindow().setVisible(true);
		gameWindow gw = new gameWindow();
		gw.setVisible(true);
		gw.startGame();
	}
	

}
