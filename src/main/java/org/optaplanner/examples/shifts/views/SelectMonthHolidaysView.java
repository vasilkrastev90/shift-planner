package org.optaplanner.examples.shifts.views;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.views.listeners.ContinueAfterMonthSelected;

public class SelectMonthHolidaysView {

	public SelectMonthHolidaysView(JComboBox<String> listYears, HashMap<String, Integer> mapYears,
			JComboBox<String> listMonths, HashMap<String, Integer> mapMonths, HashMap<Integer, Integer> workDaysMap,
			HashMap<Integer, Integer> workDaysMapReverse,HashMap<Integer,Boolean> monthHolidays, ArrayList<Employee> employeesList, EmployeeView employeeView) {

		JFrame selectionFrame = new JFrame();
		selectionFrame.setSize(200, 200);
		CalendarPanel calendarPanel = new CalendarPanel(mapYears.get(listYears.getSelectedItem()),
				mapMonths.get(listMonths.getSelectedItem()), workDaysMap,monthHolidays);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton cont = new JButton("Continue");
		cont.addActionListener(new ContinueAfterMonthSelected(selectionFrame,listYears,mapYears,listMonths,mapMonths,workDaysMap,workDaysMapReverse,monthHolidays,employeesList,employeeView));
		JLabel info = new JLabel("Избери почивни дни за месеца:");
		buttonPanel.add(info);
		buttonPanel.add(cont);
		selectionFrame.setLayout(new GridLayout(2, 1));
		selectionFrame.add(calendarPanel);
		selectionFrame.add(buttonPanel);
		selectionFrame.pack();
		selectionFrame.setVisible(true);
	}

}
