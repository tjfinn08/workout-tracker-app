package logic;

import model.Exercise;
import model.MuscleGroup;
import model.WorkoutDay;

import java.time.LocalDate;
import java.util.LinkedList;

public class WorkoutManager {
    private LinkedList<WorkoutDay> workouts;

    public WorkoutManager(){
        workouts = new LinkedList<>();
    }

    public void addWorkoutDay(WorkoutDay aWorkout){
        workouts.add(aWorkout);
    }

    public LinkedList<WorkoutDay> getWorkouts() {
        return workouts;
    }

    public Exercise findExerciseProgress(String anExerciseName) {
        Exercise recentExercise = null;
        LocalDate recentDate = null;
        for(WorkoutDay currDay : workouts){
            for(MuscleGroup currMuscle : currDay.getMuscleGroups()) {
                for(Exercise currExercise : currMuscle.getMuscleExercises()) {
                    if(currExercise.getExerciseName().equalsIgnoreCase(anExerciseName)) {
                        if(recentDate == null || currDay.getDate().isAfter(recentDate)){
                            recentDate = currDay.getDate();
                            recentExercise = currExercise;
                        }
                    }
                }
            }
        }
        return recentExercise;
    }
}
