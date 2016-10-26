package org.optaplanner.examples.shifts.domain;

import java.util.Comparator;

public class EmployeeStrengthComparator0 implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee0
				.get(o1) > ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(o2)) {
              return 1;
		} else if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee0
				.get(o1) < ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(o2)) {
			return -1;
		} else {
		return 0;
		}
	}

}
