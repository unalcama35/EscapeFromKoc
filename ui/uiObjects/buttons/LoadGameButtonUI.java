package ui.uiObjects.buttons;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.uiObjects.LoadButtonGroupUI;
import ui.uiObjects.ScrollViewUI;

import java.awt.*;

public class LoadGameButtonUI extends ButtonUI{
	
	private ScrollViewUI filesScrollView;
	private LoadButtonGroupUI radioButtonsGroup;

	public LoadGameButtonUI(int x, int y, SpriteType spriteType, SpriteType tintedSprite, ScrollViewUI filesScrollView, LoadButtonGroupUI radioButtonsGroup) {
		super("load game", x, y, null, false, spriteType, tintedSprite);
		// TODO Auto-generated constructor stub
		this.filesScrollView = filesScrollView;
		this.radioButtonsGroup = radioButtonsGroup;
	}
	
	@Override
	public void onClick(Point p, Scene scene) {
	
		if(isClickable()) {
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
			Game.getInstance().loadGame(filesScrollView.getSelectedFileID(), radioButtonsGroup.isFromDB());
		}
		
	}
}
