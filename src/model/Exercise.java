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
        int setCount = 0;
        String exercise_string = exerciseName + "\n";
        for(int i = 0; i < exerciseSets.size(); i++){
            setCount+=1;
            exercise_string += setCount + ": " + exerciseSets.get(i).getNumReps() + " x " + exerciseSets.get(i).getWeight();
            exercise_string += "\n";
        }
        return exercise_string;
    }
}
