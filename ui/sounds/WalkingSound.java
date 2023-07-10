package ui.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;

public class WalkingSound extends Sound implements LineListener{

    private Clip clip;
	
    public WalkingSound() {
    	isPlaybackCompleted = true;
    	
		try {
			clip = AudioSystem.getClip();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
	@Override
	public void playSound() {
		// TODO Auto-generated method stub
		if(isPlaybackCompleted) {
			try {	
				AudioInputStream inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/walking_sound.wav"));
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
		return SoundType.WALKING_SOUND;
	}

	@Override
	public void stopSound() {
		if(clip != null) clip.stop();
	}

}