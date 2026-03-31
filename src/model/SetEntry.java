package model;

public class SetEntry {
    private int numReps;
    private double weight;

    public SetEntry(int aNumReps, double aWeight){
        numReps = aNumReps;
        weight = aWeight;
    }

    public SetEntry(){
        numReps = 0;
        weight = 0;
    }

    public void setNumReps(int aNumReps) {
        this.numReps = aNumReps;
    }

    public void setWeight(double aWeight) {
        this.weight = aWeight;
    }

    public int getNumReps() {
        return numReps;
    }

    public double getWeight() {
        return weight;
    }
}
