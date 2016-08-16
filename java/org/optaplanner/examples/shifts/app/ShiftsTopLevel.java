package org.optaplanner.examples.shifts.app;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.domain.Shift;
import org.optaplanner.examples.shifts.domain.ShiftTimeTableGenerator;
import org.optaplanner.examples.shifts.domain.ShiftTimetable;
import org.optaplanner.examples.shifts.views.EmployeeView;
import org.optaplanner.examples.shifts.views.Listener2;
import org.optaplanner.examples.shifts.views.Listener3;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShiftsTopLevel {

	public static int numberOfDays;
	
	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ObjectMapper om = new ObjectMapper();
		Calendar calendar = new GregorianCalendar();
		try {
			ClassLoader classLoader = new ShiftsTopLevel().getClass().getClassLoader();
			employees = om.readValue(
					new File(
							classLoader.getResource("org/optaplanner/examples/shifts/solver/employees.json").getFile()),
					new TypeReference<ArrayList<Employee>>() {
					});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// angel
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> mapreverse = new HashMap<Integer,Integer>();
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		EmployeeView ev = new EmployeeView(employees, 2016, 1,map, mapreverse,calendar);
		JScrollPane scrollPane = new JScrollPane(ev);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 500, 500);
		JPanel jp = new JPanel(null);
		jp.setPreferredSize(new Dimension(500,500));
		jp.add(scrollPane);
        JButton button1 = new JButton("Избери година/месец");
		Listener2 l2 = new Listener2(ev, employees,map,mapreverse);
		button1.addActionListener(l2);
		c.gridx=0;
		c.gridy=0;
		p.add(button1,c);
		
		JButton button2 = new JButton("Изчисли");
		button2.addActionListener(new Listener3(employees, map.keySet().size(),mapreverse,calendar));
		c.gridx=1;
		c.gridy=0;
		p.add(button2,c);
		c.gridx=0;
		c.gridy=1;
		c.gridheight = 4;
		c.gridwidth = 4;
		p.add(jp,c);
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

	public static void initiateCalculation(ArrayList<Employee> employees,HashMap<Integer,Integer> mapreverse, Calendar calendar) {
		// Build the Solver
		SolverFactory<ShiftTimetable> solverFactory = SolverFactory
				.createFromXmlResource("org/optaplanner/examples/shifts/solver/shiftsSolverConfig.xml");
		Solver<ShiftTimetable> solver = solverFactory.buildSolver();
		

		// LoggerContext loggerContext = (LoggerContext)
		// LoggerFactory.getILoggerFactory();
		// loggerContext.stop();

		// Load a problem with 8 queens
		ShiftTimetable unsolvedShifts = new ShiftTimeTableGenerator().createShiftTimeTable(numberOfDays, employees);

		// Solve the problem
		ShiftTimetable solvedShifts = solver.solve(unsolvedShifts);

		// Display the result
		toDisplayString(solvedShifts,mapreverse,calendar);
	}

	public static void toDisplayString(ShiftTimetable shifts,HashMap<Integer,Integer> mapreverse, Calendar calendar) {
			int numberOfShifts = shifts.getNumberOfDays();
		List<Shift> listOfShifts = shifts.getListOfShifts();

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(mapreverse.size() + 1, 5));
		p.add(new JLabel(""),JLabel.CENTER);
		p.add(new JLabel("Терминал Сутрин",JLabel.CENTER));
		p.add(new JLabel("Рецепция Сутрин",JLabel.CENTER));
		p.add(new JLabel("Терминал Следобед",JLabel.CENTER));
		p.add(new JLabel("Рецепция Следобед",JLabel.CENTER));
		JLabel l;
		
		for (int i = 0; i < numberOfShifts; i++) {
			Shift shift = listOfShifts.get(i);
			l = new JLabel("Работници за дата:" + mapreverse.get(i) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR),JLabel.CENTER);
			p.add(l);
			l = new JLabel(shift.getReceptionMorning0().getName(),JLabel.CENTER);
			p.add(l);
			l = new JLabel(shift.getReceptionMorning1().getName(),JLabel.CENTER);
			p.add(l);
			l = new JLabel(shift.getReceptionAfternoon0().getName(),JLabel.CENTER);
			p.add(l);
			l = new JLabel(shift.getReceptionAfternoon1().getName(),JLabel.CENTER);
			p.add(l);
			
			
		}
		f.add(p);
		f.setVisible(true);
		f.pack();
		for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.keySet()) {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(employee, 0);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(employee, 0);
		}
		for (Shift shift : listOfShifts) {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(shift.getReceptionMorning0(),
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shift.getReceptionMorning0()) + 1);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(shift.getReceptionMorning1(),
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shift.getReceptionMorning1()) + 1);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(shift.getReceptionAfternoon0(),
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shift.getReceptionAfternoon0()) + 1);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(shift.getReceptionAfternoon1(),
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shift.getReceptionAfternoon1()) + 1);
		}

        
	}
}
