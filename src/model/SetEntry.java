package model;

public class SetEntry {
    private int numReps;
    private double weight;

    /**
     * Constructs a SetEntry with the specified repetitions and weight
     *
     * @param aNumReps the number of repetitions
     * @param aWeight the weight used
     *
     * @pre aNumReps >= 0 AND aWeight >= 0
     *
     * @post numReps = aNumReps AND weight = aWeight
     */
    public SetEntry(int aNumReps, double aWeight){
        numReps = aNumReps;
        weight = aWeight;
    }

    /**
     * Constructs a SetEntry with zero repetitions and 0 weight
     *
     * @pre none
     *
     * @post numReps = 0 AND weight = 0
     */
    public SetEntry(){
        numReps = 0;
        weight = 0;
    }

    /**
     * Sets the number of repetitions for this set
     *
     * @param aNumReps the new number of repetitions
     *
     * @pre aNumReps >= 0
     *
     * @post numReps = aNumReps AND weight = #weight
     */
    public void setNumReps(int aNumReps) {
        this.numReps = aNumReps;
    }

    /**
     * Sets the weight used for this set
     *
     * @param aWeight the weight used
     *
     * @pre aWeight >= 0
     *
     * @post weight = aWeight AND numReps = #numReps
     */
    public void setWeight(double aWeight) {
        this.weight = aWeight;
    }

    /**
     * Returns the number of repetitions in this set
     *
     * @return the number of repetitions in this set
     *
     * @pre none
     *
     * @post getNumReps = this.numReps AND numReps = #numReps AND weight = #weight
     */
    public int getNumReps() {
        return numReps;
    }

    /**
     * Returns the weight used for this set
     *
     * @return the weight used for this set
     *
     * @pre none
     *
     * @post getWeight = weight AND numReps = #numReps AND weight = #weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the repetitions and weight as a formatted string
     *
     * @return a string with the formatted set information
     *
     * @pre none
     *
     * @post toString = this.numReps + "     X     " + this.weight
     *       AND numReps = #numReps AND weight = #weight
     */
    public String toString() {
        return numReps + "     X     " + weight;
    }
}
