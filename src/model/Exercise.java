package model;

import java.util.LinkedList;

public class Exercise {

    private String muscleGroup;
    private String exerciseName;
    private LinkedList<SetEntry> exerciseSets;

    public Exercise(String aMuscleGroup, String aExerciseName){
        muscleGroup = aMuscleGroup;
        exerciseName = aExerciseName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void addSet(SetEntry aSet){
        exerciseSets.add(aSet);
    }
}
