package org.optaplanner.examples.shifts.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;


public class Listener implements ActionListener {

	JFrame f;
	Calendar cal;
	JComboBox<String> years;
	JComboBox<String> months;
	HashMap<String,Integer> y;
	HashMap<String,Integer> m;
	
	public Listener(JFrame f,JComboBox<String> years, HashMap<String,Integer> mapy, JComboBox<String> months, HashMap<String,Integer> mapm, Calendar cal){
		this.f=f;
		this.years = years;
		this.months = months;
		y=mapy;
		m=mapm;
		this.cal = cal;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		cal.set(y.get(years.getSelectedItem()), m.get(months.getSelectedItem()), 1);
		f.dispose();
	}
}
