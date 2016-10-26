package org.optaplanner.examples.shifts.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningSolution
public class ShiftTimetable extends AbstractPersistable implements Solution<HardSoftScore> {

	private int numberOfDays;
	private int totalNumberOfShifts;
	private HardSoftScore score;
	private List<Shift> listOfShifts;
	private List<Employee> employees;

	@ValueRangeProvider(id = "employeeRange")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@PlanningEntityCollectionProperty
	public List<Shift> getListOfShifts() {
		return listOfShifts;
	}

	public void setListOfShifts(List<Shift> listOfShifts) {
		this.listOfShifts = listOfShifts;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfShifts) {
		this.numberOfDays = numberOfShifts;
	}

	@Override
	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		facts.addAll(employees);
		return facts;

	}

	@Override
	public HardSoftScore getScore() {
		return score;
	}

	@Override
	public void setScore(HardSoftScore score) {
		this.score = score;

	}

	public void setTotalNumberOfShifts() {
		this.totalNumberOfShifts = this.numberOfDays * 2;
	}

	public int getTotalNumberOfShifts() {
		return totalNumberOfShifts;
	}

	public void calculateTotalNumberOfShiftsPerEmployee() {
		HashMap<Integer, ArrayList<Employee>> employeesPerCoefficients = new HashMap<Integer, ArrayList<Employee>>();
		int divisionResult = totalNumberOfShifts / employees.size();
		int remainder = totalNumberOfShifts % employees.size();
        ArrayList<Employee> employeesNotOnHoliday = new ArrayList(employees);

		for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.keySet()) {
			if (employee.getTotalAmountOfHoliday() >= 5) {
				double coefficient = (((double) employee.getTotalAmountOfHoliday() * 2) / this.totalNumberOfShifts)
						* divisionResult;
				Integer coefficientInt = Integer.valueOf((int) Math.floor(coefficient));
				System.out.println("the coefficient for " + employee.getName() + ":" + coefficient);
				if (employeesPerCoefficients.get(coefficientInt) == null) {
					employeesPerCoefficients.put(coefficientInt, new ArrayList<Employee>());
				}
				employeesPerCoefficients.get(coefficientInt).add(employee);
			}
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(employee, divisionResult);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(employee, divisionResult);
		}

		ArrayList<Integer> coefficients = new ArrayList<Integer>(employeesPerCoefficients.keySet());
        coefficients.sort(new IntegerComparator());
		for (Integer coefficient : coefficients) {
			ArrayList<Employee> employeesToBeFiltered = employeesPerCoefficients.get(coefficient);
			int finalCoefficient = 0;
			for (Employee employee : employeesToBeFiltered) {
				finalCoefficient = finalCoefficient + coefficient;
				employeesNotOnHoliday.remove(employee);
				ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(employee,ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee) - coefficient );
				ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(employee, ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee) - coefficient );
			}
			distributeRemainder(finalCoefficient, employeesNotOnHoliday);
		}

		distributeRemainder(remainder, employeesNotOnHoliday);

		printShiftDistribution();
	}

	private void printShiftDistribution() {
		for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.keySet()) {
			System.out.print("Employee entry:" + employee.getName() + ":"
					+ ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee) + "  ");
		}
		System.out.print("\n");

		for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.keySet()) {
			System.out.print("Employee entry:" + employee.getName() + ":"
					+ ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee) + "  ");
		}
		System.out.print("\n");
	}

	private void distributeRemainder(int remainder, List<Employee> employeesNotOnHoliday) {
		for (int i = remainder; i > 0; i--) {
			Employee minHoliday0 = findMin(employeesNotOnHoliday, ShiftTimeTableGenerator.numberOfShiftsPerEmployee0);
			Employee minHoliday1 = findMin(employeesNotOnHoliday, ShiftTimeTableGenerator.numberOfShiftsPerEmployee1);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(minHoliday0,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(minHoliday0) + 1);
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(minHoliday1,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(minHoliday1) + 1);
		
		}
	}

	private Employee findMin(List<Employee> employeesNotOnHoliday,
			HashMap<Employee, Integer> numberOfShiftsPerEmployee) {
		if (employeesNotOnHoliday == null) {
			return null;
		}
		Employee min = employeesNotOnHoliday.get(0);
		for (Employee employee : employeesNotOnHoliday) {
			if (numberOfShiftsPerEmployee.get(employee) < numberOfShiftsPerEmployee.get(min)) {
				min = employee;
			}
		}
		return min;
	}

}
