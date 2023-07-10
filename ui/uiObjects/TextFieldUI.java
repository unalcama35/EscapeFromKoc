package ui.uiObjects;

import domain.gameLogic.scenes.Scene;
import ui.SpriteType;
import ui.drawables.DrawableType;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TextFieldUI extends UIObject{

	private String text;
	
	public TextFieldUI(int x, int y) {
		super("textfield");
		// TODO Auto-generated constructor stub
		icon = SpriteType.TEXT_FIELD;
		currSprite = icon;
		
		text = "";
		
		focused = false;
		//rect = new Rectangle(x,y,150,25);
		setBoundsToSprite(x, y );
	}

	
	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		focused = true;
	}
	
	public void addChar(KeyEvent e) {
		
        char c = e.getKeyChar();

		
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && text.length() > 0) {
			text = text.substring(0, text.length()-1);
		}else if(Character.isLetter(c) || Character.isDigit(c) || Character.getType(c) == Character.OTHER_PUNCTUATION){
			text += e.getKeyChar();
		}
			//else if(KeyEvent.getKeyText(e.getKeyCode()).length() == 1 || e.getKe){
//			text += e.getKeyChar();
//		}
	}
	
	public String getText() {
		return text;
	}



	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.TEXT_FIELD;
	}


	@Override
	public void onMouseOver(Point p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseNotOver(Point p) {
		// TODO Auto-generated method stub
		
	}
	


}
