package ui.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class PlayerHitSound extends Sound implements LineListener{

	
	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		try {	
			AudioInputStream inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/hurt_sound.wav"));
			Clip hurtClip = AudioSystem.getClip();
			hurtClip.addLineListener(this);
			hurtClip.open(inStream);
			hurtClip.start();	
		}catch(Exception e) {
			
		}		
	}

	@Override
	public SoundType getSoundType() {
		// TODO Auto-generated method stub
		return SoundType.PLAYER_HIT;
	}

}
