package org.optaplanner.examples.shifts.views;

import java.awt.GridLayout;
import java.util.*;


import javax.swing.*;

public class test {
	
	public static void main(String[] args) {
		
		
		int year, month;
		year = 2016;
		month = 8;
		
		Calendar calendar = new GregorianCalendar();
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		HashMap<Integer,Integer> workdays = new HashMap<Integer,Integer>();
		int workday = 1;
		
		for(int i = 1; i <= daysInMonth; i++) {
			
			calendar.set(year, month - 1, i);	
			if(!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				
				workdays.put(Integer.valueOf(i), Integer.valueOf(workday));
				workday++;
			}
		}
		
		JFrame f = new JFrame();
		f.setSize(700, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CalendarPanel cp = new CalendarPanel(2016, 7);
		f.add(cp);
		
		/*JPanel p = new JPanel();
		
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
		Listener l = new Listener(f,listYears,mapyears,listMonths,mapmonths,calendar);
		JButton button = new JButton("Set");
		button.addActionListener(l);
		
		p.add(listYears);
		p.add(listMonths);
		p.add(button);
		f.add(p);*/
		f.setVisible(true);
		
		
	}
}
