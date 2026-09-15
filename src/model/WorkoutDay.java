package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Locale;

public class WorkoutDay {
    private LocalDate date;
    private LinkedList<MuscleGroup> muscleGroups;

    /**
     * Constructs a WorkoutDay for the current date
     *
     * @pre none
     *
     * @post data = LocalDate.now() AND muscleGroups is an empty list
     */
    public WorkoutDay(){
        date = LocalDate.now();
        muscleGroups = new LinkedList<>();
    }

    /**
     * Constructs a WorkoutDay for the specified date
     *
     * @param aDate the date of the workout
     *
     * @pre aDAte is not NULL
     *
     * @post date = aDate AND muscleGroups is an empty list
     */
    public WorkoutDay(LocalDate aDate){
        date = aDate;
        muscleGroups = new LinkedList<>();
    }

    /**
     * Returns the date of this workout
     *
     * @return the date of this workout
     *
     * @pre none
     *
     * @post getDate = date AND date = #date AND muscleGroups = #muscleGroups
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the muscle groups associated with this workout date
     *
     * @return the list of muscle groups for this workout date
     *
     * @pre none
     *
     * @post getMuscleGroups = muscleGroups AND date = #date AND muscleGroups = #muscleGroups
     */
    public LinkedList<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    /**
     * Adds a muscle group if one with the same name is not already present
     *
     * @param aMuscleGroup the muscle group that is being added
     *
     * @return true if the muscle group is added, false otherwise
     *
     * @pre aMuscleGroup is not NULL
     *
     * @post addMuscleGroup = true IFF aMuscleGroup is not equal to any of the muscleGroups in muscleGroups ELSE false
     *       AND date = #date AND muscleGroups contains all elements of #muscleGroups
     *       in the same order with aMuscleGroup added to the end
     */
    public boolean addMuscleGroup(MuscleGroup aMuscleGroup){
        for(MuscleGroup curr : muscleGroups){
            if(curr.getMuscle().equalsIgnoreCase(aMuscleGroup.getMuscle())){
                return false;
            }
        }
        muscleGroups.add(aMuscleGroup);
        return true;
    }

    /**
     * Returns the workout date and all muscle groups as a string
     *
     * @return a formatted string representation of this workout day
     *
     * @pre none
     *
     * @post toString = date.toString() + "\n" +
     *                  muscleGroups[0].toString() + "\n" +
     *                  muscleGroups[1].toString() + "\n" + ... +
     *                  muscleGroups[n-1].toString() + "\n"
     *       AND date = #date AND muscleGroups = #muscleGroups
     */
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
