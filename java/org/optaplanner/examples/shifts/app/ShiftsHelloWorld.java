package org.optaplanner.examples.shifts.app;

import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.shifts.domain.Employee;
import org.optaplanner.examples.shifts.domain.Shift;
import org.optaplanner.examples.shifts.domain.ShiftTimeTableGenerator;
import org.optaplanner.examples.shifts.domain.ShiftTimetable;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

public class ShiftsHelloWorld {

	public static void main(String[] args) {
		
        // Build the Solver
        SolverFactory<ShiftTimetable> solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/shifts/solver/shiftsSolverConfig.xml");
        Solver<ShiftTimetable> solver = solverFactory.buildSolver();
        int numberOfDays = 23;
        
//        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//        loggerContext.stop();
        
        // Load a problem with 8 queens
        ShiftTimetable unsolvedShifts = new ShiftTimeTableGenerator().createShiftTimeTable(numberOfDays);

        // Solve the problem
        ShiftTimetable solvedShifts = solver.solve(unsolvedShifts);

        // Display the result
        System.out.println("\nSolved" + numberOfDays + "shifts:\n" + toDisplayString(solvedShifts));
    }

    public static String toDisplayString(ShiftTimetable shifts) {
        StringBuilder displayString = new StringBuilder();
        int numberOfShifts = shifts.getNumberOfDays();
        List<Shift> listOfShifts = shifts.getListOfShifts();
        
            for (int i = 0; i < numberOfShifts; i++) {
                Shift shift = listOfShifts.get(i);
               
                displayString.append("Employees for day:" + (i+1) + " " + shift.getReceptionMorning0().getName() + ", ");
                displayString.append(shift.getReceptionMorning1().getName() + ", ");
                displayString.append(shift.getReceptionAfternoon0().getName() + ", ");
                displayString.append(shift.getReceptionAfternoon1().getName() + "\n");
                
            }
            for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.keySet()) {
            	ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(employee,0);
            	ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(employee,0);
            }
            for (Shift shift : listOfShifts) {
    			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(shift.getReceptionMorning0(),ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shift.getReceptionMorning0())+1);
    			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(shift.getReceptionMorning1(),ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shift.getReceptionMorning1())+1);
    			ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.put(shift.getReceptionAfternoon0(),ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(shift.getReceptionAfternoon0())+1);
    			ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.put(shift.getReceptionAfternoon1(),ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(shift.getReceptionAfternoon1())+1);
    		}
            
            for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.keySet()) {
				displayString.append("Employee entry:" + employee.getName() + ":" + ShiftTimeTableGenerator.numberOfShiftsPerEmployee0.get(employee) + "  ");
            }
            
            displayString.append("\n");
            
            for (Employee employee : ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.keySet()) {
				displayString.append("Employee entry:" + employee.getName() + ":" + ShiftTimeTableGenerator.numberOfShiftsPerEmployee1.get(employee) + "  ");
            }
            
            displayString.append("\n");
        
        return displayString.toString();
    }

}
