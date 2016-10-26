package org.optaplanner.examples.shifts.domain;

import java.util.Comparator;

public class EmployeeStrengthComparator1 implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee1
				.get(o1) > ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(o2)) {
              return 1;
		} else if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee1
				.get(o1) < ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(o2)) {
			return -1;
		} else {
		return 0;
		}
	}

}
