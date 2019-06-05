package main.entity.element;

import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class BoatElement extends UnitGameObject{

	public BoatElement(int x, int y, int width, int height)
  {
    super(ImageUtils.generateImageFromUrl(UrlConstants.BOAT_PART), x, y, width, height);
  }
}
