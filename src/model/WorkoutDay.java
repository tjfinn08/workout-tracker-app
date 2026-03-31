package model;

import java.time.LocalDate;
import java.util.LinkedList;

public class WorkoutDay {
    private LocalDate date;
    private LinkedList<MuscleGroup> muscleGroups;

    public WorkoutDay(){
        date = LocalDate.now();
        muscleGroups = new LinkedList<>();
    }

    public WorkoutDay(LocalDate aDate){
        date = aDate;
        muscleGroups = new LinkedList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public LinkedList<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public boolean addMuscleGroup(MuscleGroup aMuscleGroup){
        for(MuscleGroup curr : muscleGroups){
            if(curr.getMuscle().equals(aMuscleGroup.getMuscle())){
                return false;
            }
        }
        muscleGroups.add(aMuscleGroup);
        return true;
    }

    @Override
    public String toString() {
        String workoutDateString = date + "\n";
        for(int i = 0; i < muscleGroups.size(); i++){
            workoutDateString += muscleGroups.get(i).toString();
            workoutDateString += "\n";
        }
        return workoutDateString;
    }
}
