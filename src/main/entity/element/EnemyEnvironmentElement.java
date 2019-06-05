package main.entity.element;

import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class EnemyEnvironmentElement extends UnitGameObject{

	public EnemyEnvironmentElement(int x, int y, int width, int height) {
		super(ImageUtils.generateImageFromUrl(UrlConstants.FIRE_BALL_URL), x, y, width, height);
	}

}
