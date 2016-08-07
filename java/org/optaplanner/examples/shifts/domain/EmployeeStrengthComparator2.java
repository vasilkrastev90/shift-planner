package org.optaplanner.examples.shifts.domain;

import java.util.Comparator;

public class EmployeeStrengthComparator2 implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		if (o1.getTotalAmountOfHoliday() < o2.getTotalAmountOfHoliday()) {
			return 1;
		} else if (o1.getTotalAmountOfHoliday() > o2.getTotalAmountOfHoliday()) {
			return 1;
		} else {
			return 0;
		}
	}

}
