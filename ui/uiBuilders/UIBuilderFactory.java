package ui.uiBuilders;

import ui.uiBuilders.sceneUIBuilders.BuildingModeUIBuilder;
import ui.uiBuilders.sceneUIBuilders.LevelUIBuilder;
import ui.uiBuilders.sceneUIBuilders.LoginUIBuilder;

public class UIBuilderFactory {
	
	public static UIBuilder getSceneUI(UIType type) {
		
		switch(type) {
			case LOGIN_SCENE:
				return new LoginUIBuilder();
			case BUILDING_MODE_SCENE:
				return new BuildingModeUIBuilder();
			case LEVEL_SCENE:
				return new LevelUIBuilder();
			case LEVEL_MENU:
				return new LevelMenuUIBuilder();
			case SAVE_GAME_MENU:
				return new SaveWindowUIBuilder();
			case LOAD_GAME_MENU:
				return new LoadWindowUIBuilder();
			case MAIN_MENU:
				return new MainMenuUIBuilder();
			case HELP_MENU:
				return new HelpMenuUIBuilder();
			default:
				break;				
			}
		
		return null;
		
	}
}
