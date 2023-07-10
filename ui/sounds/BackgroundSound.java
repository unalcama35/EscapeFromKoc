package ui.sounds;

public class BackgroundSound extends Sound{

	@Override
	public void playSound() {
		// TODO Auto-generated method stub		
//		try {
//			String bip = getClass().getResource("/sounds/sweden.mp3").toString();
//		    com.sun.javafx.application.PlatformImpl.startup(() -> {});
//			Media media = new Media(new File("src/sounds/sweden.mp3").toURI().toString());
//		    MediaPlayer mp3Player = new MediaPlayer(media);
//		    mp3Player.play();
//		} catch (Exception ex) {
//		    System.out.println("Error occured during playback process:" + ex.getMessage());
//		}
	}

	@Override
	public SoundType getSoundType() {
		// TODO Auto-generated method stub
		return SoundType.BACKGROUND_SOUND;
	}


}
