package domain.gameObjects.buildables;

import java.awt.Graphics2D;

import ui.SpriteType;

public class BookShelf extends BuildableGameObject{

	public BookShelf(int x, int y) {
		super("bookshelf", x, y, 32, 32);
		
		currSprite = SpriteType.BOOKSHELF;
		//loadImg("/images/book-shelf");
		
		// TODO Auto-generated constructor stub
	}
//
//	public BookShelf() {
//		super("bookshelf");
//		loadImg("/images/book-shelf");
//	}
//	
//
//	
//	public void init(int x, int y) {
//		setBoundsToSprite(x, y);
//	}

	
	@Override
	protected BuildableGameObject newInstance(int x, int y) {
		// TODO Auto-generated method stub
		return new BookShelf(x,y);
	}

}
