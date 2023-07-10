package ui.uiBuilders;

import ui.SpriteManager;
import ui.SpriteType;
import ui.uiObjects.DialogUI;
import ui.uiObjects.UIObject;
import utility.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HelpMenuUIBuilder implements UIBuilder {
    @Override
    public List<UIObject> buildUI() {
        List<UIObject> UIObjects = new ArrayList<UIObject>();
        Rectangle screen = new Rectangle(0,0, Utilities.getScreenSize().width, Utilities.getScreenSize().height);

//        SavePanelUI savePanel = new SavePanelUI((int)screen.getCenterX(), (int)screen.getCenterY());
//        UIObjects.add(savePanel);

       // UIObjects.add(new ButtonUI("close help menu", (int) savePanel.getRect().getMaxX() - SpriteManager.getInstance().getSprite(SpriteType.CROSS_BUTTON).getWidth(), savePanel.getRect().y, GameEventType.CLOSE_LOAD_MENU, false, SpriteType.CROSS_BUTTON, SpriteType.TINTED_CROSS_BUTTON));

        int x = (int) (Utilities.getScreenSize().getWidth() / 2 - SpriteManager.getInstance().getSprite(SpriteType.LOAD_PANEL).getWidth() / 2);
        int y = (int) (Utilities.getScreenSize().getHeight() / 2 - SpriteManager.getInstance().getSprite(SpriteType.LOAD_PANEL).getHeight() / 2);

        UIObjects.add(new DialogUI(x,y, SpriteType.LOAD_PANEL));

        return UIObjects;
    }
}
