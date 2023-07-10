package ui.drawables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import domain.gameObjects.GameObject;
import ui.SpriteManager;
import ui.uiObjects.TextFieldUI;
import ui.uiObjects.UIObject;

public class TextFieldDrawable implements Drawable{

	private TextFieldUI textfield;
	private long startTime;
	private final int blinkCD = 500;
	private boolean showBar;
	
	public TextFieldDrawable() {
		startTime = System.currentTimeMillis();
		showBar = false;
		
	}
	
	@Override
	public void draw(Graphics2D g2) {
		
		Rectangle rect = textfield.getRect();
		// TODO Auto-generated method stub
		//g2.setColor(Color.white);
		//g2.fillRect(textfield.getRect().x,textfield.getRect().y,textfield.getRect().width,textfield.getRect().height);
		BufferedImage sprite = SpriteManager.getInstance().getSprite(textfield.getCurrSprite());
		g2.drawImage(sprite, rect.x, rect.y, null);
		
		//g2.setColor(Color.red);
		//g2.drawRect(textfield.getRect().x,textfield.getRect().y,textfield.getRect().width,textfield.getRect().height);
		int margin = 3;
		
		g2.setFont(new Font("Arial", 0, 12));
		g2.setColor(Color.black);
		g2.drawString(textfield.getText(), textfield.getRect().x + margin, textfield.getRect().y + 15);
		
		if(System.currentTimeMillis() - startTime >= blinkCD) {
			showBar = !showBar;
			startTime = System.currentTimeMillis();

		}
		
		int width = g2.getFontMetrics().stringWidth(textfield.getText());

		
		if(textfield.isFocused() && showBar) {
			g2.setColor(Color.black);
			g2.drawRect(rect.x + width + margin, (int) (rect.getCenterY() - 8), 1,16);
		}
	}
	

	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		textfield = (TextFieldUI) obj;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.TEXT_FIELD;
	}

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		
	}
	
	

}
