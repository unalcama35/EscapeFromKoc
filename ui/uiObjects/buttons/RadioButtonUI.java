package ui.uiObjects.buttons;

import domain.gameLogic.scenes.Scene;
import ui.SpriteType;

import java.awt.*;

public class RadioButtonUI extends ButtonUI {



    public RadioButtonUI(int x, int y, SpriteType spriteType, SpriteType tintedSprite) {
        super("radio button", x, y, null, false, spriteType, tintedSprite);
    }

    @Override
    public void setFocused(boolean focused) {
        this.focused = focused;
        if(focused){
            currSprite = tintedIcon;
        }else{
            currSprite = icon;
        }
    }


    @Override
    public void onClick(Point p, Scene scene) {

    }

    @Override
    public void onMouseOver(Point p) {
        currSprite = tintedIcon;
    }

    @Override
    public void mouseNotOver(Point p) {
        if(!focused){
            currSprite = icon;
        }

    }
}
