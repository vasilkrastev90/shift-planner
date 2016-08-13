package org.optaplanner.examples.shifts.views;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;

public class EmployeeView extends JPanel {
	
	public EmployeeView(ArrayList<Employee> list,int year, int month) {
		
		CalendarPanel cp[] = new CalendarPanel[list.size()];
		setLayout(new GridLayout(list.size(), 2));
		for(int i = 0; i < list.size(); i++) {
			cp[i] = new CalendarPanel(year,month);
			add(new JLabel(list.get(i).getName(),JLabel.RIGHT));
			add(cp[i]);
		}
	}

}
