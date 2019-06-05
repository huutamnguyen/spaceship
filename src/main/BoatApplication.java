package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.frame.GameFrame;

public class BoatApplication {
	public static void main(String[] args) {
	    
	    EventQueue.invokeLater(() -> {
	        JFrame ex = new GameFrame();
	        ex.setVisible(true);
	    });
	}
}
