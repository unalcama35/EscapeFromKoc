package ui.drawables;

import domain.gameObjects.GameObject;
import ui.SpriteManager;
import ui.uiObjects.DialogUI;
import ui.uiObjects.UIObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogDrawable implements Drawable{

    private DialogUI dialog;

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub

        BufferedImage sprite = SpriteManager.getInstance().getSprite(dialog.getCurrSprite());
        g2.drawImage(sprite, dialog.getRect().x, dialog.getRect().y,null);

        g2.setFont(new Font("PC Senior", Font.PLAIN, 7));
        int width = g2.getFontMetrics().stringWidth(dialog.getText());
        int height = g2.getFontMetrics().getHeight();
        g2.setColor(Color.white);

        if(dialog.getCentered()){
            g2.drawString(dialog.getText(), (int) (dialog.getRect().getCenterX() - width / 2), (int) (dialog.getRect().getCenterY() - height / 2));
        }else{
            int y = (int) (dialog.getRect().getY() + 20);
            for (String line : dialog.getText().split("\n")){
                g2.drawString(line, dialog.getRect().x + 10, y += g2.getFontMetrics().getHeight());
            }
        }



        DrawableManager.getInstance().addRefAndDraw(g2, dialog.getButton().getDrawableType(), dialog.getButton());


    }

    @Override
    public void setObjRef(GameObject obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setObjRef(UIObject obj) {
        // TODO Auto-generated method stub
        dialog = (DialogUI) obj;
    }

    @Override
    public DrawableType getType() {
        // TODO Auto-generated method stub
        return DrawableType.DIALOG;
    }
}
