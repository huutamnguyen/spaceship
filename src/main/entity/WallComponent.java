package main.entity;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.entity.element.WallElement;

public class WallComponent extends Component{
	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  private List<WallElement> wallElements;
  
  private int unitWallElementSize;

	public WallComponent(int unitWallElementSize)
  {
    super();
    this.unitWallElementSize = unitWallElementSize;
    this.initAttribute();
  }

  private void initAttribute ()
  {
    this.wallElements = new ArrayList<>();
  }
  
  public void initWall ()
  {
    for (int i = 0; i < this.getHeight() / this.unitWallElementSize; i++) {
      wallElements.add(new WallElement(this.getX(), i * this.unitWallElementSize + this.getY(), this.unitWallElementSize, this.unitWallElementSize));
      wallElements.add(new WallElement(this.getWidth() - this.unitWallElementSize + this.getX(), i * this.unitWallElementSize + this.getY(), this.unitWallElementSize, this.unitWallElementSize));
    }
    for (int j = 1; j < this.getWidth() / this.unitWallElementSize - 1; j++) {
      wallElements.add(new WallElement(j * this.unitWallElementSize + 1 + this.getX(), this.getY(), this.unitWallElementSize, this.unitWallElementSize));
      wallElements.add(new WallElement(j * this.unitWallElementSize + 1 + this.getX(), this.getHeight() - this.unitWallElementSize + this.getY(), this.unitWallElementSize, this.unitWallElementSize));
    }
  }

	public void draw(Graphics g) {
		for (WallElement wallElement : wallElements)
		{
		  wallElement.draw(g, this.getParent());
		}
	}

}
