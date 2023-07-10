package ui.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineListener;

public class LaserSound extends Sound implements LineListener{


    public LaserSound() {
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
				inStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/laser.wav"));
				clip = AudioSystem.getClip();
				clip.addLineListener(this);
				clip.open(inStream);
				FloatControl gainControl = 
					    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-25.0f); // Reduce volume by 10 decibels.
				clip.start();	
			}catch(Exception e) {
			
			}
		}
	}

	@Override
	public SoundType getSoundType() {
		// TODO Auto-generated method stub
		return SoundType.LASER_SOUND;
	}


}
