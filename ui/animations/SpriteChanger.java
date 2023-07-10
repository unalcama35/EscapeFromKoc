package ui.animations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpriteChanger extends Animation{

	private int currSpriteInd;
	private int spriteCnt;
	
	private int spriteChangeCooldown = 80;
	private long changeStartTime;
	private boolean isReturning;
	private boolean goingRight;
	
	public SpriteChanger(int spriteCnt, boolean isReturning) {
		currSpriteInd = 0;
		this.spriteCnt = spriteCnt;
		this.isReturning = isReturning;
		goingRight = true;
		
		changeStartTime = System.currentTimeMillis();
		
	}
	
	public int getCurrSpriteInd() {
		return currSpriteInd;
	}
	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean animate() {
		// TODO Auto-generated method stub
		
		if(System.currentTimeMillis() - changeStartTime >= spriteChangeCooldown) {
			if(!isReturning) {
				if(currSpriteInd == spriteCnt-1) {
				currSpriteInd = 0;
				}else {
					currSpriteInd++;
				}
			}else {
				if(goingRight) {
					currSpriteInd++;
				}else {
					currSpriteInd--;
				}
				
				if(currSpriteInd == spriteCnt - 1) {
					goingRight = false;
				}else if(currSpriteInd == 0){
					goingRight = true;
				}
				
				
			}
			
			changeStartTime = System.currentTimeMillis();
			return true;
		}
		
		return false;
	}

}
