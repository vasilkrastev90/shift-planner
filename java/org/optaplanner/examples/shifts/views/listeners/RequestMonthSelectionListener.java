package org.optaplanner.examples.shifts.views.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.views.EmployeeView;
import org.optaplanner.examples.shifts.views.SelectMonthView;

public class RequestMonthSelectionListener implements ActionListener{

	private HashMap<Integer,Integer> workDaysMap;
	private HashMap<Integer,Integer> workDaysMapReverse;
	private EmployeeView ev;
	private ArrayList<Employee> list;
	private HashMap<Integer,Boolean> monthHolidays;
	public RequestMonthSelectionListener(EmployeeView ev,ArrayList<Employee> list,HashMap<Integer,Integer> map,HashMap<Integer,Integer> mapreverse) {
		this.ev = ev;
		this.list = list;
		this.workDaysMap = map;
		this.workDaysMapReverse = mapreverse;
		this.monthHolidays = new HashMap<Integer,Boolean>();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new SelectMonthView(workDaysMap,workDaysMapReverse,ev,list,monthHolidays);
		
	}

}
