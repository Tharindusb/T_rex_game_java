package Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Util.Resource;


public class Clouds {
	private BufferedImage cloudImage;
	private List<ImageCloud> listCloud;
	
	public Clouds() {
		cloudImage =Resource.getResouceImage("data/cloud.png");
		listCloud = new ArrayList<ImageCloud>();
		
		ImageCloud cloud1=new ImageCloud();
		cloud1.posX=100;
		cloud1.posY=50;
		listCloud.add(cloud1);
		
		cloud1=new ImageCloud();
		cloud1.posX=200;
		cloud1.posY=30;
		listCloud.add(cloud1);
		
		cloud1=new ImageCloud();
		cloud1.posX=300;
		cloud1.posY=80;
		listCloud.add(cloud1);
		
		cloud1=new ImageCloud();
		cloud1.posX=450;
		cloud1.posY=50;
		listCloud.add(cloud1);
		
		cloud1=new ImageCloud();
		cloud1.posX=600;
		cloud1.posY=60;
		listCloud.add(cloud1);
	}
	
	public void update() {  
		for(ImageCloud cloud:listCloud) {
			cloud.posX--;	
		}
		ImageCloud firstCloud = listCloud.get(0);
		if(firstCloud.posX + cloudImage.getWidth()<0 ) {
			 listCloud.remove(0);
			 firstCloud.posX = 600;
			 listCloud.add(firstCloud);
		}
		
		
	}
	
	public void draw(Graphics g) {
		for(ImageCloud imgCloud:listCloud) {
		g.drawImage(cloudImage,(int)imgCloud.posX,(int)imgCloud.posY,null);	
		}
	}
	
	private class ImageCloud{
		float posX;
		float posY;
	}
}
