package org.optaplanner.examples.shifts.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.optaplanner.examples.shifts.domain.Employee;

public class Listener2 implements ActionListener{

	HashMap<Integer,Integer> map;
	HashMap<Integer,Integer> mapreverse;
	EmployeeView ev;
	ArrayList<Employee> list;
	public Listener2(EmployeeView ev,ArrayList<Employee> list,HashMap<Integer,Integer> map,HashMap<Integer,Integer> mapreverse) {
		this.ev = ev;
		this.list = list;
		this.map = map;
		this.mapreverse = mapreverse;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame f = new JFrame();
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		Listener l = new Listener(f,listYears,mapyears,listMonths,mapmonths,ev,list,map,mapreverse);
		JButton button = new JButton("Set");
		button.addActionListener(l);
		
		p.add(listYears);
		p.add(listMonths);
		p.add(button);
		f.add(p);
		f.setVisible(true);
		
	}

}
