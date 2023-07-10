package ui.drawables;

import domain.gameObjects.GameObject;
import ui.uiObjects.SaveLabelUI;
import ui.uiObjects.UIObject;

import java.awt.*;

public class SaveLabelDrawable implements Drawable{
	
	private SaveLabelUI saveLabel;

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		Rectangle rect = saveLabel.getRect();
		g2.setColor(Color.black);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);

		if(saveLabel.isFocused()) {
			g2.setColor(new Color(200,30,30, 100));
			g2.fillRect(rect.x, rect.y, rect.width, rect.height);
		}else {
			g2.setColor(new Color(0,0,0, 100));
			g2.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		g2.setColor(Color.white);
		g2.setFont(new Font("PC Senior", Font.PLAIN, 12));
		//int width = g2.getFontMetrics().stringWidth(dialog.getText());
		int height = g2.getFontMetrics().getHeight();
		g2.drawString(saveLabel.getFileName(), saveLabel.getRect().x + 5, (int) (saveLabel.getRect().getCenterY() + height / 2));
	}

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		saveLabel = (SaveLabelUI) obj;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.SAVE_LABEL;
	}

}
