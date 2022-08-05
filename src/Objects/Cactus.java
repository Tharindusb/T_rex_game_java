package Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.w3c.dom.css.Rect;

import Util.Resource;

public class Cactus extends Enemy {
	
	private BufferedImage image;
	private int posX,posY,y;
	private Rectangle rect;
	private MainCharacter mainCharacter;
	private boolean Scored = false;
	public Cactus(MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
		image = Resource.getResouceImage("data/cactus1.png");
		posX=5;
		posY=85;
		rect = new Rectangle();
	}
	
	public void update() {
		posX -=10;
		rect.x = posX; // left
		rect.y=posY; //top
		rect.width=image.getWidth();
		rect.height=image.getHeight();
		
	}
	@Override
	public Rectangle getBound() {
		return rect;
	}
	public void draw(Graphics g) {
		g.drawImage(image,posX,posY,null);	
	}
	
	public void setX(int x) {
		posX =x;
	}
	
	public void setY(int y) {
		posY=y;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;		
	}
	
	@Override
	public boolean isOutOfScreen() {
		return(posX + image.getWidth() <0);
		
	}
	
	@Override
	public boolean GameOver() {
		return(mainCharacter.getX() > posX);
			
		
	}
	@Override
	public boolean Scored() {
		return Scored;
	}
	@Override
	public void SetScored(boolean Scored) {
		this.Scored=Scored;
	}
	
}
