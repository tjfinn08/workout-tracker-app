package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Locale;

public class Exercise {

    private String exerciseName;
    private LinkedList<SetEntry> exerciseSets;

    public Exercise(String anExerciseName){
        exerciseName = anExerciseName;
        exerciseSets = new LinkedList<>();
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public LinkedList<SetEntry> getExerciseSets() {
        return exerciseSets;
    }

    public void addSet(SetEntry aSet){
        exerciseSets.add(aSet);
    }

    public String toString(){
        return exerciseName;
    }
}
