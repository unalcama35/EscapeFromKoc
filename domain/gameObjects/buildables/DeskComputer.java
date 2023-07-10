package domain.gameObjects.buildables;

import ui.SpriteType;

public class DeskComputer extends BuildableGameObject{

	public DeskComputer(int x, int y) {
		super("desk computer", x, y, 32, 32);
		
		currSprite = SpriteType.DESK_COMPUTER;
		
		// TODO Auto-generated constructor stub
	}


	@Override
	protected BuildableGameObject newInstance(int x, int y) {
		// TODO Auto-generated method stub
		return new DeskComputer(x,y);
	}

}
