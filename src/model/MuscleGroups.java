package model;

import java.util.LinkedList;

public class MuscleGroups {
    private String muscle;
    private LinkedList<Exercise> muscleExercises;

    public MuscleGroups(String aMuscle){
        muscle = aMuscle;
        muscleExercises = new LinkedList<>();
    }

    public String getMuscle() {
        return muscle;
    }

    public void addExercise(Exercise anExercise){
        muscleExercises.add(anExercise);
    }

    public String toString() {
        String muscle_string = muscle + ":\n";
        for(int i = 0; i < muscleExercises.size(); i++) {
            muscle_string += muscleExercises.get(i).toString();
        }
        return muscle_string;
    }
}
