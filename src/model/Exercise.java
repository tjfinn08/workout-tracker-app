package model;

import java.util.LinkedList;

public class Exercise {

    private String muscleGroup;
    private String exerciseName;
    private LinkedList<SetEntry> exerciseSets;

    public Exercise(String aMuscleGroup, String anExerciseName){
        muscleGroup = aMuscleGroup;
        exerciseName = anExerciseName;
        exerciseSets = new LinkedList<>();
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

    public String toString(){
        String exercise_string = exerciseName;
        for(int i = 0; i < exerciseSets.size(); i++){
            System.out.println(i+1 + ": " + exerciseSets.get(i).getNumReps() + " x " + exerciseSets.get(i).getWeight());
        }
    }
}
