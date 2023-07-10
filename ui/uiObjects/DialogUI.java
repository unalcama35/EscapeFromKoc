package ui.uiObjects;

import domain.gameLogic.GameEventType;
import domain.gameLogic.scenes.Scene;
import ui.SpriteManager;
import ui.SpriteType;
import ui.drawables.DrawableType;
import ui.uiObjects.buttons.ButtonUI;

import java.awt.*;

public class DialogUI extends UIObject{

    private String text;
    private ButtonUI button;
    protected UIObject hoveredObj;

    private boolean centered;

    public DialogUI(String text, int x, int y) {
        super("dialog");
        this.text = text;
        icon = SpriteType.DIALOG_PANEL;
        currSprite = icon;
        clickable = true;
        setBoundsToSprite(x, y);
        centered = true;

        int buttonX = (int) (rect.getCenterX() - SpriteManager.getInstance().getSprite(SpriteType.OKAY_BUTTON).getWidth() / 2);
        int buttonY = (int) (rect.getCenterY() - SpriteManager.getInstance().getSprite(SpriteType.OKAY_BUTTON).getHeight() / 2) + 55;
        button = new ButtonUI("close dialog button", buttonX,buttonY, GameEventType.CLOSE_DIALOG_WINDOW, false,SpriteType.OKAY_BUTTON, SpriteType.TINTED_OKAY_BUTTON);
    }

    public DialogUI(String text, int x, int y, GameEventType type) {
        super("dialog");
        this.text = text;
        icon = SpriteType.DIALOG_PANEL;
        currSprite = icon;
        clickable = true;
        setBoundsToSprite(x, y);
        centered = true;

        int buttonX = (int) (rect.getCenterX() - SpriteManager.getInstance().getSprite(SpriteType.OKAY_BUTTON).getWidth() / 2);
        int buttonY = (int) (rect.getCenterY() - SpriteManager.getInstance().getSprite(SpriteType.OKAY_BUTTON).getHeight() / 2) + 55;
        button = new ButtonUI("close dialog button", buttonX,buttonY, type, true,SpriteType.OKAY_BUTTON, SpriteType.TINTED_OKAY_BUTTON);
    }

    public DialogUI(int x, int y, SpriteType spriteType){
        super("dialog");
        this.text = generateHelpText();
        icon = spriteType;
        currSprite = icon;
        clickable = true;
        setBoundsToSprite(x, y);
        centered = false;

        int buttonX = (int) (rect.getCenterX() - SpriteManager.getInstance().getSprite(SpriteType.OKAY_BUTTON).getWidth() / 2);
        int buttonY = (int) (rect.getMaxY() - SpriteManager.getInstance().getSprite(SpriteType.OKAY_BUTTON).getHeight() / 2 - 55) ;
        button = new ButtonUI("close dialog button", buttonX,buttonY, GameEventType.CLOSE_LOAD_MENU, false,SpriteType.OKAY_BUTTON, SpriteType.TINTED_OKAY_BUTTON);
    }

    private String generateHelpText() {
        String movement = "Movement:\n• Use 'W' key to move up\n• Use 'D' key to move right\n• Use 'S' key to move down\n• Use 'A' key to move left";
        String objects = "\n\nInteracting with objects:\n• Left click on objects with the mouse to search for keys\n• You can only interact with objects when you are next to them";
        String aliens = "\n\nAliens:\n• Blind Alien: This type of alien is blind and moves randomly around the building. \nAvoid getting too close or it will kill you. \nYou can throw a plastic bottle (power-up) to distract it.\n• Shooter Alien: This type of alien shoots bullets every second, \navoid getting too close less than 4 squares or it will kill you. \nYou can use a protection vest (power-up) to protect yourself.\n• Time-wasting Alien: This type of alien changes the location of \nthe key randomly every 5 seconds. It cannot be killed or distracted.";
        String powerUps = "\n\nPower-ups:\n• Extra life: Collect this power-up to gain an extra life.\n• Protection vest: Collect this power-up to \nprotect yourself from the Shooter Alien's bullets.\n• Plastic bottle: Collect this power-up to throw and distract the Blind Alien.";
        String game = "\n\nGame:\n• The game is over if you lose all your lives\n• The game is won if you find all the keys in the right order before the time limit.";

       return movement+objects+aliens+powerUps+game;
    }

    @Override
    public void onClick(Point p, Scene scene) {
        if(button.getRect().contains(p)){
            button.onClick(p, scene);
        }
    }

    @Override
    public void onMouseOver(Point p) {
        UIObject mouseOverObj = null;

        if(button.getRect().contains(p) && button.isClickable()) {
            //System.out.println(obj.name);
            button.onMouseOver(p);
            mouseOverObj = button;
            hoveredObj = button;
            //break;
        }

		if(mouseOverObj == null && hoveredObj != null) {
            //UIObjects.stream().forEach(obj -> obj.setFocus(false));
            hoveredObj.mouseNotOver(p);
            hoveredObj = null;
        }
    }
    public boolean getCentered(){return centered;}

    @Override
    public void mouseNotOver(Point p) {

    }

    public String getText(){return text;}

    public UIObject getButton() {
        return button;
    }

    @Override
    public DrawableType getDrawableType(){
        return DrawableType.DIALOG;
    }
}
