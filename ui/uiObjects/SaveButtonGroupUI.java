package ui.uiObjects;

import domain.gameLogic.Game;
import domain.gameLogic.scenes.Scene;
import ui.SpriteManager;
import ui.SpriteType;
import ui.drawables.DrawableType;
import ui.uiObjects.buttons.RadioButtonUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class SaveButtonGroupUI extends ButtonGroupUI{



    private boolean toDB;


    public SaveButtonGroupUI(int x, int y, int width, int height) {
        super("button group", x, y, width, height);

        toDB = false;
        buildButtons(x,y);

        buttons.get(0).setFocused(true);
        selectedButton = buttons.get(0);
    }

    private void buildButtons(int x, int y){
        buttons = new ArrayList<RadioButtonUI>();

        BufferedImage localButtonImg = SpriteManager.getInstance().getSprite(SpriteType.LOCAL_BUTTON);
        BufferedImage databaseButtonImg = SpriteManager.getInstance().getSprite(SpriteType.DATABASE_BUTTON);


        RadioButtonUI localButton = new RadioButtonUI(x - (localButtonImg.getWidth() + databaseButtonImg.getWidth() + 50) / 2,y - localButtonImg.getHeight(), SpriteType.LOCAL_BUTTON,SpriteType.TINTED_LOCAL_BUTTON);
        buttons.add(localButton);

        RadioButtonUI databaseButton = new RadioButtonUI((int) (localButton.getRect().getMaxX() + 50),y - localButtonImg.getHeight(), SpriteType.DATABASE_BUTTON,SpriteType.TINTED_DATABASE_BUTTON);
        buttons.add(databaseButton);
        this.getRect().x = localButton.getRect().x - 50;
        this.getRect().y = localButton.getRect().y - 50;
        this.getRect().width = (int) databaseButton.getRect().getMaxX() - this.getRect().x + 50;
        this.getRect().height = (int) databaseButton.getRect().getMaxY() - this.getRect().y + 50;


    }
    @Override
    public void onClick(Point p, Scene scene) {
        clickOnUIObject(p);
    }

    @Override
    public void onMouseOver(Point p) {
        UIObject mouseOverObj = null;
        for(UIObject obj : this.buttons) {
            if(obj.getRect().contains(p) && obj.isClickable()) {
                //System.out.println(obj.name);
                obj.onMouseOver(p);
                mouseOverObj = obj;
                hoveredObj = obj;
                //break;
            }

        }

        if(mouseOverObj == null && hoveredObj != null) {
            //UIObjects.stream().forEach(obj -> obj.setFocus(false));
            hoveredObj.mouseNotOver(p);
            hoveredObj = null;
        }
    }

    @Override
    public void mouseNotOver(Point p) {

    }

    @Override
    public DrawableType getDrawableType() {
        // TODO Auto-generated method stub
        return DrawableType.BUTTON_GROUP;
    }
    private void clickOnUIObject(Point p) {

        RadioButtonUI clickedObj = null;

        if(buttons.get(0).getRect().contains(p.x,p.y)){
            toDB = false;
        } else if (buttons.get(1).getRect().contains(p.x,p.y)) {
            toDB = true;
        }

        for(RadioButtonUI obj : this.buttons) {
            if(obj.getRect().contains(p)) {
                clickedObj = obj;
                obj.onClick(p, Game.getInstance().getCurrScene());
                if(selectedButton != null) selectedButton.setFocused(false);
                selectedButton = obj;
                selectedButton.setFocused(true);
            }
        }

        if(clickedObj == null && selectedButton != null) {
            //UIObjects.stream().forEach(obj -> obj.setFocus(false));
            selectedButton.setFocused(false);
            selectedButton = null;
        }

    }

    public boolean isToDB(){return toDB;}

    public List<RadioButtonUI> getButtons() {
        return buttons;
    }
}
