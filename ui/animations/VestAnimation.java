package ui.animations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import domain.gameObjects.characters.Player;
import ui.SpriteManager;
import ui.SpriteType;

public class VestAnimation extends Animation{

	private ArrayList<SpriteType> fieldSprites;
	private int currFieldInd;
	private final int fieldSpriteChangeCD = 100;
	private long fieldSpriteChangeStartTime;
	
	private long vestStartTime;
	private final int VEST_DURATION = 20000; //20s
	
	private Player player;
	
	public VestAnimation(Player player) {
		fieldSprites = loadImgs("FIELD");
		this.player = player;
		
		fieldSpriteChangeStartTime = System.currentTimeMillis();
		vestStartTime = System.currentTimeMillis();

	}
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		int x = (int) (player.getRect().getCenterX() - SpriteManager.getInstance().getSprite(fieldSprites.get(currFieldInd)).getWidth()/2);
		int y = (int) (player.getRect().getCenterY() - SpriteManager.getInstance().getSprite(fieldSprites.get(currFieldInd)).getHeight()/2);
		g2.drawImage(SpriteManager.getInstance().getSprite(fieldSprites.get(currFieldInd)),x ,y,null);
	}

	@Override
	public boolean animate() {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis() - vestStartTime >= VEST_DURATION) {
			hasEnded = true;
		}
		
		if(System.currentTimeMillis() - fieldSpriteChangeStartTime >= fieldSpriteChangeCD) {
			if(currFieldInd == 3) {
				currFieldInd = 0;
			}else {
				currFieldInd++;
			}
			fieldSpriteChangeStartTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	
}
