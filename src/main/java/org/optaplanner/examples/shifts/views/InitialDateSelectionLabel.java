package org.optaplanner.examples.shifts.views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JLabel;

import org.optaplanner.examples.shifts.domain.Employee;

public class InitialDateSelectionLabel extends JLabel implements MouseListener {

	boolean isSelected;
	int day;
	HashMap<Integer, Integer> workdaysMap;
	HashMap<Integer,Boolean> monthsHoliday;

	public InitialDateSelectionLabel(int day, HashMap<Integer, Integer> map, HashMap<Integer, Boolean> monthsHoliday) {
		super("" + day, JLabel.CENTER);
		isSelected = false;
		addMouseListener(this);
		this.monthsHoliday = monthsHoliday;
		monthsHoliday.put(day, false);
		this.day = day;
		this.workdaysMap = map;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isSelected) {
			monthsHoliday.put(day, true);
			this.setForeground(Color.RED);
			isSelected = true;
		} else {
			monthsHoliday.put(day,false);
			this.setForeground(Color.BLACK);
			isSelected = false;
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
