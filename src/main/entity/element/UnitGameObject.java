package main.entity.element;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class UnitGameObject {
	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;

	public UnitGameObject(Image image, int x, int y, int width, int height)
  {
    super();
    this.image = image;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

	public Image getImage()
  {
    return image;
  }

  public void setImage(Image image)
  {
    this.image = image;
  }

  public int getX()
  {
    return x;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public int getY()
  {
    return y;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public int getWidth()
  {
    return width;
  }

  public void setWidth(int width)
  {
    this.width = width;
  }

  public int getHeight()
  {
    return height;
  }

  public void setHeight(int height)
  {
    this.height = height;
  }

  /*
	 * Draw with default size
	 */
	public void draw (Graphics g, ImageObserver container)
	{
		g.drawImage(this.getImage(), this.x, this.y, this.width, this.height, container);
	}
	
	/*
	 * Draw with default size
	 */
	public void drawBig (Graphics g, ImageObserver container)
	{
		g.drawImage(this.getImage(), this.x - 15, this.y - 15, this.width + 30, this.height +30, container);
	}
	
	public Rectangle getBounds() {
    return new Rectangle(x, y, width, height);
  }
	
}
