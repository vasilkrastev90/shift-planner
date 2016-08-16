package org.optaplanner.examples.shifts.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.optaplanner.examples.shifts.app.ShiftsTopLevel;
import org.optaplanner.examples.shifts.domain.Employee;


public class Listener implements ActionListener {

	JFrame f;
	EmployeeView ev;
	ArrayList<Employee> list;
	JComboBox<String> years;
	JComboBox<String> months;
	HashMap<String,Integer> y;
	HashMap<String,Integer> m;
	HashMap<Integer,Integer> map;
	HashMap<Integer,Integer> mapreverse;
	public Listener(JFrame f,JComboBox<String> years, HashMap<String,Integer> mapy, 
								JComboBox<String> months, HashMap<String,Integer> mapm,
								EmployeeView ev, ArrayList<Employee> list,
								HashMap<Integer,Integer> map,HashMap<Integer,Integer> mapreverse){
		this.f=f;
		this.years = years;
		this.months = months;
		y=mapy;
		m=mapm;
		this.ev = ev;
		this.list = list;
		this.map = map;
		this.mapreverse = mapreverse;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ev.updateEV(list,y.get(years.getSelectedItem()), m.get(months.getSelectedItem()),map,mapreverse);
		ShiftsTopLevel.numberOfDays=map.keySet().size();
		f.dispose();
	}
}
