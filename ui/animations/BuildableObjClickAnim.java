package ui.animations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import domain.gameLogic.scenes.Scene;
import ui.sounds.SoundType;

public class BuildableObjClickAnim extends Animation{
	
	private int maxWidth;
	private int currWidth;
	private Rectangle rect;
	private long startTime;
	private final long duration = 10; //10s
	
	public BuildableObjClickAnim(Rectangle targetRect, Scene scene) {
		super();
		rect = new Rectangle(targetRect.x,targetRect.y, targetRect.width, targetRect.height);
		hasEnded = false;
		startTime = System.currentTimeMillis();
		maxWidth = 0;
		currWidth = 0;

		scene.playSound(SoundType.CLICK_SOUND);
		
	}

	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
		g2.setColor(Color.white);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);		
	}


	@Override
	public boolean animate() {
		// TODO Auto-generated method stub
		
		if(System.currentTimeMillis() - startTime >= duration) {
			hasEnded = true;
		}
		
//		if(!hasEnded) {
//			
//			int dWidth = 1;
//			
//			rect.x -= dWidth;
//			rect.y -= dWidth;
//			rect.width += dWidth * 2;
//			rect.height += dWidth * 2;
//			
//		}
		return false;

	}

}
