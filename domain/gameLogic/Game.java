package domain.gameLogic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import db.DatabaseConnection;
import db.GameObjectAdapter;
import db.SceneAdapter;
import db.TimeWastingAlienAdapter;
import domain.gameLogic.scenes.LevelScene;
import domain.gameLogic.scenes.Scene;
import domain.gameLogic.scenes.SceneManager;
import domain.gameObjects.GameObject;
import domain.gameObjects.characters.TimeWastingAlien;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import ui.GameFrame;
import ui.animations.Animation;
import ui.sounds.SoundType;
import ui.uiBuilders.UIType;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.*;


public class Game {
	
	private static Timer timer;
	private GameFrame mainFrame;
	private SceneManager sceneManager;
	private static Game single_instance = null;
	private long startTime;

	private String loggedInUserID;

	public static Game getInstance()
    {

        if (single_instance == null) {
        	single_instance = new Game();
        }

        return single_instance;
    }

	private Game() {	
		sceneManager = new SceneManager();

		mainFrame = new GameFrame();

		startTime = System.currentTimeMillis();
		startTimer();
	}
	
	private void tick() {

	    mainFrame.render();

	    sceneManager.update();
		
	    System.out.println((System.currentTimeMillis() - startTime)+"ms took to tick");
	    startTime = System.currentTimeMillis();
				
	}
	
	
	public boolean login(String username, String password) {
		//return true;
		if(isValidCredentials(username, password)){
			HashMap<String,String> userInfo = DatabaseConnection.getInstance().getUserInfo(username);
			if (userInfo == null) {
				mainFrame.newDialog("Please enter a valid username and password");
				return false;
			} else {
				try{
					String salt = userInfo.get("salt");
					String calculatedHash = getEncryptedPassword(password, salt);
					if (calculatedHash.equals(userInfo.get("encrypted password"))) {
 						loggedInUserID = username;
						return true;
					} else {
						return false;
					}
				}
				catch(Exception e){

				}
			}
		}
		return false;

	}

	public boolean register(String username, String password){
		String salt = getNewSalt();
		String encryptedPassword = getEncryptedPassword(password, salt);

		Document userInfo = new Document("userName", username)
				.append("password", encryptedPassword)
				.append("salt", salt);

		if(!isValidCredentials(username,password)){
			return false;
		}else{
			if(DatabaseConnection.getInstance().insertUser(userInfo)){
				mainFrame.newDialog("You have successfully created an account!");
				return true;
			}else{
				mainFrame.newDialog("An account with this username already exists!");
				return false;
			}
		}

	}

	private boolean isValidCredentials(String username, String password){
		if(username.isEmpty()){
			mainFrame.newDialog("Username can not be empty!");
			return false;
		} else if (password.length() < 6) {
			mainFrame.newDialog("Password length must be greater than 5!");
			return false;
		}

		return true;

	}
	// Get a encrypted password using PBKDF2 hash algorithm
	public String getEncryptedPassword(String password, String salt){
		try{
			String algorithm = "PBKDF2WithHmacSHA1";
			int derivedKeyLength = 160; // for SHA1
			int iterations = 20000; // NIST specifies 10000

			byte[] saltBytes = Base64.getDecoder().decode(salt);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
			SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

			byte[] encBytes = f.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(encBytes);
		}catch(Exception e){
			return null;
		}
	}

