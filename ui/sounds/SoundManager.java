package ui.sounds;

import java.util.ArrayList;
import java.util.List;

public class SoundManager {
	
	private List<Sound> soundsList;
	
	public SoundManager() {
		loadSounds();
	}
	
	private void loadSounds() {
		soundsList = new ArrayList<Sound>();

		soundsList.add(SoundFactory.createSound(SoundType.PLAYER_HIT));
		soundsList.add(SoundFactory.createSound(SoundType.BACKGROUND_SOUND));
		soundsList.add(SoundFactory.createSound(SoundType.WALKING_SOUND));
		soundsList.add(SoundFactory.createSound(SoundType.LASER_SOUND));
		soundsList.add(SoundFactory.createSound(SoundType.DOOR_SOUND));
		soundsList.add(SoundFactory.createSound(SoundType.WALL_IMPACT_SOUND));
		soundsList.add(SoundFactory.createSound(SoundType.CLICK_SOUND));
		soundsList.add(SoundFactory.createSound(SoundType.OBJ_PLACE_SOUND));

	}
	
	public Sound getSound(SoundType soundType) {
		
		for(Sound sound : soundsList) {
			if(sound.getSoundType() == soundType) {
				return sound;
			}
		}
		
		return null;
		
	}
	

}
