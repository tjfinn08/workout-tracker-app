package logic;

import model.Exercise;
import model.MuscleGroup;
import model.WorkoutDay;

import java.sql.Wrapper;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class WorkoutManager {
    private HashMap<LocalDate, WorkoutDay> workoutDays;

    public WorkoutManager(){
        workoutDays = new HashMap<>();
    }

    public WorkoutDay getWorkoutDay(LocalDate aDate) {
        if(!workoutDays.containsKey(aDate)) {
            WorkoutDay day = new WorkoutDay(aDate);
            workoutDays.put(aDate, day);
        }

        return workoutDays.get(aDate);
    }
}
