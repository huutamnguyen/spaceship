package main.entity;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Timer;

import main.constants.Constants;
import main.entity.element.BoatElement;
import main.entity.element.EnemyEnvironmentElement;
import main.entity.element.EnenyElement;
import main.enums.DirectionEnum;
import main.utils.GameKeyEventListener;

public class BoatComponent extends Component implements ActionListener
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final int ADD_BULLET_TIME = 2;
  private List<BoatElement> boatElements;
  private int uniqueBoatElementSize;
  private Timer timer;
  private GameKeyEventListener keyEventListener;
  private BulletComponent bulletComponent;
  private int time = ADD_BULLET_TIME;
  private boolean gameOverStatus;

  public BoatComponent(int uniqueBoatElementSize)
  {
    super();
    this.uniqueBoatElementSize = uniqueBoatElementSize;
    this.initAttribute();
  }

  private void initAttribute()
  {
    boatElements = new ArrayList<>();
    bulletComponent = new BulletComponent(Constants.STEP_DISTANCE);
    this.timer = new Timer(Constants.BOAT_TIME_DELAY, this);
  }

  private void addBulletComponent()
  {
    bulletComponent.setBounds(this.getX(), this.getY(), uniqueBoatElementSize,
        uniqueBoatElementSize);
    this.getParent().add(bulletComponent);
    bulletComponent.active();
  }

  public void setKeyEventListener(GameKeyEventListener keyEventListener)
  {
    this.keyEventListener = keyEventListener;
  }

  public List<BoatElement> getBoatElements()
  {
    return boatElements;
  }

  public void setBoatElements(List<BoatElement> boatElements)
  {
    this.boatElements = boatElements;
  }

  public BulletComponent getBulletComponent()
  {
    return bulletComponent;
  }

  public void setBulletComponent(BulletComponent bulletComponent)
  {
    this.bulletComponent = bulletComponent;
  }

  public void initBoat()
  {
    boatElements.add(new BoatElement(this.getX(), this.getY(), this.uniqueBoatElementSize,
        this.uniqueBoatElementSize));
    boatElements.add(new BoatElement(this.getX() - this.uniqueBoatElementSize, this.getY(),
        this.uniqueBoatElementSize, this.uniqueBoatElementSize));
    boatElements.add(new BoatElement(this.getX() + this.uniqueBoatElementSize, this.getY(),
        this.uniqueBoatElementSize, this.uniqueBoatElementSize));
    boatElements.add(new BoatElement(this.getX(), this.getY() - this.uniqueBoatElementSize,
        this.uniqueBoatElementSize, this.uniqueBoatElementSize));
  }

  private void updateBoats()
  {
    int distance = this.getX() - boatElements.get(0).getX();
    for (BoatElement boatElement : boatElements)
    {
      boatElement.setX(boatElement.getX() + distance);
    }
  }

  public void active()
  {
    gameOverStatus = false;
    addBulletComponent();
    this.setFocusable(true);
    this.timer.start();
  }

  public void draw(Graphics g)
  {
    for (BoatElement boatElement : boatElements)
    {
      boatElement.draw(g, this.getParent());
    }
    bulletComponent.draw(g);
  }

  private void updateBulletLocation()
  {
    bulletComponent.setLocation(this.getLocation());
  }

  public void move()
  {
    if (Objects.equals(this.keyEventListener.getDirection(), DirectionEnum.LEFT)
        && this.getX() - uniqueBoatElementSize >= Constants.X_BEGIN)
    {
      this.setLocation(this.getX() - uniqueBoatElementSize, this.getY());
    }
    else if (Objects.equals(this.keyEventListener.getDirection(), DirectionEnum.RIGHT)
        && this.getX() + 2 * uniqueBoatElementSize <= Constants.X_END)
    {
      this.setLocation(this.getX() + uniqueBoatElementSize, this.getY());
    }
    updateBulletLocation();
  }

  private void addBullet()
  {
    bulletComponent.add();
  }

  public void destroy()
  {
    timer.stop();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    this.move();
    this.updateBoats();
    if (time > 0)
    {
      time--;
    }
    else
    {
      this.addBullet();
      time = ADD_BULLET_TIME;
    }

    this.getParent().repaint();
  }

  public void checkCollision(EnemyComponent enemyComponent)
  {
    for (BoatElement boatElement : boatElements)
    {
      for (EnenyElement enenyElement : enemyComponent.getEnenyElements())
      {
        if (enenyElement.getBounds().intersects(boatElement.getBounds()))
        {
          enemyComponent.getEnenyElements().remove(enenyElement);
          bulletComponent.destroy();
          this.destroy();
          this.gameOverStatus = true;
          this.getParent().repaint();
          break;
        }
      }
    }
  }
  
  public void checkCollisionFireBall(EnemyEnvironmentComponent fireBall)
  {
    for (BoatElement boatElement : boatElements)
    {
      for (EnemyEnvironmentElement enenyElement : fireBall.getEnemyEnvironmentElements())
      {
        if (enenyElement.getBounds().intersects(boatElement.getBounds()))
        {
          bulletComponent.destroy();
          this.destroy();
          this.gameOverStatus = true;
          this.getParent().repaint();
          break;
        }
      }
    }
  }

  public boolean isGameOverStatus()
  {
    return gameOverStatus;
  }

  public void setGameOverStatus(boolean gameOverStatus)
  {
    this.gameOverStatus = gameOverStatus;
  }

}
