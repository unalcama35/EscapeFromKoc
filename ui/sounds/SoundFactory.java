package ui.sounds;

public class SoundFactory {

public static Sound createSound(SoundType type) {
		
		switch(type) {
			case PLAYER_HIT:
				return new PlayerHitSound();
			case BACKGROUND_SOUND:
				return new BackgroundSound();
			case WALKING_SOUND:
				return new WalkingSound();
			case LASER_SOUND:
				return new LaserSound();
			case DOOR_SOUND:
				return new DoorSound();
			case WALL_IMPACT_SOUND:
				return new WallImpactSound();
			case CLICK_SOUND:
				return new ClickSound();
			case OBJ_PLACE_SOUND:
				return new ObjectPlacingSound();
			default:
				break;				
			}
		
		return null;
		
	}
	
}
