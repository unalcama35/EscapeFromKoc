package ui;

import utility.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SpriteManager {
	
	private Map<SpriteType, BufferedImage> mapOfSprites = new HashMap<SpriteType, BufferedImage>();
	private static SpriteManager single_instance = null;


	protected final Color tintColor = new Color(0, 0, 0, 100);

	private Font pcSeniorFont;

	
	private SpriteManager() {
		loadImgs();
	}
	
	public static SpriteManager getInstance()
    {

        if (single_instance == null) {
        	single_instance = new SpriteManager();

        }

        return single_instance;
    }

	private void loadImgs() {
		// TODO Auto-generated method stub
		mapOfSprites.put(SpriteType.BOOKSHELF, loadImg("/images/book-shelf")); 
		mapOfSprites.put(SpriteType.CHAIR, loadImg("/images/chair_north")); 
		mapOfSprites.put(SpriteType.DESK, loadImg("/images/desk1")); 
		mapOfSprites.put(SpriteType.DESK_COMPUTER, loadImg("/images/desk2")); 
		loadImgs("BLIND_ALIEN", "/images/blind_alien");
		mapOfSprites.put(SpriteType.BULLET, loadImg("/images/bullet")); 
		loadImgs("PLAYER", "/images/player");
		mapOfSprites.put(SpriteType.SHOOTER_ALIEN, loadImg("/images/shooter_alien0")); 
		loadImgs("TIME_WASTING_ALIEN", "/images/time_wasting_alien");
		mapOfSprites.put(SpriteType.BOTTLE, loadImg("/images/bottle")); 
		loadImgs("EXTRA_LIFE", "/images/heart");
		loadImgs("EXTRA_TIME", "/images/clock");
		loadImgs("HINT", "/images/hint");
		mapOfSprites.put(SpriteType.PROTECTION_VEST, loadImg("/images/armor")); 
		loadImgs("DOOR", "/images/door");
		mapOfSprites.put(SpriteType.TALL_WALL, loadImg("/images/tallWall")); 
		loadImgs("LEVEL_LABEL", "/images/level_label");
		mapOfSprites.put(SpriteType.RANDOMISE_BUTTON, loadImg("/images/randomise_button")); 
		mapOfSprites.put(SpriteType.SAVE_LEVEL_BUTTON, loadImg("/images/save_level_button")); 
		mapOfSprites.put(SpriteType.RESUME_BUTTON, loadImg("/images/resume_button")); 
		mapOfSprites.put(SpriteType.LOAD_BUTTON, loadImg("/images/load_button")); 
		mapOfSprites.put(SpriteType.HELP_BUTTON, loadImg("/images/help_button")); 
		mapOfSprites.put(SpriteType.QUIT_BUTTON, loadImg("/images/quit_button")); 
		mapOfSprites.put(SpriteType.SAVE_GAME_MENU_BUTTON, loadImg("/images/save_button")); 
		loadImgs("IMPACT", "/images/impact");
		loadImgs("FIELD_POWERUP", "/images/field");
		mapOfSprites.put(SpriteType.TINTED_RANDOMISE_BUTTON, Utilities.tint(loadImg("/images/randomise_button"),tintColor)); 
		mapOfSprites.put(SpriteType.TINTED_SAVE_LEVEL_BUTTON, Utilities.tint(loadImg("/images/save_level_button"),tintColor)); 
		mapOfSprites.put(SpriteType.TINTED_RESUME_BUTTON, Utilities.tint(loadImg("/images/resume_button"),tintColor)); 
		mapOfSprites.put(SpriteType.TINTED_LOAD_BUTTON, Utilities.tint(loadImg("/images/load_button"),tintColor)); 
		mapOfSprites.put(SpriteType.TINTED_HELP_BUTTON, Utilities.tint(loadImg("/images/help_button"),tintColor)); 
		mapOfSprites.put(SpriteType.TINTED_QUIT_BUTTON, Utilities.tint(loadImg("/images/quit_button"),tintColor)); 
		mapOfSprites.put(SpriteType.TINTED_SAVE_GAME_MENU_BUTTON, Utilities.tint(loadImg("/images/save_button"),tintColor)); 
		mapOfSprites.put(SpriteType.LOAD_PANEL, loadImg("/images/save_panel")); 
		mapOfSprites.put(SpriteType.SAVE_PANEL, loadImg("/images/save_panel")); 
		mapOfSprites.put(SpriteType.MENU_PANEL, loadImg("/images/menu_panel")); 
		mapOfSprites.put(SpriteType.TEXT_FIELD, loadImg("/images/text_field")); 
		mapOfSprites.put(SpriteType.LOGIN_BUTTON, loadImg("/images/login_button")); 
		mapOfSprites.put(SpriteType.TINTED_LOGIN_BUTTON, Utilities.tint(loadImg("/images/login_button"),tintColor)); 
		mapOfSprites.put(SpriteType.CROSS_BUTTON, loadImg("/images/cross_button")); 
		mapOfSprites.put(SpriteType.TINTED_CROSS_BUTTON, Utilities.tint(loadImg("/images/cross_button"),tintColor));
		mapOfSprites.put(SpriteType.GAME_TITLE_LABEL, loadImg("/images/title_label"));
		mapOfSprites.put(SpriteType.USER_NAME_LABEL, loadImg("/images/username_label"));
		mapOfSprites.put(SpriteType.PASSWORD_LABEL, loadImg("/images/password_label"));
		mapOfSprites.put(SpriteType.PLAY_BUTTON, loadImg("/images/play_button"));
		mapOfSprites.put(SpriteType.TINTED_PLAY_BUTTON, Utilities.tint(loadImg("/images/play_button"),tintColor));
		mapOfSprites.put(SpriteType.DIGIT_0, loadImg("/images/0"));
		mapOfSprites.put(SpriteType.DIGIT_1, loadImg("/images/1"));
		mapOfSprites.put(SpriteType.DIGIT_2, loadImg("/images/2"));
		mapOfSprites.put(SpriteType.DIGIT_3, loadImg("/images/3"));
		mapOfSprites.put(SpriteType.DIGIT_4, loadImg("/images/4"));
		mapOfSprites.put(SpriteType.DIGIT_5, loadImg("/images/5"));
		mapOfSprites.put(SpriteType.DIGIT_6, loadImg("/images/6"));
		mapOfSprites.put(SpriteType.DIGIT_7, loadImg("/images/7"));
		mapOfSprites.put(SpriteType.DIGIT_8, loadImg("/images/8"));
		mapOfSprites.put(SpriteType.DIGIT_9, loadImg("/images/9"));
		mapOfSprites.put(SpriteType.COLON, loadImg("/images/colon"));
		mapOfSprites.put(SpriteType.LOCAL_BUTTON, loadImg("/images/local_button"));
		mapOfSprites.put(SpriteType.TINTED_LOCAL_BUTTON, Utilities.tint(loadImg("/images/local_button"),tintColor));
		mapOfSprites.put(SpriteType.DATABASE_BUTTON, loadImg("/images/database_button"));
		mapOfSprites.put(SpriteType.TINTED_DATABASE_BUTTON, Utilities.tint(loadImg("/images/database_button"),tintColor));
		mapOfSprites.put(SpriteType.REGISTER_BUTTON, loadImg("/images/register_button"));
		mapOfSprites.put(SpriteType.TINTED_REGISTER_BUTTON, Utilities.tint(loadImg("/images/register_button"),tintColor));
		mapOfSprites.put(SpriteType.DIALOG_PANEL, loadImg("/images/dialog_panel"));
		mapOfSprites.put(SpriteType.OKAY_BUTTON, loadImg("/images/okay_button"));
		mapOfSprites.put(SpriteType.TINTED_OKAY_BUTTON, Utilities.tint(loadImg("/images/okay_button"),tintColor));


		try {

			//File directory = new File("src/fonts/pcesnior.ttf");
			//System.out.println(directory.getAbsolutePath());



			pcSeniorFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/pcsenior.ttf").getAbsoluteFile());
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pcSeniorFont);
		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}





	}
	
	private void loadImgs(String name, String url) {
		int count = 0;
		
		String newUrl = url+count;
		
		for (SpriteType type : SpriteType.values()) { 
		    if(type.toString().contains(name)) {
		    	mapOfSprites.put(type, loadImg(newUrl));
		    	count++;
		    	newUrl = url+count;
		    } 
		}
		
	}
	
	public BufferedImage getSprite(SpriteType type) {
		return mapOfSprites.get(type);
	}

	protected BufferedImage loadImg(String url) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(getClass().getResource(url+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sprite;
	}

	public Font getFont(){
		return pcSeniorFont;
	}
	
	
	
}
