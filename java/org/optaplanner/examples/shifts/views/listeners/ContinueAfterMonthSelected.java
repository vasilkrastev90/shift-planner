package org.optaplanner.examples.shifts.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.optaplanner.examples.shifts.app.ShiftsTopLevel;
import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.views.EmployeeView;

public class ContinueAfterMonthSelected implements ActionListener {

	private JComboBox<String> listYears; 
	private HashMap<String, Integer> mapYears;
	private JComboBox<String> listMonths;
	private HashMap<String, Integer> mapMonths; 
	private HashMap<Integer, Integer> workDaysMap;
	private HashMap<Integer, Integer> workDaysMapReverse;
	private HashMap<Integer,Boolean> monthHolidays;
	private EmployeeView employeeView;
	private ArrayList<Employee> employeesList;
	private JFrame selectionFrame;
	
	public ContinueAfterMonthSelected(JFrame selectionFrame,JComboBox<String> listYears, HashMap<String, Integer> mapYears,
			JComboBox<String> listMonths, HashMap<String, Integer> mapMonths, HashMap<Integer, Integer> workDaysMap,
			HashMap<Integer, Integer> workDaysMapReverse, HashMap<Integer, Boolean> monthHolidays, ArrayList<Employee> employeesList, EmployeeView employeeView) {
		super();
		this.selectionFrame = selectionFrame;
		this.listYears = listYears;
		this.mapYears = mapYears;
		this.listMonths = listMonths;
		this.mapMonths = mapMonths;
		this.workDaysMap = workDaysMap;
		this.workDaysMapReverse = workDaysMapReverse;
		this.monthHolidays = monthHolidays;
		this.employeeView = employeeView;
		this.employeesList = employeesList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectionFrame.dispose();
		employeeView.updateEV(employeesList,mapYears.get(listYears.getSelectedItem()), mapMonths.get(listMonths.getSelectedItem()),monthHolidays,workDaysMap,workDaysMapReverse);
		ShiftsTopLevel.numberOfDays=workDaysMap.keySet().size();
			
		

	}

}
