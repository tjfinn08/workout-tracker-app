package model;

import java.util.LinkedList;

public class MuscleGroup {
    private String muscle;
    private LinkedList<Exercise> muscleExercises;

    public MuscleGroup(String aMuscle){
        muscle = aMuscle;
        muscleExercises = new LinkedList<>();
    }

    public String getMuscle() {
        return muscle;
    }

    public LinkedList<Exercise> getMuscleExercises() {
        return muscleExercises;
    }

    public void addExercise(Exercise anExercise){
        muscleExercises.add(anExercise);
    }

    public String toString() {
        return muscle;
    }
}
