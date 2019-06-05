package main.entity;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.constants.Constants;
import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class BoatMenu extends Component implements ActionListener
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final int PROGRESS = 5;
  private Image readyImage;
  private Image gameOverImage;
  private Timer timer;
  private int progress;
  private boolean ready;
  
  public BoatMenu()
  {
    super();
    this.readyImage = ImageUtils.generateImageFromUrl(UrlConstants.READY_IMAGE_URL);
    timer = new Timer(Constants.READY_TIME_DELAY, this);
    ready = false;
    progress = PROGRESS;
  }
  
  public boolean isReady()
  {
    return ready;
  }

  public void setReady(boolean isReady)
  {
    this.ready = isReady;
  }

  public void active ()
  {
    timer.start();
  }

  public void gameOver(Graphics g, int score, WallComponent wallComponent, String urlImage)
  {
    this.gameOverImage = ImageUtils.generateImageFromUrl(urlImage);
    String playAgain = Constants.PLAY_AGAIN_MESSAGE;
    Font font = new Font("Helvetica", Font.BOLD, 120);
    Font font2 = new Font("Helvetica", Font.CENTER_BASELINE, 18);
    FontMetrics metr = getFontMetrics(font);
    FontMetrics metr2 = getFontMetrics(font2);
    g.setColor(Color.yellow);
    g.setFont(font);
    g.drawImage(this.gameOverImage, (Constants.BOARD_WITH - Constants.READY_LENGTH)/2, Constants.BOARD_HEIGHT / 2 -30, this.getParent());
    g.drawString(String.valueOf(score), (Constants.BOARD_WITH - metr.stringWidth(String.valueOf(score))) / 2,
            Constants.BOARD_HEIGHT / 2 - 110);
    g.setColor(Color.white);
    g.setFont(font2);
    g.drawString(playAgain, (Constants.BOARD_WITH - metr2.stringWidth(playAgain)) / 2,
            Constants.BOARD_HEIGHT - 60);
    wallComponent.draw(g);
    
  }
  
  public void drawStartGame (Graphics g, WallComponent wallComponent, BoatComponent boatComponent)
  {
    
    if (this.progress > 0)
    {
        g.drawImage(this.readyImage, (Constants.BOARD_WITH - Constants.READY_LENGTH)/2, Constants.BOARD_HEIGHT / 2 -30, this.getParent());
        wallComponent.draw(g);
        boatComponent.draw(g);
        progress --;
    }
    else {
      this.setReady(true);
      timer.stop();
      this.getParent().repaint();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.getParent().repaint();
  }
}
