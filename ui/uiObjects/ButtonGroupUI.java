package ui.uiObjects;

import domain.gameLogic.scenes.Scene;
import ui.uiObjects.buttons.RadioButtonUI;

import java.awt.*;
import java.util.List;

public class ButtonGroupUI extends UIObject{
    protected List<RadioButtonUI> buttons;
    protected UIObject hoveredObj;

    protected RadioButtonUI selectedButton;

    public ButtonGroupUI(String text, int x, int y, int width, int height) {
        super(text, x, y, width, height);
    }

    @Override
    public void onClick(Point p, Scene scene) {

    }

    public List<RadioButtonUI> getButtons() {
        return buttons;
    }

    @Override
    public void onMouseOver(Point p) {

    }

    @Override
    public void mouseNotOver(Point p) {

    }
}
