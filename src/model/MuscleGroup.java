package model;

import java.util.LinkedList;

public class MuscleGroup {
    private String muscle;
    private LinkedList<Exercise> muscleExercises;

    /**
     * Creates a MuscleGroup with the specified name and an empty list of exercises
     *
     * @param aMuscle the name of the muscle group
     *
     * @pre aMuscle is not NULL
     *
     * @post muscle = aMuscle AND muscleExercise is an empty list
     */
    public MuscleGroup(String aMuscle){
        muscle = aMuscle;
        muscleExercises = new LinkedList<>();
    }

    /**
     * Returns the name of this muscle group
     *
     * @return the name of this exercise
     *
     * @pre none
     *
     * @post getMuscle = this.muscle AND muscle = #muscle AND muscleExercise = #muscleExercise
     */
    public String getMuscle() {
        return muscle;
    }

    /**
     * Returns the exercises associated with this muscle group
     *
     * @return the list of exercises for this muscle group
     *
     * @pre none
     *
     * @post getMuscleExercises = muscleExercises AND muscle = #muscle
     *       AND muscleExercise = #muscleExercises
     */
    public LinkedList<Exercise> getMuscleExercises() {
        return muscleExercises;
    }

    /**
     *  Adds an exercise to this muscle group
     *
     * @param anExercise the exercise that is being added
     *
     * @pre anExercise is not NULL
     *
     * @post muscle = #muscle AND
     *       muscleExercise contains all elements of #muscleExercises in the
     *       same order with anExercise added to the end
     */
    public void addExercise(Exercise anExercise){
        muscleExercises.add(anExercise);
    }

    /**
     * Returns the name of this muscle group
     *
     * @return the name of this muscle group
     *
     * @pre none
     *
     * @post toString = this.muscle AND muscle = #muscle AND muscleExercise = #muscleExercise
     */
    public String toString() {
        return muscle;
    }
}
