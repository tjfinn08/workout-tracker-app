package logic;

import model.Exercise;
import model.WorkoutDay;

import java.util.LinkedList;

public class WorkoutManager {
    private LinkedList<WorkoutDay> workoutDays;

    public WorkoutManager(){
        workoutDays = new LinkedList<>();
    }

    public void addWorkoutDay(WorkoutDay aWorkoutDay){
        workoutDays.add(aWorkoutDay);
    }

    public LinkedList<WorkoutDay> getWorkoutDays() {
        return workoutDays;
    }

    public Exercise findExerciseProgress(String anExerciseName) {

    }
}
