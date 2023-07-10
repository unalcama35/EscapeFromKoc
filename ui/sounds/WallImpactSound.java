package ui.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;

public class WallImpactSound extends Sound{

	@Override
	public void playSound() {
		// TODO Auto-generated method stub
			try {	
				AudioInputStream inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/wall_impact.wav"));
				clip = AudioSystem.getClip();
				clip.addLineListener(this);
				clip.open(inStream);
				FloatControl gainControl = 
					    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
				clip.start();	
			}catch(Exception e) {
			
			}
	}

	@Override
	public SoundType getSoundType() {
		// TODO Auto-generated method stub
		return SoundType.WALL_IMPACT_SOUND;
	}

}
