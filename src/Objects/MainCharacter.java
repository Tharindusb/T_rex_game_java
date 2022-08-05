package Objects;

import static userInterFace.gameScreen.GROUND;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Util.Animation;
import Util.Resource;

public class MainCharacter {
	private float x  =0;
	private float y = 0;
	private float SpeedY =0;
	private Animation characterRun;
	private Rectangle rect;
	private boolean Alive =true;
	
	public MainCharacter() {
		characterRun = new Animation(100);
		characterRun.addFrame(Resource.getResouceImage("data/main-character1.png"));
		characterRun.addFrame(Resource.getResouceImage("data/main-character2.png"));
		rect = new Rectangle();
		
	}
	
	public void update() {
		characterRun.update();
		//for jump
		if(y>=GROUND-characterRun.getFrame().getHeight()) {
			SpeedY =0;
			y=GROUND-characterRun.getFrame().getHeight();
		}else {
			SpeedY+=0.6f;
			y+=SpeedY;
		}
		rect.x =(int) x;
		rect.y =(int) y;
		rect.width =characterRun.getFrame().getWidth();
		rect.height = characterRun.getFrame().getHeight();
	}
	
	public Rectangle getBound() {
		return rect;
	}
	
	public void jump() {
		if(y >= 65) {
			SpeedY = -7.5f;
			y += SpeedY;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
//		g.drawRect((int)x,(int)y,characterRun.getFrame().getWidth(),characterRun.getFrame().getHeight());
		g.drawImage(characterRun.getFrame(),(int) x,(int)y+15,null);
		
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getSpeedY() {
		return SpeedY;
	}
	public void setSpeedY(float speedY) {
		SpeedY = speedY;
	}
	public void setAlive(boolean alive) {
		Alive = alive;
	}
	
	public boolean getAlive() {
		return Alive;
	}
}
