package main.entity;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import main.constants.Constants;
import main.entity.element.BulletElement;

public class BulletComponent extends Component implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bulletSize;
	private Timer timer;
	private List<BulletElement> bulletElements;

	public BulletComponent(int bulletSize) {
		super();
		this.bulletSize = bulletSize;
		timer = new Timer(Constants.BULLET_TIME_DELAY, this);
		bulletElements = new ArrayList<>();
	}

	public void active() {
		timer.start();
	}

	public int getBulletSize() {
		return bulletSize;
	}

	public void setBulletSize(int bulletSize) {
		this.bulletSize = bulletSize;
	}

	/**
	 * @return the bulletElements
	 */
	public List<BulletElement> getBulletElements() {
		return bulletElements;
	}

	/**
	 * @param bulletElements
	 *            the bulletElements to set
	 */
	public void setBulletElements(List<BulletElement> bulletElements) {
		this.bulletElements = bulletElements;
	}

	private void move() {
	  List<BulletElement> bulletToDeletes = new ArrayList<>();
		for (BulletElement bulletElement : this.bulletElements)
		{
			if (bulletElement.getY() - Constants.STEP_DISTANCE >= Constants.WALL_SIZE) {
				bulletElement.setY(bulletElement.getY() - Constants.STEP_DISTANCE);
			} else {
			  bulletToDeletes.add(bulletElement);
			}
		}
		this.destroyElement(bulletToDeletes);
	}

	private void destroyElement(List<BulletElement> bulletElements) {
		this.bulletElements.removeAll(bulletElements);
	}
	
	public void destroy ()
	{
	  timer.stop();
	  bulletElements = new ArrayList<>();
	}

	public void draw(Graphics g) {
		for (BulletElement bulletElement : this.bulletElements)
		{
			bulletElement.draw(g, this.getParent());
		}
	}
	
	public void add()
	{
	  BulletElement bulletElement = new BulletElement(this.getX(), this.getY(), bulletSize, bulletSize);
	  bulletElements.add(bulletElement);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		move();
		this.getParent().repaint();
	}

}
