package main.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtils
{
  private ImageUtils()
  {
    super();
  }
  
  public static Image generateImageFromUrl (String url)
  {
    ImageIcon imageIcon = new ImageIcon(url);
    return imageIcon.getImage();
  }
}
