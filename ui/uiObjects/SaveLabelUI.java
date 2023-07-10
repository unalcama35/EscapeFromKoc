package ui.uiObjects;

import domain.gameLogic.scenes.Scene;
import ui.drawables.DrawableType;

import java.awt.*;

public class SaveLabelUI extends UIObject{

	private String fileName;
	private String id;
	
	
	public SaveLabelUI(String fileName, String id, int x, int y, int width) {
		super("save label");
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		this.id = id;
		rect = new Rectangle(x, y, width, 50);
	}

	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMouseOver(Point p) {

	}

	@Override
	public void mouseNotOver(Point p) {

	}


	public String getFileName(){
		return fileName;
	}
	public String getID() {
		return id;
	}
	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SAVE_LABEL;
	} 
}
