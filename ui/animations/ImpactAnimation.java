package ui.animations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ui.SpriteManager;
import ui.SpriteType;

public class ImpactAnimation extends Animation {

	private ArrayList<SpriteType> sprites;
	private int x,y;
	private int ind;
	
	private final int spriteChangeCD = 20;
	private long changeStartTime;
	
	
	public ImpactAnimation(int x, int y) {
		super();
		sprites = loadImgs("IMPACT");
		
		this.x = x;
		this.y = y;
		changeStartTime = System.currentTimeMillis();
		
	}
	
	
	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
		
		g2.drawImage(SpriteManager.getInstance().getSprite(sprites.get(ind)), x - SpriteManager.getInstance().getSprite(sprites.get(ind)).getWidth()/2,
				y - SpriteManager.getInstance().getSprite(sprites.get(ind)).getHeight()/2, null);
	}

	@Override
	public boolean animate() {
		// TODO Auto-generated method stub
		
		if(System.currentTimeMillis() - changeStartTime >= spriteChangeCD) {
			if(ind == 3) {
				hasEnded = true;
			}else {
				ind++;
			}
		}
		return false;
	}

}
