package org.optaplanner.examples.shifts.domain;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.optaplanner.examples.common.app.LoggingMain;
import org.optaplanner.examples.common.persistence.AbstractSolutionImporter;
import org.optaplanner.examples.shifts.views.EmployeeView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShiftTimeTableGenerator extends LoggingMain {

	public static HashMap<Employee,Integer> numberOfShiftsPerEmployee0;
	public static HashMap<Employee,Integer> numberOfShiftsPerEmployee1;
	
	
	public ShiftTimetable createShiftTimeTable(int n) {
		Employee cvk = new Employee("ЦВ.К",n);
		Employee np = new Employee("Н.П.", n);
		Employee dk = new Employee("Д.К.", n);
		Employee hrm = new Employee("Хр.М.", n);
		Employee bm = new Employee("Б.М.",n);
		Employee ma = new Employee("М.А.",n);
		Employee md = new Employee("М.Д.",n);
		Employee vv = new Employee("В.В",n);
		Employee kd = new Employee("К.Д.",n);
		
		for (int i =0; i < n; i++) {
			cvk.setUnavailable(i, true);
		}
		
		for (int i = 0; i < 5; i++) {
			bm.setUnavailable(i, true);
		}
		
		for (int i = 5; i<10;i++){
			hrm.setUnavailable(i, true);
		}
		
		for (int i = 15; i < 20; i++) {
			md.setUnavailable(i, true);
		}
		
		for (int i = 20; i<23; i++) {
			dk.setUnavailable(i, true);
		}
		
		np.setUnavailable(9, true);
		kd.setUnavailable(9, true);
		np.setUnavailable(10, true);
		bm.setUnavailable(10, true);
		ma.setUnavailable(10,true);
		dk.setUnavailable(11, true);
		vv.setUnavailable(11, true);
		kd.setUnavailable(12, true);
		dk.setUnavailable(12, true);
		bm.setUnavailable(13, true);
		np.setUnavailable(13, true);
		
		ArrayList<Employee> employees = new ArrayList<Employee>(Arrays.asList(cvk, hrm,dk,np,bm,ma,md,vv,kd));
		ObjectMapper om = new ObjectMapper();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			employees = om.readValue(new File(classLoader.getResource("org/optaplanner/examples/shifts/solver/employees.json").getFile()), new TypeReference<ArrayList<Employee>>() {});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//angel
		JFrame f = new JFrame();
		f.setSize(700, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		JButton button1 = new JButton("Избери година/месец");
		p.add(button1);
		JButton button2 = new JButton("Изчисли");
		p.add(button2);
		EmployeeView ev = new EmployeeView(employees,2016,7);
		p.add(ev);
		f.add(p);
		f.setVisible(true);
		//angel
		//employees.sort(new EmployeeStrengthComparator2());
		this.numberOfShiftsPerEmployee0 = new HashMap<Employee,Integer>();
		for (Employee employee : employees) {
			this.numberOfShiftsPerEmployee0.put(employee,new Integer(0));
		}
		this.numberOfShiftsPerEmployee1 = new HashMap<Employee, Integer>(this.numberOfShiftsPerEmployee0);
		
		ShiftTimetable timetable = new ShiftTimetable();
		timetable.setId(0L);
		timetable.setNumberOfDays(n);
		timetable.setTotalNumberOfShifts();
		timetable.setListOfShifts(createListShifts(timetable,numberOfShiftsPerEmployee0,numberOfShiftsPerEmployee1));
		timetable.setEmployees(employees);
		timetable.calculateTotalNumberOfShiftsPerEmployee();
		BigInteger possibleSolutionSize = BigInteger.valueOf(timetable.getEmployees().size())
				.pow(timetable.getNumberOfDays());
		logger.info("Timetable {} has {} queens with a search space of {}.", n, timetable.getNumberOfDays(),
				AbstractSolutionImporter.getFlooredPossibleSolutionSize(possibleSolutionSize));
		return timetable;
	}

	private List<Shift> createListShifts(ShiftTimetable timetable, HashMap<Employee, Integer> numberOfShiftsPerEmployee0, HashMap<Employee, Integer> numberOfShiftsPerEmployee1) {
		List<Shift> initialListOfShifts = new ArrayList<Shift>(timetable.getNumberOfDays());
		for (int i = 0; i < timetable.getNumberOfDays(); i++) {
			Shift shift = new Shift();
			initialListOfShifts.add(shift);
		}
		return initialListOfShifts;
	}

}
