package main.entity.element;

import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class BulletElement extends UnitGameObject
{
  public BulletElement(int x, int y, int width, int height)
  {
    super(ImageUtils.generateImageFromUrl(UrlConstants.BULLET), x, y, width, height);
  }
}
