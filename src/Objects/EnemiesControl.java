package Objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Util.Resource;
import userInterFace.gameScreen;

public class EnemiesControl {
	private List<Enemy>enemies;
	private Random random;
	private BufferedImage imageCactus1,imageCactus2;
	private MainCharacter mainCharacter;
	private gameScreen gamescreen;
	
	public EnemiesControl(MainCharacter mainCharacter, gameScreen gamescreen) {
		this.gamescreen =gamescreen;
		this.mainCharacter=mainCharacter;
		enemies = new ArrayList<Enemy>();
		imageCactus1=Resource.getResouceImage("data/cactus1.png");
		imageCactus2=Resource.getResouceImage("data/cactus2.png");
		random = new Random(); 
		enemies.add(getRandomCactus());
	}
	
	public void update() {
		for(Enemy e: enemies) {
			e.update();
			if(e.GameOver() && !e.Scored()) {
				gamescreen.plusScore(20);
				e.SetScored(true);
			}
			if(e.getBound().intersects(mainCharacter.getBound())) {
				mainCharacter.setAlive(false);
				
			}
		}
		Enemy firstEnemy = enemies.get(0);
		if(firstEnemy.isOutOfScreen()) {
			enemies.remove(firstEnemy);
			enemies.add(getRandomCactus());
		}	
	}
	
	private Cactus getRandomCactus() {
		Cactus cactus ;
		cactus = new Cactus(mainCharacter);
		cactus.setX(600);
		if(random.nextBoolean()) {
			cactus.setImage(imageCactus1);
			cactus.setY(80);
		}
		else {
			cactus.setImage(imageCactus2);
			cactus.setY(90);
		}
		return cactus;
		
	}
	
	public void draw(Graphics g) {
		for (Enemy e:enemies) {
			e.draw(g);
		}
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(getRandomCactus());
	}	
}

