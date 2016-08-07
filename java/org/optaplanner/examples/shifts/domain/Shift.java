package org.optaplanner.examples.shifts.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningEntity
public class Shift extends AbstractPersistable {

	// planning variable
	private Employee receptionMorning0;
	private Employee receptionMorning1;
	private Employee receptionAfternoon0;
	private Employee receptionAfternoon1;

	@PlanningVariable(valueRangeProviderRefs = {
			"employeeRange" }, strengthComparatorClass = EmployeeStrengthComparator0.class)
	public Employee getReceptionMorning0() {
		return receptionMorning0;
	}

	public void setReceptionMorning0(Employee receptionMorning0) {
		if (this.receptionMorning0 != null) {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(this.receptionMorning0,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(this.receptionMorning0) + 1);
		}
		this.receptionMorning0 = receptionMorning0;
		if (receptionMorning0 == null) {
			return;
		} else {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(this.receptionMorning0,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(this.receptionMorning0) - 1);
		}
	}

	@PlanningVariable(valueRangeProviderRefs = { "employeeRange" }, strengthComparatorClass = EmployeeStrengthComparator1.class)
	public Employee getReceptionMorning1() {
		return receptionMorning1;
	}

	public void setReceptionMorning1(Employee receptionMorning1 ) {

		if (this.receptionMorning1 != null) {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(this.receptionMorning1,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(this.receptionMorning1) + 1);
		}
		this.receptionMorning1 = receptionMorning1;

		if (receptionMorning1 == null) {
			return;
		} else {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(this.receptionMorning1,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(this.receptionMorning1) - 1);
		}
	}

	@PlanningVariable(valueRangeProviderRefs = { "employeeRange" }, strengthComparatorClass = EmployeeStrengthComparator0.class)
	public Employee getReceptionAfternoon0() {
		return receptionAfternoon0;
	}

	public void setReceptionAfternoon0(Employee receptionAfternoon0) {
		if (this.receptionAfternoon0 != null) {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(this.receptionAfternoon0,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(this.receptionAfternoon0) + 1);
		}
		this.receptionAfternoon0 = receptionAfternoon0;
		if (receptionAfternoon0 == null) {
			return;
		} else {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(this.receptionAfternoon0,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(this.receptionAfternoon0) - 1);
		}
	}

	@PlanningVariable(valueRangeProviderRefs = { "employeeRange" }, strengthComparatorClass = EmployeeStrengthComparator1.class)
	public Employee getReceptionAfternoon1() {
		return receptionAfternoon1;
	}

	public void setReceptionAfternoon1(Employee receptionAfternoon1) {
		if (this.receptionAfternoon1 != null) {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(this.receptionAfternoon1,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(this.receptionAfternoon1) + 1);
		}
		this.receptionAfternoon1 = receptionAfternoon1;
		if (receptionAfternoon1 == null) {
			return;
		} else {
			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(this.receptionAfternoon1,
					ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(this.receptionAfternoon1) - 1);
		}
	}

	public boolean containsName(String name) {

		return this.receptionMorning0.getName().equals(name) || this.receptionMorning1.getName().equals(name)
				|| this.receptionAfternoon0.getName().equals(name) || this.receptionAfternoon1.getName().equals(name);

	}

	public boolean allDifferent() {
		return !this.receptionMorning0.equals(this.receptionMorning1)
				&& !this.receptionMorning0.equals(this.receptionAfternoon0)
				&& !this.receptionMorning0.equals(this.receptionAfternoon1)
				&& !this.receptionMorning1.equals(this.receptionAfternoon0)
				&& !this.receptionMorning1.equals(this.receptionAfternoon1)
				&& !this.receptionAfternoon0.equals(this.receptionAfternoon1);
	}

}