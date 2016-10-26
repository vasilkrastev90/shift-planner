package org.optaplanner.examples.shifts.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;

public class CalendarPanel extends JPanel
{
	
	
  public CalendarPanel(int year, int month, Employee e,HashMap<Integer,Integer> map,HashMap<Integer,Boolean> monthHolidays)
  {
	  int day = 1;
	  GregorianCalendar gc = new GregorianCalendar(year,month,day);
	  String[] headers = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	  int weeks = gc.getActualMaximum(Calendar.WEEK_OF_MONTH);
	  setLayout(new GridLayout(weeks + 2, 7));
	  setBorder(BorderFactory.createEtchedBorder());
	  for(int i = 0; i < 7; i++) add(new JLabel(headers[i],JLabel.CENTER));
	  int dayofweek = gc.get(Calendar.DAY_OF_WEEK);
	  
	  for(int i = 1; i != dayofweek; i++) {
		  add(new JLabel("",JLabel.CENTER));
	  }
	  
	  while(day <= gc.getActualMaximum(Calendar.DAY_OF_MONTH) && gc.get(Calendar.MONTH)==month) {
		  
		  if(!monthHolidays.get(day)){
			  add(new MyLabel(day,e,map));
		  }
		  else{
			  JLabel l = new JLabel("" + day,JLabel.CENTER);
			  l.setForeground(Color.RED);
			  add(l);
		  }
		
		  day++;
		  gc.set(year, month, day);
	  }
	  gc.set(year,month,day-1);
	  for(int i = gc.get(Calendar.DAY_OF_WEEK); i < 7; i++)  {
		  add(new JLabel("",JLabel.CENTER));
		  }
  	}
  
  public CalendarPanel(int year, int month,HashMap<Integer,Integer> map,HashMap<Integer,Boolean> monthsHoliday)
  {
	  monthsHoliday.clear();
	  int day = 1;
	  GregorianCalendar gc = new GregorianCalendar(year,month,day);
	  String[] headers = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	  int weeks = gc.getActualMaximum(Calendar.WEEK_OF_MONTH);
	  setLayout(new GridLayout(weeks + 2, 7));
	  setBorder(BorderFactory.createEtchedBorder());
	  for(int i = 0; i < 7; i++) add(new JLabel(headers[i],JLabel.CENTER));
	  int dayofweek = gc.get(Calendar.DAY_OF_WEEK);
	  
	  for(int i = 1; i != dayofweek; i++) {
		  add(new JLabel("",JLabel.CENTER));
	  }
	  
	  while(day <= gc.getActualMaximum(Calendar.DAY_OF_MONTH) && gc.get(Calendar.MONTH)==month) {
		  
		  InitialDateSelectionLabel l = new InitialDateSelectionLabel(day,map,monthsHoliday);
		  if((gc.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY || 
					gc.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY)){
			    monthsHoliday.put(day,true);
			    l.setSelected(true);
			    l.setForeground(Color.RED);
			  
		  }
		  add(l);
		  day++;
		  gc.set(year, month, day);
	  }
	  gc.set(year,month,day-1);
	  for(int i = gc.get(Calendar.DAY_OF_WEEK); i < 7; i++)  {
		  add(new JLabel("",JLabel.CENTER));
		  }
  	}
  
}