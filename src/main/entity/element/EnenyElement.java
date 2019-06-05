package main.entity.element;

import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class EnenyElement extends UnitGameObject{
	public EnenyElement(int x, int y, int width, int height)
	  {
	    super(ImageUtils.generateImageFromUrl(UrlConstants.ENEMY_PART_URL), x, y, width, height);
	  }
}
