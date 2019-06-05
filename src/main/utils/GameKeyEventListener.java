package main.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.enums.DirectionEnum;

public class GameKeyEventListener extends KeyAdapter {
	private DirectionEnum direction;

	public GameKeyEventListener(DirectionEnum direction) {
		super();
		this.direction = direction;
	}

	/**
	 * @return the direction
	 */
	public DirectionEnum getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			direction = DirectionEnum.LEFT;
		}
		if (key == KeyEvent.VK_RIGHT) {
			direction = DirectionEnum.RIGHT;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	  int key = e.getKeyCode();
    if (key == KeyEvent.VK_LEFT) {
      direction = DirectionEnum.NONE;
    }
    if (key == KeyEvent.VK_RIGHT) {
      direction = DirectionEnum.NONE;
    }
  }
}