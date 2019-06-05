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
import main.constants.UrlConstants;
import main.entity.element.BulletElement;
import main.entity.element.EnenyElement;
import main.utils.FileUtil;

public class EnemyComponent extends Component implements ActionListener{
  private static final long serialVersionUID = 1L;
  
  private static final int TIME_STEP_DOWN = 4;
  private List<EnenyElement> enenyElements;
  private int uniqueEnemyElementSize;
  private Timer timer;
  private boolean leftDirection;
  private int distanceElement;
  private int distanceElementDown;
  private int componentSize;
  private int timeToDown = TIME_STEP_DOWN;
  private List<String> enemyInput;
  private int enemyOrder;
  private boolean winStatus;
  private int score;
  
  public EnemyComponent(int uniqueEnemyElementSize)
  {
    super();
    this.uniqueEnemyElementSize = uniqueEnemyElementSize;
    initAttribute();
  }
  
  private void generateEnemyInput()
  {
    enemyInput.add(UrlConstants.ENEMY_MATRIX_1);
    enemyInput.add(UrlConstants.ENEMY_MATRIX_2);
    enemyInput.add(UrlConstants.ENEMY_MATRIX_3);
    enemyInput.add(UrlConstants.ENEMY_MATRIX_4);
    enemyInput.add(UrlConstants.ENEMY_MATRIX_5);
  }
  
  private void initAttribute ()
  {
    score = 0;
    timer = new Timer(Constants.ENEMY_TIME_DELAY, this);
    enenyElements = new ArrayList<>();
    leftDirection = false;
    enemyInput = new ArrayList<>();
    enemyOrder = 0;
    generateEnemyInput();
  }
  
  public List<EnenyElement> getEnenyElements()
  {
    return enenyElements;
  }

  public void setEnenyElements(List<EnenyElement> enenyElements)
  {
    this.enenyElements = enenyElements;
  }

  private void populateEnemyElements(String enemyFileUrl)
  {
    List<List<Integer>> enemyMatrix = FileUtil.getObjectMatrix(enemyFileUrl);
    for (int i = 0; i < enemyMatrix.size(); i++)
    {
      List<Integer> matrixLine = enemyMatrix.get(i);
      for (int j = 0; j < matrixLine.size(); j ++)
      {
        if (Objects.equals(matrixLine.get(j), 1))
        {
          EnenyElement enenyElement = new EnenyElement(this.getX() + j * uniqueEnemyElementSize,
              this.getY() + i * uniqueEnemyElementSize, uniqueEnemyElementSize,
              uniqueEnemyElementSize);
          enenyElements.add(enenyElement);
        }
      }
    }
    distanceElement = this.enenyElements.get(0).getX() - this.getX();
    distanceElementDown = this.enenyElements.get(0).getY() - this.getY();
    componentSize = enemyMatrix.get(0).size();
  }
  
  private void updateEnemyElementsLocation()
  {
    int distance = this.getX() + this.distanceElement - this.enenyElements.get(0).getX();
    for (EnenyElement enenyElement : enenyElements)
    {
      enenyElement.setX(enenyElement.getX() + distance);
    }
  }
  
  private void updateEnemyElementsLocationDown()
  {
    int distance = this.getY() + this.distanceElementDown - this.enenyElements.get(0).getY();
    for (EnenyElement enenyElement : enenyElements)
    {
      enenyElement.setY(enenyElement.getY() + distance);
    }
  }
  
  private void moveDown()
  {
    this.setLocation(this.getX(), this.getY() + uniqueEnemyElementSize);
    updateEnemyElementsLocationDown();
  }
  
  private void move()
  {
    if (leftDirection)
    {
      this.setLocation(this.getX() - uniqueEnemyElementSize, this.getY());
    }
    else
    {
      this.setLocation(this.getX() + uniqueEnemyElementSize, this.getY());
    }
    
    updateEnemyElementsLocation();
    
    if (this.getX() <= Constants.X_BEGIN)
    {
      this.setLeftDirection(false);
    }
    else if (this.getX() >= Constants.X_END - componentSize * uniqueEnemyElementSize)
    {
      this.setLeftDirection(true);
    }

  }
  
  public void draw (Graphics g)
  {
    for (EnenyElement enenyElement : enenyElements)
    {
      enenyElement.draw(g, this.getParent());
    }
  }
  
  public void active()
  {
    winStatus = false;
    populateEnemyElements(enemyInput.get(enemyOrder));
    timer.start();
  }
  
  private void checkDestroy()
  {
    List<EnenyElement> enemyToDeletes = new ArrayList<>();
    for (EnenyElement enenyElement : enenyElements)
    {
      if (enenyElement.getY() >= Constants.Y_END)
      {
        enemyToDeletes.add(enenyElement);
      }
    }
    enenyElements.removeAll(enemyToDeletes);
    if (enenyElements.isEmpty())
    {
      if (enemyOrder < enemyInput.size() -1)
      {
        enemyOrder ++;
        this.setLocation(Constants.X_BEGIN, Constants.Y_BEGIN - Constants.STEP_DISTANCE);
        populateEnemyElements(enemyInput.get(enemyOrder));
      }
      else
      {
        timer.stop();
        this.winStatus = true;
        this.getParent().repaint();
      }
    }
  }
  
  public void setDestroy()
  {
    timer.stop();
  }
  
  public void checkCollision (BulletComponent bulletComponent)
  {
    List<EnenyElement> enemyToDeletes = new ArrayList<>();
    List<BulletElement> bulletToDeletes = new ArrayList<>();
    for (BulletElement bulletElement : bulletComponent.getBulletElements())
    {
      for (EnenyElement enenyElement : enenyElements)
      {
        if (enenyElement.getBounds().intersects(bulletElement.getBounds()))
        {
          enemyToDeletes.add(enenyElement);
          bulletToDeletes.add(bulletElement);
        }
      }
    }
    score += enemyToDeletes.size();
    enenyElements.removeAll(enemyToDeletes);
    bulletComponent.getBulletElements().removeAll(bulletToDeletes);
    if (enenyElements.isEmpty())
    {
      if (enemyOrder < enemyInput.size() -1)
      {
        enemyOrder ++;
        this.setLocation(Constants.X_BEGIN, Constants.Y_BEGIN);
        populateEnemyElements(enemyInput.get(enemyOrder));
      }
      else
      {
        timer.stop();
        this.winStatus = true;
        this.getParent().repaint();
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    move();
    if (timeToDown > 0)
    {
      timeToDown --;
    }
    else
    {
      this.moveDown();
      timeToDown = TIME_STEP_DOWN;
    }
    checkDestroy();
    this.getParent().repaint();
  }

  public boolean isLeftDirection()
  {
    return leftDirection;
  }

  public void setLeftDirection(boolean leftDirection)
  {
    this.leftDirection = leftDirection;
  }

  public boolean isWinStatus()
  {
    return winStatus;
  }

  public void setWinStatus(boolean winStatus)
  {
    this.winStatus = winStatus;
  }

  public int getScore()
  {
    return score;
  }

  public void setScore(int score)
  {
    this.score = score;
  }
  
}
