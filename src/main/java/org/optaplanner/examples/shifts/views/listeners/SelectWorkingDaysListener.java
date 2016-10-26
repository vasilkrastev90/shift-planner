package org.optaplanner.examples.shifts.views.listeners;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.views.CalendarPanel;
import org.optaplanner.examples.shifts.views.EmployeeView;
import org.optaplanner.examples.shifts.views.SelectMonthHolidaysView;

public class SelectWorkingDaysListener implements ActionListener {

	private JFrame f;
	private JComboBox<String> listYears;
	private HashMap<String, Integer> mapYears;
	private JComboBox<String> listMonths;
	private HashMap<String, Integer> mapMonths;
	private HashMap<Integer, Integer> workDaysMap;
	private HashMap<Integer, Integer> workDaysMapReverse;
	private HashMap<Integer,Boolean> monthHolidays;
	private ArrayList<Employee> employeesList;
	private EmployeeView employeeView;
	
	public SelectWorkingDaysListener(JFrame f, JComboBox<String> listYears, HashMap<String, Integer> mapyears, JComboBox<String> listMonths, HashMap<String, Integer> mapmonths, HashMap<Integer, Integer> workDaysMap, HashMap<Integer, Integer> workDaysMapReverse, HashMap<Integer,Boolean> monthHolidays, EmployeeView employeeView, ArrayList<Employee> employeesList) {
	 
		this.f = f;
		this.listYears = listYears;
		this.mapYears = mapyears;
		this.listMonths = listMonths;
		this.mapMonths = mapmonths;
		this.monthHolidays=monthHolidays;
		this.workDaysMap = workDaysMap;
		this.workDaysMapReverse = workDaysMapReverse;
		this.employeesList = employeesList;
		this.employeeView = employeeView;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
	 
		f.dispose();
	    new SelectMonthHolidaysView(listYears, mapYears, listMonths, mapMonths, workDaysMap, workDaysMapReverse, monthHolidays, employeesList,employeeView);

	}

}
