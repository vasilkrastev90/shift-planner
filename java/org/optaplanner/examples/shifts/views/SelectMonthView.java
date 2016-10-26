package org.optaplanner.examples.shifts.views;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.views.listeners.SelectWorkingDaysListener;

public class SelectMonthView {

    	private HashMap<Integer, Boolean> monthHolidays;

		public SelectMonthView(HashMap<Integer,Integer> workDaysMap,HashMap<Integer,Integer> workDaysMapReverse,EmployeeView employeeView,ArrayList<Employee> employeesList, HashMap<Integer, Boolean> monthHolidays) {
    	this.monthHolidays = monthHolidays;
    	JFrame f = new JFrame();
		f.setSize(200, 200);
		
		JPanel p = new JPanel();
		
		HashMap<String,Integer> mapyears = new HashMap<String, Integer>();
		String years[] = {"2014","2015","2016","2017","2018"};
		JComboBox<String> listYears = new JComboBox<String>(years);
		int y=2014;
		for (String s : years) {
			mapyears.put(s, y);
			y++;
		}
		HashMap<String,Integer> mapmonths = new HashMap<String, Integer>();
		String months[] ={"January","February","March","April","May","June","July","August","September","October","November","December"};
		JComboBox<String> listMonths = new JComboBox<String>(months);
		int m=0;
		for (String s : months) {
			mapmonths.put(s, m);
			m++;
		}
		//Listener l = new Listener(f,listYears,mapyears,listMonths,mapmonths,ev,list,workDaysMap,workDaysMapReverse);
		SelectWorkingDaysListener selector = new SelectWorkingDaysListener(f,listYears,mapyears,listMonths,mapmonths,workDaysMap,workDaysMapReverse,monthHolidays,employeeView,employeesList);
		JButton button = new JButton("Set");
		button.addActionListener(selector);
		
		p.add(listYears);
		p.add(listMonths);
		p.add(button);
		f.add(p);
		f.setVisible(true);
		f.pack();
	}

}
