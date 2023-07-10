package ui.sceneDrawables;

import ui.uiBuilders.UIType;
import ui.uiBuilders.UIBuilder;
import ui.uiBuilders.sceneUIBuilders.LoginUIBuilder;

public class SceneDrawableFactory {
	
public static SceneDrawable getSceneDrawable(UIType type) {
		
		switch(type) {
			case LOGIN_SCENE:
				return new LoginSceneDrawable();
			case BUILDING_MODE_SCENE:
				return new BuildingModeSceneDrawable();
			case LEVEL_SCENE:
				return new LevelSceneDrawable();
			default:
				break;				
			}
		
		return null;
		
	}

}
