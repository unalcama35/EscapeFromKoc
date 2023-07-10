package ui.animations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;

public class HintAnimation extends Animation{

	private Rectangle rect;
	
	private long startTime;
	private final long duration = 10000; //10s
	private int width = 1;
	private Color color;
	private float hue;
	
	public HintAnimation(Rectangle targetRect) {
		
		color = Color.white;
		hue = 0;
		startTime = System.currentTimeMillis();
		rect = new Rectangle(targetRect.x - width,targetRect.y - width, targetRect.width + width*2, targetRect.height + width*2);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
		
		color = Color.getHSBColor(hue, 1.0f, 1.0f);
		g2.setColor(color);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public void moveHint(Rectangle targetRect) {
		rect = new Rectangle(targetRect.x - width,targetRect.y - width, targetRect.width + width*2, targetRect.height + width*2);
	}

	@Override
	public boolean animate() {
		// TODO Auto-generated method stub
		
		if(hue == 1) {
			hue = 0;
		}
		hue += 0.05f;
		
		if(System.currentTimeMillis() - startTime >= duration) {
			hasEnded = true;
		}
		return false;
		
	}

}
