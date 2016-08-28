package org.optaplanner.examples.shifts.score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.domain.Shift;
import org.optaplanner.examples.shifts.domain.ShiftTimeTableGenerator;
import org.optaplanner.examples.shifts.domain.ShiftTimetable;

public class ShiftPlanningEasyScoreCalculator implements EasyScoreCalculator<ShiftTimetable> {

	@Override
	public HardSoftScore calculateScore(ShiftTimetable timetable) {
		int hardScore = 0;
		int softScore = 0;
		List<Shift> shifts = timetable.getListOfShifts();

		for (int i = 0; i < shifts.size(); i++) {
			if (shifts.get(i).getReceptionMorning0() == null || shifts.get(i).getReceptionMorning1() == null
					|| shifts.get(i).getReceptionAfternoon0() == null || shifts.get(i).getReceptionAfternoon1() == null)
				continue;

			Employee morning0 = shifts.get(i).getReceptionMorning0();
			Employee morning1 = shifts.get(i).getReceptionMorning1();
			Employee afternoon0 = shifts.get(i).getReceptionAfternoon0();
			Employee afternoon1 = shifts.get(i).getReceptionAfternoon1();
			
			
			if (!shifts.get(i).allDifferent()) {
				hardScore = hardScore - 1;
				continue;
			}

			if (morning0.isUnavailable(i) || morning1.isUnavailable(i) || afternoon0.isUnavailable(i)
					|| afternoon1.isUnavailable(i)) {
				hardScore = hardScore - 1;
				continue;
			}
			


			int lookback = i-1;
			if (lookback>=0) {
				Employee morning0Before = shifts.get(lookback).getReceptionMorning0();
				Employee morning1Before = shifts.get(lookback).getReceptionMorning1();
				Employee afternoon0Before = shifts.get(lookback).getReceptionAfternoon0();
				Employee afternoon1Before = shifts.get(lookback).getReceptionAfternoon1();
				Employee[] emparrayToday= {morning0,morning1,afternoon0,afternoon1};
				Employee[] emparrayYesterday = {morning0Before,morning1Before,afternoon0Before,afternoon1Before};
				ArrayList<Employee> TodayShifts = new ArrayList<Employee>(Arrays.asList(emparrayToday));
				ArrayList<Employee> YesterdayShifts = new ArrayList<Employee>(Arrays.asList(emparrayYesterday));
                for (Employee employee : YesterdayShifts) {
					if(TodayShifts.contains(employee)) {
						softScore= softScore-1;
					}
				}
				
				if (morning0Before == null || morning1Before == null || afternoon0Before == null || afternoon1Before == null) 
					continue;
				
				
		    }

			
			for (Employee employee : timetable.getEmployees()) {
				
				if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shifts.get(i).getReceptionMorning0())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee) < -1) {
					softScore = softScore + ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shifts.get(i).getReceptionMorning0())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee);
				}
				if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shifts.get(i).getReceptionMorning1())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee) < -1) {
					softScore = softScore + ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shifts.get(i).getReceptionMorning1())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee);
				}
				if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shifts.get(i).getReceptionAfternoon0())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee) < -1) {
					softScore = softScore + ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shifts.get(i).getReceptionAfternoon0())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee);
				}
				if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shifts.get(i).getReceptionAfternoon1())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee) < -1) {
					softScore = softScore  + ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shifts.get(i).getReceptionAfternoon1())- ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee);
				}
			}
		}
		
		for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.keySet()) {
			if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee) < 0) {
				softScore = softScore - 1;
			}
		}
		for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.keySet()) {
			if (ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee) < 0) {
				softScore = softScore - 1;
			}
			// System.out.println("The key:" + employee.getName() + " " +
			// timetable.getNumberOfShiftsPerEmployee().get(employee));
		}

		return HardSoftScore.valueOf(hardScore, softScore);
	}

}
