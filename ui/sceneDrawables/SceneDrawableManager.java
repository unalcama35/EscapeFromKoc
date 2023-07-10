package ui.sceneDrawables;

import java.util.ArrayList;
import java.util.List;

import ui.uiBuilders.UIType;

public class SceneDrawableManager {
	
	private List<SceneDrawable> sceneDrawablesList;


	public SceneDrawableManager() {
		loadSceneDrawables();
	}
	
	private void loadSceneDrawables() {
		sceneDrawablesList = new ArrayList<SceneDrawable>();

		sceneDrawablesList.add(SceneDrawableFactory.getSceneDrawable(UIType.BUILDING_MODE_SCENE));
		sceneDrawablesList.add(SceneDrawableFactory.getSceneDrawable(UIType.LOGIN_SCENE));
		sceneDrawablesList.add(SceneDrawableFactory.getSceneDrawable(UIType.LEVEL_SCENE));



	}
	
	public SceneDrawable getSceneDrawable(UIType type) {
		
		for(SceneDrawable sceneDrawable: sceneDrawablesList) {
			if(sceneDrawable.getSceneType() == type) {
				return sceneDrawable;
			}
		}
		
		return null;	
	}
}
