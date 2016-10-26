package org.optaplanner.examples.shifts.domain;

public class Employee {

	private String name;
	private boolean[] unavailable;
	private int totalAmountOfHoliday;

	public Employee(String name, int n) {
		super();
		this.name = name;
		this.unavailable = new boolean[n];
		this.totalAmountOfHoliday = 0;
	}

	public Employee() {
	}
	
	public String getName() {
		return name;
	}

	public boolean[] getUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean[] unavailable) {
		this.unavailable = unavailable;
	}

	public void setTotalAmountOfHoliday(int totalAmountOfHoliday) {
		this.totalAmountOfHoliday = totalAmountOfHoliday;
	}

	public void setName(String name) {
		this.name = name;
	};

	public boolean isUnavailable(int i) {
        
		return unavailable[i];

	}

	public void setUnavailable(int i, boolean b) {
		if(this.unavailable[i]) {
        	this.totalAmountOfHoliday = this.totalAmountOfHoliday - 1;
        }
		this.unavailable[i] = b;
		if (b) {
			this.totalAmountOfHoliday = this.totalAmountOfHoliday + 1;
		}
	}

	public int getTotalAmountOfHoliday() {
		return totalAmountOfHoliday;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Employee)) {
			return false;
		}
		return this.getName().equals(((Employee) obj).getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
