package main.entity;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import main.constants.Constants;
import main.entity.element.EnemyEnvironmentElement;

public class EnemyEnvironmentComponent extends Component implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int STEP_TO_ADD_ENEMY = 20;
	private List<EnemyEnvironmentElement> enemyEnvironmentElements;
	private int uniqueEnemyElementSize;
	private Timer timer;
	private BoatComponent boat;
	private int progress;
	public EnemyEnvironmentComponent(int uniqueEnemyElementSize) {
		super();
		this.uniqueEnemyElementSize = uniqueEnemyElementSize;
		this.initAttribute();
	}
	
	private void initAttribute ()
	{
		enemyEnvironmentElements = new ArrayList<>();
		timer = new Timer(Constants.ENEMY_ENVIRONMENT_DELAY, this);
		progress = 0;
	}
	
	/**
	 * @return the enemyEnvironmentElements
	 */
	public List<EnemyEnvironmentElement> getEnemyEnvironmentElements() {
		return enemyEnvironmentElements;
	}
	/**
	 * @param enemyEnvironmentElements the enemyEnvironmentElements to set
	 */
	public void setEnemyEnvironmentElements(List<EnemyEnvironmentElement> enemyEnvironmentElements) {
		this.enemyEnvironmentElements = enemyEnvironmentElements;
	}
	/**
	 * @return the uniqueEnemyElementSize
	 */
	public int getUniqueEnemyElementSize() {
		return uniqueEnemyElementSize;
	}
	/**
	 * @param uniqueEnemyElementSize the uniqueEnemyElementSize to set
	 */
	public void setUniqueEnemyElementSize(int uniqueEnemyElementSize) {
		this.uniqueEnemyElementSize = uniqueEnemyElementSize;
	}
	/**
	 * @return the boat
	 */
	public BoatComponent getBoat() {
		return boat;
	}
	/**
	 * @param boat the boat to set
	 */
	public void setBoat(BoatComponent boat) {
		this.boat = boat;
	}
	
	public void active ()
	{
		timer.start();
	}
	
	public void destroy ()
	{
		timer.stop();
	}
	
	private void move()
	{
		List<EnemyEnvironmentElement> elementToDeletes = new ArrayList<>();
		for (EnemyEnvironmentElement enemyEnvironmentElement : enemyEnvironmentElements)
		{
			if (enemyEnvironmentElement.getY() + uniqueEnemyElementSize < Constants.Y_END)
			{
				enemyEnvironmentElement.setY(enemyEnvironmentElement.getY() + uniqueEnemyElementSize);
			}
			else
			{
				elementToDeletes.add(enemyEnvironmentElement);
			}
		}
		if (!elementToDeletes.isEmpty())
		{
			enemyEnvironmentElements.removeAll(elementToDeletes);
		}
	}
	
	public void draw (Graphics g)
	{
		for (EnemyEnvironmentElement enemyEnvironmentElement : enemyEnvironmentElements)
		{
			enemyEnvironmentElement.drawBig(g, this.getParent());
		}
	}
	
	private void addEnemyEnvironment()
	{
		EnemyEnvironmentElement enemyEnvironmentElement = new EnemyEnvironmentElement(boat.getX(), Constants.Y_BEGIN,
				uniqueEnemyElementSize, uniqueEnemyElementSize);
		enemyEnvironmentElements.add(enemyEnvironmentElement);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		if (progress > 0)
	    {
			progress --;
	    }
	    else
	    {
	      this.addEnemyEnvironment();
	      progress = STEP_TO_ADD_ENEMY;
	    }
	}
	

}
