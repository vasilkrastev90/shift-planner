package org.optaplanner.examples.shifts.views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JLabel;

import org.optaplanner.examples.shifts.domain.Employee;

public class MyLabel extends JLabel implements MouseListener{

	boolean b;
	int day;
	Employee emp;
	HashMap<Integer,Integer> map;
	
	public MyLabel(int day, Employee e, HashMap<Integer,Integer> map) {
		super("" + day,JLabel.CENTER);
		b=true;
		addMouseListener(this);
		this.day = day;
		emp = e;
		this.map = map;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	  if(b) {
		  this.setForeground(Color.RED);
		  emp.setUnavailable(map.get(day), true);
		  b=false;
	  }
	  else {
		  this.setForeground(Color.BLACK);
		  emp.setUnavailable(map.get(day),false);
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
