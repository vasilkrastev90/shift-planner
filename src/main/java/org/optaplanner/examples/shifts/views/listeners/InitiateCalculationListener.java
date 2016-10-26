package org.optaplanner.examples.shifts.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.optaplanner.examples.shifts.app.ShiftsTopLevel;
import org.optaplanner.examples.shifts.domain.Employee;

public class InitiateCalculationListener implements ActionListener {

	ArrayList<Employee> employees;
	HashMap<Integer,Integer> mapreverse;
	Calendar calendar;
	
	public InitiateCalculationListener(ArrayList<Employee> employees, int numberOfWorkingDays,HashMap<Integer,Integer> mapreverse, Calendar calendar) {
		this.employees = employees;
		this.mapreverse = mapreverse;
		this.calendar = calendar;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ShiftsTopLevel.initiateCalculation(employees,mapreverse,calendar);
		
	}

}
