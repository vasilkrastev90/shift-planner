package org.optaplanner.examples.shifts.views;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import org.optaplanner.examples.shifts.domain.Employee;

public class EmployeeView extends JPanel {
	
	CalendarPanel cp[];
	Calendar calendar;
	
	public EmployeeView(ArrayList<Employee> employees,int year, int month, HashMap<Integer,Integer> workdays,HashMap<Integer,Integer> mapreverse, Calendar calendar) {
	super();
	cp = new CalendarPanel[employees.size()];
	this.calendar = calendar;
	}

	public void updateEV(ArrayList<Employee> employees, int year, int month, HashMap<Integer, Boolean> monthHolidays, HashMap<Integer, Integer> workdays,
			HashMap<Integer, Integer> mapreverse) {
		workdays.clear();
		mapreverse.clear();
		removeAll();
		calendar.set(year, month, 1);
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		int workday = 0;

		for (int i = 1; i <= daysInMonth; i++) {

			calendar.set(year, month, i);
			if (!monthHolidays.get(i)) {

				workdays.put(Integer.valueOf(i), Integer.valueOf(workday));
				mapreverse.put(workday, i);
				workday++;
			}
		}
		
		add(new JLabel("",JLabel.CENTER));
		add(new JLabel("Дата:" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR),JLabel.CENTER));
		setLayout(new GridLayout(employees.size()+1, 2));
		for (int i = 0; i < employees.size(); i++) {
			cp[i] = new CalendarPanel(year, month, employees.get(i), workdays,monthHolidays);
			add(new JLabel(employees.get(i).getName(), JLabel.RIGHT));
			add(cp[i]);
		}
		
		for (Employee employee : employees) {
			employee.setUnavailable(new boolean[workdays.keySet().size()]);
		}
		revalidate();
		repaint();
	}

}
