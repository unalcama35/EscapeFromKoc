package ui.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class ClickSound extends Sound{

	public ClickSound() {
    	isPlaybackCompleted = true;
    	
	}
	
	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		if(isPlaybackCompleted) {		
			try {	
				long startTime = System.currentTimeMillis();
				AudioInputStream inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/click.wav"));
				
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
		return SoundType.CLICK_SOUND;
	}

}
