package ui.uiObjects;

import db.DatabaseConnection;
import domain.gameLogic.Game;
import domain.gameLogic.scenes.Scene;
import ui.drawables.DrawableType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScrollViewUI extends UIObject{

	private List<String> saveFileNames;
	private List<String> saveIDsDB;
	private List<String> saveNamesDB;
	private List<SaveLabelUI> saveLabels;
	
	private SaveLabelUI focusedLabel;
	
	
	public ScrollViewUI(int x, int y, int width, int height) {
		super("saves scroll view");
		// TODO Auto-generated constructor stub
		rect = new Rectangle(x,y,width,height);
		saveFileNames = Game.getInstance().getFiles();
		saveIDsDB = Game.getInstance().getSaveIDs();
		saveNamesDB = Game.getInstance().getSaveNames();

		saveLabels = buildSaveLabels(true);
	}

	private List<SaveLabelUI> buildSaveLabels(Boolean fromDB) {
		List<SaveLabelUI> saveLabels = new ArrayList<SaveLabelUI>();

		if(fromDB){
			for(int i = 0; i < saveNamesDB.size(); i++) {
				saveLabels.add(new SaveLabelUI(saveNamesDB.get(i), saveIDsDB.get(i), this.rect.x, this.rect.y + i*50, rect.width));
			}
		}else{
			for(int i = 0; i < saveFileNames.size(); i++) {
				saveLabels.add(new SaveLabelUI(saveFileNames.get(i), saveFileNames.get(i), this.rect.x, this.rect.y + i*50, rect.width));
			}
		}
		return saveLabels;
	}

	public void updateSaveLabels(Boolean fromDB){
		List<SaveLabelUI> saveLabels = new ArrayList<SaveLabelUI>();

		if(fromDB){
			for(int i = 0; i < saveNamesDB.size(); i++) {
				saveLabels.add(new SaveLabelUI(saveNamesDB.get(i), saveIDsDB.get(i), this.rect.x, this.rect.y + i*50, rect.width));
			}
		}else{
			for(int i = 0; i < saveFileNames.size(); i++) {
				saveLabels.add(new SaveLabelUI(saveFileNames.get(i), saveFileNames.get(i), this.rect.x, this.rect.y + i*50, rect.width));
			}
		}

		this.saveLabels = saveLabels;
	}
	
	
	
	@Override
	public void onClick(Point p, Scene scene) {
		// TODO Auto-generated method stub
		clickOnUIObject(p);
		
	}

	@Override
	public void onMouseOver(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseNotOver(Point p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public DrawableType getDrawableType() {
		// TODO Auto-generated method stub
		return DrawableType.SCROLL_VIEW;
	}

	public String getSelectedFileID() {
		if(focusedLabel != null) {
			return focusedLabel.getID();
		}else {
			return null;
		}
	}
	
	public List<SaveLabelUI> getLabels(){
		return saveLabels;
	}
	
	
	private void clickOnUIObject(Point p) {
		
		SaveLabelUI clickedObj = null;
		
		for(SaveLabelUI obj : this.saveLabels) {
			if(obj.getRect().contains(p)) {
				clickedObj = obj;
				obj.onClick(p, Game.getInstance().getCurrScene());
				if(focusedLabel != null) focusedLabel.setFocused(false);
				focusedLabel = obj;
				focusedLabel.setFocused(true);
			}
		}	

		if(clickedObj == null && focusedLabel != null) {
			//UIObjects.stream().forEach(obj -> obj.setFocus(false));
			focusedLabel.setFocused(false);
			focusedLabel = null;
		}
		
	}

}
