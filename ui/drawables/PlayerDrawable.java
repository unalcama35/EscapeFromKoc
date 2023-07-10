package ui.drawables;

import domain.gameObjects.GameObject;
import domain.gameObjects.characters.Player;
import ui.SpriteManager;
import ui.uiObjects.UIObject;
import utility.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerDrawable implements Drawable{

	private Player player;
	
	private int opacity;
	private boolean lostLife;

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		BufferedImage sprite = SpriteManager.getInstance().getSprite(player.getCurrSprite());


		int squareWidth = 32;
		Rectangle playerRect = player.getRect();
		//Rectangle biggerRect = new Rectangle(playerRect.x-squareWidth*2, playerRect.y-squareWidth*2,
		//		squareWidth*2, squareWidth*2);
		g2.drawOval((int) (playerRect.getCenterX()-squareWidth), (int) (playerRect.getCenterY()-squareWidth),
				squareWidth*2, squareWidth*2);


		if(lostLife) {
			opacity += 10;
			g2.drawImage(Utilities.tint(sprite, new Color(255,0,0,opacity)), player.getRect().x, player.getRect().y,null);
			if(opacity == 150) {
				lostLife = false;
				opacity = 0;
			}
		}else {
			g2.drawImage(sprite, player.getRect().x, player.getRect().y,null);
		}
		
	}
	
	

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		player = (Player) obj;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.PLAYER;
	}

	public void lostLife() {
		lostLife = true;
		opacity = 50;
	}

	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		
	}
	
}
