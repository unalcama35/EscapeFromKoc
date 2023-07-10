package ui.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;

public class DoorSound extends Sound implements LineListener{

	public DoorSound() {
		isPlaybackCompleted = true;
	}
	
	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		if(isPlaybackCompleted) {
			try {	
				//isPlaybackCompleted = false;
				AudioInputStream inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/door.wav"));
				Clip hurtClip = AudioSystem.getClip();
				hurtClip.addLineListener(this);
				hurtClip.open(inStream);
				hurtClip.start();	
			}catch(Exception e) {
				
			}
		}
	}

	@Override
	public SoundType getSoundType() {
		// TODO Auto-generated method stub
		return SoundType.DOOR_SOUND;
	}

	

}
