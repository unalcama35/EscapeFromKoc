package ui.drawables;

public class DrawableFactory {
	
	public static Drawable getDrawable(DrawableType type) {
		
		switch(type) {
			case PLAYER:
				return new PlayerDrawable();
			case SHOOTER_ALIEN:
				return new ShooterAlienDrawable();
			case SIMPLE_SPRITE:
				return new SimpleSpriteDrawable();
			case WALL:
				return new WallDrawable();
			case BULLET:
				return new BulletDrawable();
			case TEXT_FIELD:
				return new TextFieldDrawable();
			case SCROLL_VIEW:
				return new ScrollViewDrawable();
			case SAVE_LABEL:
				return new SaveLabelDrawable();
			case BUTTON_GROUP:
				return new ButtonGroupDrawable();
			case DIALOG:
				return new DialogDrawable();

			default:
				break;				
			}
		
		return null;
		
	}
	
	
	
	

}
