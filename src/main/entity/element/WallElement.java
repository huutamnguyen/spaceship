package main.entity.element;

import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class WallElement extends UnitGameObject{

  public WallElement(int x, int y, int width, int height)
  {
    super(ImageUtils.generateImageFromUrl(UrlConstants.WALL_URL), x, y, width, height);
  }
	
}
