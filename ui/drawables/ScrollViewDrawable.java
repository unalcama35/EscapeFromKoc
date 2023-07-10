/**
 * 
 */
package ui.drawables;

import domain.gameObjects.GameObject;
import ui.uiObjects.SaveLabelUI;
import ui.uiObjects.ScrollViewUI;
import ui.uiObjects.UIObject;

import java.awt.*;

/**
 * @author lenovo
 *
 */
public class ScrollViewDrawable implements Drawable {

	private ScrollViewUI scrollView;
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		Rectangle rect = scrollView.getRect();
		
		
		//g2.setColor(Color.gray);
		g2.setColor(new Color(176,176,176,100));
		g2.fillRect(rect.x, rect.y, rect.width, rect.height);
		g2.setColor(Color.black);
		for(SaveLabelUI saveLabel:scrollView.getLabels()) {
			DrawableManager.getInstance().addRefAndDraw(g2, saveLabel.getDrawableType(), saveLabel);
			
			//g2.drawString(saveLabel.getFileName(), saveLabel.getRect().x, saveLabel.getRect().y + height);
		}
	}

	@Override
	public void setObjRef(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObjRef(UIObject obj) {
		// TODO Auto-generated method stub
		scrollView = (ScrollViewUI) obj;
	}

	@Override
	public DrawableType getType() {
		// TODO Auto-generated method stub
		return DrawableType.SCROLL_VIEW;
	}

}
