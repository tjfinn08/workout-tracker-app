package model;

public class Exercise {

    private String muscleGroup;
    private String exerciseName;
    private

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

    }
}
