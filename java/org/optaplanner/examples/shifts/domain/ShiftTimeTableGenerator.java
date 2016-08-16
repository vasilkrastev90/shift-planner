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
import org.optaplanner.examples.shifts.views.Listener2;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShiftTimeTableGenerator extends LoggingMain {

	public static HashMap<Employee,Integer> numberOfShiftsPerEmployee0;
	public static HashMap<Employee,Integer> numberOfShiftsPerEmployee1;
	
	
	public ShiftTimetable createShiftTimeTable(int n, ArrayList<Employee> employees) {
		
		
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