	// Returns base64 encoded salt
	public String getNewSalt(){
		// Don't use Random!
		try {
			SecureRandom random = null;
			random = SecureRandom.getInstance("SHA1PRNG");
			// NIST recommends minimum 4 bytes. We use 8.
			byte[] salt = new byte[8];
			random.nextBytes(salt);
			return Base64.getEncoder().encodeToString(salt);

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}
	
	private void startTimer() {

		timer = new Timer();
		timer.scheduleAtFixedRate(new RenderTimerTask(), 0, 15);
		
		
		Timer timer1 = new Timer();
		timer1.scheduleAtFixedRate(new UpdateTimerTask(), 0, 15);

		
	}
	
	public void sceneChange(UIType sceneType) {
		mainFrame.getMainPanel().sceneChange(sceneType);
	}
	
	public void lostLife() {
		mainFrame.getMainPanel().lostLife();
	}
	
	public Scene getCurrScene() {
		return sceneManager.getCurrScene();
	}
	
	public SceneManager getSceneManager() {
		return sceneManager;
	}
	
	public void addAnimation(Animation anim) {
		mainFrame.addAnimation(anim);
	}
	
	public void addAndPlaySound(SoundType soundType) {
		mainFrame.addAndPlaySound(soundType);
	}
	
	public void playSound(SoundType soundType) {
		mainFrame.playSound(soundType);
	}
	
	public void stopSound(SoundType soundType) {
		mainFrame.stopSound(soundType);
	}
	
	public Point getMouseLoc() {
		return new Point(MouseInfo.getPointerInfo().getLocation().x - mainFrame.getComponent(0).getLocationOnScreen().x,
				MouseInfo.getPointerInfo().getLocation().y -mainFrame.getComponent(0).getLocationOnScreen().y);
	}
	
	public void saveGame(String saveId, boolean isToDB) {
		
		String url = "saves/" + saveId;
		System.out.println(saveId);
		if(saveId.isEmpty()){
			mainFrame.newDialog("Please enter a name for your save!");
		}else{
			try {
				if(isToDB){
					Gson gson = new GsonBuilder().registerTypeAdapter(Scene.class, new SceneAdapter()).registerTypeAdapter(GameObject.class, new GameObjectAdapter())
							.registerTypeAdapter(TimeWastingAlien.class, new TimeWastingAlienAdapter()).create();
					JsonElement jsonElement = gson.toJsonTree(sceneManager);
					jsonElement.getAsJsonObject().addProperty("saveName", saveId);
					jsonElement.getAsJsonObject().addProperty("userID", loggedInUserID);

					String json = gson.toJson(jsonElement);
					DatabaseConnection.getInstance().insert(Document.parse(json));
				}else{
					FileOutputStream fout = new FileOutputStream(url);
					ObjectOutputStream out = new ObjectOutputStream(fout);
					out.writeObject(sceneManager);
					out.flush();
				}
				mainFrame.newDialog("You have successfully saved the game");
			}catch(Exception e) {
				e.printStackTrace();
				mainFrame.newDialog("There was an error while saving the game");

			}
		}

	}
	
	public void loadGame(String saveId, boolean fromDB) {

	    try {
			if(fromDB){
				Gson gson = new GsonBuilder().registerTypeAdapter(Scene.class, new SceneAdapter()).registerTypeAdapter(GameObject.class, new GameObjectAdapter())
						.registerTypeAdapter(TimeWastingAlien.class, new TimeWastingAlienAdapter()).create();
				String json1 = DatabaseConnection.getInstance().getSave(saveId).toJson(JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).build());
				sceneManager = gson.fromJson(json1,SceneManager.class);
			}else{
				String filePath = "saves/"+saveId;
				System.out.println(filePath);
				FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				sceneManager = (SceneManager) ois.readObject();
				ois.close();
			}
			mainFrame.newDialog("You have successfully loaded the file");

        } catch (Exception e){
			mainFrame.newDialog("There was an error while loading the file");
			e.printStackTrace();
		}
	}
	
	public List<String> getFiles(){
		String path = "saves/";

		List<String> files = new ArrayList<String>();
		
	    // Create a File object for the folder
	    File folder = new File(path);

	    // Get the list of files in the folder
	    String[] filesArray = folder.list();

	    // Print the names of the files
	    for (String file : filesArray) {
	    	files.add(file);
	    }
		
		return files;
	}

	
	
	public void pressKey(KeyEvent e) {
		if(getCurrScene() instanceof LevelScene) {
			((LevelScene) getCurrScene()).addPressedKey(e);
		}
	}
	
	public void releaseKey(KeyEvent e) {
		if(getCurrScene() instanceof LevelScene) {
			((LevelScene) getCurrScene()).removePressedKey(e);
		}
	}
	

	public void pressMouse(MouseEvent e) {
		getCurrScene().mousePressed(e);
	}
	
	public void releaseMouse(MouseEvent e) {
		getCurrScene().mouseReleased(e);
	}

	public void addEventToDomain(GameEventType type) {
		if(type == GameEventType.OPEN_MAIN_MENU){
			sceneManager = new SceneManager();
			sceneManager.setCurrScene(0);
			mainFrame.addEvent(GameEventType.OPEN_MAIN_MENU);
		}else{
			getCurrScene().handleEvent(type);
		}
	}
	
	public void addEventToUI(GameEventType type) {
		mainFrame.addEvent(type);
	}

	public List<String> getSaveIDs() {
		return DatabaseConnection.getInstance().getSaveIDs(loggedInUserID);
	}

	public List<String> getSaveNames() {
		return DatabaseConnection.getInstance().getSaveNames(loggedInUserID);
	}


	private class RenderTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mainFrame.render();
		}
		
	}
	
	private class UpdateTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			sceneManager.update();

			//System.out.println("update");
		}
		
	}
	
}
