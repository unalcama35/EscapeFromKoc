package ui.drawables;

import domain.gameObjects.GameObject;
import ui.uiObjects.ButtonGroupUI;
import ui.uiObjects.UIObject;
import ui.uiObjects.buttons.RadioButtonUI;

import java.awt.*;

public class ButtonGroupDrawable implements Drawable {

    private ButtonGroupUI buttonGroup;
    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        Rectangle rect = buttonGroup.getRect();


//       g2.setColor(Color.red);
//       g2.fillRect(rect.x, rect.y, rect.width, rect.height);
//        g2.setColor(new Color(100, 0, 0, 100));
//        g2.fillRect(rect.x, rect.y, rect.width, rect.height);

        g2.setColor(Color.black);
        for(RadioButtonUI radioButton:buttonGroup.getButtons()) {
            DrawableManager.getInstance().addRefAndDraw(g2, radioButton.getDrawableType(), radioButton);

            //g2.drawString(saveLabel.getFileName(), saveLabel.getRect().x, saveLabel.getRect().y + height);
        }
    }

    @Override
    public void setObjRef(GameObject obj) {

    }

    @Override
    public void setObjRef(UIObject obj) {
        buttonGroup = (ButtonGroupUI) obj;
    }

    @Override
    public DrawableType getType() {
        return DrawableType.BUTTON_GROUP;
    }
}
