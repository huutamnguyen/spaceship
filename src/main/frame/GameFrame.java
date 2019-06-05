package main.frame;

import javax.swing.JFrame;

import main.board.MainGame;
import main.constants.UrlConstants;
import main.utils.ImageUtils;

public class GameFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameFrame ()
	{
		this.add(new MainGame());
//		this.setUndecorated(true);
		this.setResizable(false);
	    this.pack();
	    this.setTitle("Tank");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setIconImage(ImageUtils.generateImageFromUrl(UrlConstants.BOAT_PART));
	    this.setAlwaysOnTop(true);
	}
}
