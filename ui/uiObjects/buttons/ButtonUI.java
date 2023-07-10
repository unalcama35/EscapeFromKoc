package ui.uiObjects.buttons;

import domain.gameLogic.Game;
import domain.gameLogic.GameEventType;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.uiObjects.UIObject;

import java.awt.*;

public class ButtonUI extends UIObject{
	
	private GameEventType event;
	private boolean toDomain;

	public ButtonUI(String name, int x, int y, GameEventType event, boolean toDomain, SpriteType spriteType, SpriteType tintedSprite) {
		super(name);
		this.event = event;
		this.toDomain = toDomain;
		icon = spriteType;
		tintedIcon = tintedSprite;
		currSprite = icon;
		setBoundsToSprite(x, y);


		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		
		if(isClickable()) {
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
			if(toDomain) {
				Game.getInstance().addEventToDomain(event);;
			}else {
				Game.getInstance().addEventToUI(event);
			}
		}
		
	}

	@Override
	public void onMouseOver(Point p) {
		// TODO Auto-generated method stub
		currSprite = tintedIcon;
	}

	@Override
	public void mouseNotOver(Point p) {
		// TODO Auto-generated method stub
		currSprite = icon;
	}

	

}
