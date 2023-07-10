package ui.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public abstract class Sound implements LineListener{

    protected Clip clip;
    protected AudioInputStream inStream;
	
    boolean isPlaybackCompleted;

	public abstract void playSound();
	
	public void stopSound() {
		if(clip != null) clip.stop();
	}
	
	public abstract SoundType getSoundType();
	
	@Override
	public void update(LineEvent event) {
		// TODO Auto-generated method stub
		if (LineEvent.Type.START == event.getType()) {
            isPlaybackCompleted = false;
        } else if (LineEvent.Type.STOP == event.getType()) {
            isPlaybackCompleted = true;
        }
	}
	
}
