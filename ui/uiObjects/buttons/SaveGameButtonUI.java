package ui.uiObjects.buttons;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.animations.BuildableObjClickAnim;
import ui.uiObjects.SaveButtonGroupUI;
import ui.uiObjects.TextFieldUI;

import java.awt.*;

public class SaveGameButtonUI extends ButtonUI{
	
	private TextFieldUI fileNameTextField;

	private SaveButtonGroupUI saveButtonGroup;
	public SaveGameButtonUI(int x, int y, SpriteType spriteType, SpriteType tinted, TextFieldUI textfield, SaveButtonGroupUI saveButtonGroup) {
		super("save game", x, y, null, false, spriteType, tinted);
		// TODO Auto-generated constructor stub
		fileNameTextField = textfield;
		this.saveButtonGroup = saveButtonGroup;
	}
	
	@Override
	public void onClick(Point p, Scene scene) {
	
		if(isClickable()) {
			Game.getInstance().addAnimation(new BuildableObjClickAnim(this.rect, scene));
			Game.getInstance().saveGame(fileNameTextField.getText(), saveButtonGroup.isToDB());
		}
		
	}

}
