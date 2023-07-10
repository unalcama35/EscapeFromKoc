package ui.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineListener;

public class ObjectPlacingSound extends Sound{

	public ObjectPlacingSound() {
    	isPlaybackCompleted = true;
    	
		

    }
	
	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		if(isPlaybackCompleted) {
			try {	
				inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/obj_placing.wav"));
				clip = AudioSystem.getClip();
				clip.addLineListener(this);
				clip.open(inStream);
				clip.start();	
			}catch(Exception e) {
			
			}
		}
	}

	@Override
	public SoundType getSoundType() {
		// TODO Auto-generated method stub
		return SoundType.OBJ_PLACE_SOUND;
	}

}
