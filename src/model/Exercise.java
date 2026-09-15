package model;

import java.util.LinkedList;

public class Exercise {

    private String exerciseName;
    private LinkedList<SetEntry> exerciseSets;

    /**
     * Constructs an Exercise with the specified name and an empty list of sets
     *
     * @param anExerciseName the name of the exercise
     *
     * @pre anExerciseName is not null
     *
     * @post exerciseName = anExerciseName AND exerciseSets is an empty list
     */
    public Exercise(String anExerciseName){
        exerciseName = anExerciseName;
        exerciseSets = new LinkedList<>();
    }

    /**
     * Returns the name of this exercise
     *
     * @return the name of this exercise
     *
     * @pre none
     *
     * @post getExerciseName = this.exerciseName AND exerciseName = #exerciseName AND exerciseSets = #exerciseSets
     */
    public String getExerciseName() {
        return exerciseName;
    }

    /**
     * Returns the list of sets associated with this exercise
     *
     * @return the list of sets for this exercise
     *
     * @pre none
     *
     * @post getExerciseSets = this.exerciseSets AND exerciseName = #exerciseName AND exerciseSets = #exerciseSets
     */
    public LinkedList<SetEntry> getExerciseSets() {
        return exerciseSets;
    }

    /**
     * Adds a set to this exercise's set list
     *
     * @param aSet the set to add to this exercise
     *
     * @pre aSet is not NULL
     *
     * @post exerciseName = #exerciseName AND
     *       exerciseSets contains all elements of #exerciseSets in the same order with aSet added to the end
     */
    public void addSet(SetEntry aSet){
        exerciseSets.add(aSet);
    }

    /**
     * Returns the name of this exercise as its string representation
     *
     * @return the exercise name
     *
     * @pre none
     *
     * @post toString = this.exerciseName AND exerciseName = #exerciseName AND exerciseSets = #exerciseSets
     */
    public String toString(){
        return exerciseName;
    }

    /**
     * Returns a string containing every set in this exercise, with each set followed by a new line
     *
     *
     * @return a formatted string containing all sets in this exercise
     *
     * @pre none
     *
     * @post exerciseSets = exerciseSets[0].toString() + "\n" +
     *                      exerciseSets[1].toString() + "\n" + ... +
     *                      exerciseSets[n-1].toString() + "\n"
     *       AND exerciseName = #exerciseName AND exerciseSets = #exerciseSets
     *
     */
    public String exerciseSets() {
        StringBuilder setString = new StringBuilder();
        for(SetEntry s : exerciseSets) {
            setString.append(s).append("\n");
        }
        return setString.toString();
    }
}
