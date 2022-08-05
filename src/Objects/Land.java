package Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Util.Resource;
import userInterFace.gameScreen;

public class Land {
	
	public static final int LAND_POSY = 103;
	
	private BufferedImage land1,land2,land3;
	private List<ImageLand> listImage;
	private Random random;
	
	public Land(gameScreen game) {
		random = new Random();
		land1 = Resource.getResouceImage("data/land1.png");
		land2 = Resource.getResouceImage("data/land2.png");
		land3 = Resource.getResouceImage("data/land3.png");
		
		listImage = new ArrayList<ImageLand>();
//		int numberOfImageLand = game.getWidth()/land1.getWidth();
		int numberOfImageLand = 600/land1.getWidth() +2 ;
		for(int i=0;i<numberOfImageLand;i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = (int) (i*land1.getWidth());
			setImageLand(imageLand);
			listImage.add(imageLand);
		}
		
	}
	
	public void update() {
		for(ImageLand imageLand:listImage) {
			imageLand.posX-=10 ;
		}
		ImageLand firstElement = listImage.get(0);
		if(firstElement.posX + land1.getWidth()<0) {
			listImage.remove(0);
			firstElement.posX = listImage.get(listImage.size()-1).posX + land1.getWidth();
			setImageLand(firstElement);
			listImage.add(firstElement);
			
			
		}
	}
	
	public void draw(Graphics g) {
		for(ImageLand imageLand:listImage) {
			g.drawImage(imageLand.image, imageLand.posX,LAND_POSY , null);
			
		}
	}
	
	private void setImageLand(ImageLand imageLand) {
		int typeLand = getImageLand();
		if(typeLand == 1) {
			imageLand.image = land1;
		} else if(typeLand == 3) {
			imageLand.image = land3;
		} else {
			imageLand.image = land2;
		}
		
	}
	private int getImageLand() {
		int i = random.nextInt(10);
		if(i == 1) {
			return 1;
		} else if(i == 9) {
			return 3;
		} else {
			return 2;
		}	
	}
	
//	privat BufferedImage getImageLand() {
//		int i = random.nextInt(10);
//		switch(i) {
//		case 0: return land1;
//		case 1: return land2;
//		default: return land3;
//		}
//	}
	private class ImageLand{
		int posX;
		BufferedImage image;
	}
}