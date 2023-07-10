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


public class LoadButtonGroupUI extends ButtonGroupUI{
    private ScrollViewUI saveScrollView;

    private boolean fromDB;


    public LoadButtonGroupUI(int x, int y, int width, int height, ScrollViewUI saveScrollView) {
        super("button group", x, y, width, height);

        fromDB = false;
        this.saveScrollView = saveScrollView;
        buildButtons(x,y);

        try{
            saveScrollView.updateSaveLabels(fromDB);
        }catch(Exception e){
            //e.printStackTrace();
        }
        buttons.get(0).setFocused(true);
        selectedButton = buttons.get(0);
    }

    private void buildButtons(int x, int y){
        buttons = new ArrayList<RadioButtonUI>();

        BufferedImage localButtonImg = SpriteManager.getInstance().getSprite(SpriteType.LOCAL_BUTTON);
        BufferedImage databaseButtonImg = SpriteManager.getInstance().getSprite(SpriteType.DATABASE_BUTTON);

        RadioButtonUI localButton = new RadioButtonUI(x - (localButtonImg.getWidth() + databaseButtonImg.getWidth() + 50) / 2,y, SpriteType.LOCAL_BUTTON,SpriteType.TINTED_LOCAL_BUTTON);
        buttons.add(localButton);

        RadioButtonUI databaseButton = new RadioButtonUI((int) (localButton.getRect().getMaxX() + 50),y, SpriteType.DATABASE_BUTTON,SpriteType.TINTED_DATABASE_BUTTON);
        buttons.add(databaseButton);

        this.getRect().x = localButton.getRect().x - 50;
        this.getRect().y = localButton.getRect().y - 50;
        this.getRect().width = (int) databaseButton.getRect().getMaxX() - this.getRect().x + 10;
        this.getRect().height = (int) databaseButton.getRect().getMaxY() - this.getRect().y + 10;

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
            fromDB = false;
        } else if (buttons.get(1).getRect().contains(p.x,p.y)) {
            fromDB = true;
        }

        for(RadioButtonUI obj : this.buttons) {
            if(obj.getRect().contains(p)) {
                clickedObj = obj;
                obj.onClick(p, Game.getInstance().getCurrScene());
                if(selectedButton != null) selectedButton.setFocused(false);
                selectedButton = obj;
                selectedButton.setFocused(true);
                saveScrollView.updateSaveLabels(fromDB);
            }
        }

        if(clickedObj == null && selectedButton != null) {
            //UIObjects.stream().forEach(obj -> obj.setFocus(false));
            selectedButton.setFocused(false);
            selectedButton = null;
        }

    }

    public boolean isFromDB(){return fromDB;}


}
