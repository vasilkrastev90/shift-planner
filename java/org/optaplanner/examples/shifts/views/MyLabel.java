package org.optaplanner.examples.shifts.views;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class MyLabel extends JLabel implements MouseListener {

	boolean b;
	
	public MyLabel(String s) {
		super(s,JLabel.CENTER);
		b=true;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	  if(b) {
		  this.setForeground(Color.RED);
		  b=false;
	  }
	  else {
		  this.setForeground(Color.BLACK);
		  b=true;
	  }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
